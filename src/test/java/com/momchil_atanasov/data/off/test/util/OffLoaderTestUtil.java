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
package com.momchil_atanasov.data.off.test.util;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import com.momchil_atanasov.data.off.OffFace;
import com.momchil_atanasov.data.off.IOffLoader;
import com.momchil_atanasov.data.off.OffLoader;
import com.momchil_atanasov.data.off.OffObject;
import com.momchil_atanasov.data.off.OffVertex;

public class OffLoaderTestUtil {

    private static final String RESOURCE_PACKAGE = "/com/momchil_atanasov/data/off/test/resources/";

    public static OffObject loadResource(String name) throws Exception {
        final InputStream in = OffLoaderTestUtil.class.getResourceAsStream(RESOURCE_PACKAGE + name);
        if (in == null) {
            throw new IllegalStateException("Could not find resource '" + name + "'.");
        }
        final IOffLoader loader = new OffLoader();
        try {
            return loader.load(in);
        } finally {
            in.close();
        }
    }

    public static void assertVertexCoordEquals(float x, float y, float z, OffVertex vertex) {
        assertEquals(x, vertex.x, 0.0001f);
        assertEquals(y, vertex.y, 0.0001f);
        assertEquals(z, vertex.z, 0.0001f);
    }

    public static void assertVertexColorEquals(float r, float g, float b, float a, OffVertex vertex) {
        assertEquals(r, vertex.r, 0.0001f);
        assertEquals(g, vertex.g, 0.0001f);
        assertEquals(b, vertex.b, 0.0001f);
        assertEquals(a, vertex.a, 0.0001f);
    }

    public static void assertVertexColorEquals(int r, int g, int b, int a, OffVertex vertex) {
        assertEquals(r / 255.0f, vertex.r, 0.0001f);
        assertEquals(g / 255.0f, vertex.g, 0.0001f);
        assertEquals(b / 255.0f, vertex.b, 0.0001f);
        assertEquals(a / 255.0f, vertex.a, 0.0001f);
    }

    public static void assertFaceVerticesEquals(int v0, int v1, int v2, OffFace face) {
        assertEquals(3, face.getVertexReferences().size());
        assertEquals(v0, face.getVertexReferences().get(0).intValue());
        assertEquals(v1, face.getVertexReferences().get(1).intValue());
        assertEquals(v2, face.getVertexReferences().get(2).intValue());
    }

    public static void assertFaceColorEquals(float r, float g, float b, float a, OffFace face) {
        assertEquals(r, face.r, 0.0001f);
        assertEquals(g, face.g, 0.0001f);
        assertEquals(b, face.b, 0.0001f);
        assertEquals(a, face.a, 0.0001f);
    }

    public static void assertFaceColorEquals(int r, int g, int b, int a, OffFace face) {
        assertEquals(r / 255.0f, face.r, 0.0001f);
        assertEquals(g / 255.0f, face.g, 0.0001f);
        assertEquals(b / 255.0f, face.b, 0.0001f);
        assertEquals(a / 255.0f, face.a, 0.0001f);
    }

    private OffLoaderTestUtil() {
        // Prevent instantiation
    }
}
