/*
 * Copyright (C) mokiat.com
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
package com.mokiat.data.off;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.mokiat.data.off.error.OffException;

/**
 * Basic implementation of the {@link IOffLoader} interface.
 *
 * @author Momchil Atanasov
 */
public class OffLoader implements IOffLoader {

    private int maxVertexCount = DEFAULT_MAX_VERTEX_COUNT;
    private int maxFaceCount = DEFAULT_MAX_FACE_COUNT;

    public OffLoader() {
        super();
    }

    @Override
    public void setMaxVertexCount(int count) {
        this.maxVertexCount = count;
    }

    @Override
    public int getMaxVertexCount() {
        return maxVertexCount;
    }

    @Override
    public void setMaxFaceCount(int count) {
        this.maxFaceCount = count;
    }

    @Override
    public int getMaxFaceCount() {
        return maxFaceCount;
    }

    @Override
    public OffObject load(InputStream in) throws OffException, IOException {
        try {
            final Reader reader = new InputStreamReader(in);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            return load(bufferedReader);
        } finally {
            in.close();
        }
    }

    private OffObject load(BufferedReader reader) throws IOException {
        final OffLoaderContext context = new OffLoaderContext(this);
        context.readHeader(reader);
        context.readDimensions(reader);
        context.readVertices(reader);
        context.readFaces(reader);
        return context.getOffObject();
    }
}
