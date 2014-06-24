/*
 * Copyright (C) momchil-atanasov.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.momchil_atanasov.data.off;

import static com.momchil_atanasov.data.off.OffLoaderUtil.parseColorSegmentSafe;
import static com.momchil_atanasov.data.off.OffLoaderUtil.parseFloatSafe;
import static com.momchil_atanasov.data.off.OffLoaderUtil.parseIntSafe;
import static com.momchil_atanasov.data.off.OffLoaderUtil.readContentLineMultiple;
import static com.momchil_atanasov.data.off.OffLoaderUtil.readContentLineSingle;

import java.io.BufferedReader;
import java.io.IOException;

import com.momchil_atanasov.data.off.error.OffCorruptException;
import com.momchil_atanasov.data.off.error.OffSizeException;
import com.momchil_atanasov.data.off.error.OffUnsupportedException;

class OffLoaderContext {

    private static final String OFF_BINARY_HEADER_SUFFIX = "OFF BINARY";
    private static final String OFF_HEADER = "OFF";
    private static final String OFF_COLOR_HEADER = "COFF";
    private final IOffLoader loader;
    private final OffObject offObject = new OffObject();
    private int vertexCount = 0;
    private int faceCount = 0;

    public OffLoaderContext(IOffLoader loader) {
        this.loader = loader;
    }

    public OffObject getOffObject() {
        return offObject;
    }

    public void readHeader(BufferedReader reader) throws IOException {
        final String header = readContentLineSingle(reader);
        if (OFF_BINARY_HEADER_SUFFIX.equalsIgnoreCase(header)) {
            throw new OffUnsupportedException("Binary OFF not supported.");
        }
        if (!header.endsWith(OFF_HEADER)) {
            throw new OffCorruptException("Missing header.");
        }
        if (OFF_HEADER.equalsIgnoreCase(header)) {
            return;
        }
        if (OFF_COLOR_HEADER.equalsIgnoreCase(header)) {
            offObject.setHasVertexColors(true);
            return;
        }
        throw new OffUnsupportedException("Unsupported header.");
    }

    /*
     * Note: Even though we expect 3 segments, we are only
     * interested in the first two.
     */
    public void readDimensions(BufferedReader reader) throws IOException {
        final String[] segments = readContentLineMultiple(reader);
        if (segments.length < 3) {
            throw new OffCorruptException("Incomplete count indicators.");
        }
        vertexCount = parseIntSafe(segments[0]);
        if (vertexCount > loader.getMaxVertexCount()) {
            throw new OffSizeException("Number of vertices exceeds max range.");
        }
        faceCount = parseIntSafe(segments[1]);
        if (faceCount > loader.getMaxFaceCount()) {
            throw new OffSizeException("Number of faces exceeds max range.");
        }
    }

    public void readVertices(BufferedReader reader) throws IOException {
        for (int i = 0; i < vertexCount; ++i) {
            offObject.getVertices().add(readVertex(reader));
        }
    }

    private OffVertex readVertex(BufferedReader reader) throws IOException {
        final String[] segments = readContentLineMultiple(reader);
        if (segments.length < 3) {
            throw new OffCorruptException("Insufficient coordinates.");
        }
        final OffVertex vertex = new OffVertex();
        vertex.x = parseFloatSafe(segments[0]);
        vertex.y = parseFloatSafe(segments[1]);
        vertex.z = parseFloatSafe(segments[2]);
        if (offObject.getHasVertexColors()) {
            if (segments.length < 7) {
                throw new OffCorruptException("Missing vertex color.");
            }
            vertex.r = parseColorSegmentSafe(segments[3]);
            vertex.g = parseColorSegmentSafe(segments[4]);
            vertex.b = parseColorSegmentSafe(segments[5]);
            vertex.a = parseColorSegmentSafe(segments[6]);

        }
        return vertex;
    }

    public void readFaces(BufferedReader reader) throws IOException {
        for (int i = 0; i < faceCount; ++i) {
            offObject.getFaces().add(readFace(reader));
        }
    }

    private OffFace readFace(BufferedReader reader) throws IOException {
        final String[] segments = readContentLineMultiple(reader);
        if (segments.length == 0) {
            throw new OffCorruptException("Missing face information");
        }
        final int vertexRefCount = parseIntSafe(segments[0]);
        if (vertexRefCount > segments.length - 1) {
            throw new OffCorruptException("Insufficient face data.");
        }
        final OffFace face = new OffFace();
        for (int i = 0; i < vertexRefCount; ++i) {
            final int vertexRef = parseIntSafe(segments[i + 1]);
            face.getVertexReferences().add(vertexRef);
        }
        if (segments.length - 1 >= vertexRefCount + 3) {
            face.setHasColor(true);
            face.r = parseColorSegmentSafe(segments[vertexRefCount + 1]);
            face.g = parseColorSegmentSafe(segments[vertexRefCount + 2]);
            face.b = parseColorSegmentSafe(segments[vertexRefCount + 3]);
            if (segments.length - 1 >= vertexRefCount + 4) {
                face.a = parseColorSegmentSafe(segments[vertexRefCount + 4]);
            } else {
                face.a = 1.0f;
            }
        }
        return face;
    }
}
