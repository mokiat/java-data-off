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

import java.io.IOException;
import java.io.InputStream;

import com.mokiat.data.off.error.OffException;

/**
 * This interface represents a loader that can parse OFF resources and build an
 * OFF model.
 *
 * @author Momchil Atanasov
 */
public interface IOffLoader {

    /**
     * Default maximum vertex count for a model.
     */
    public static final int DEFAULT_MAX_VERTEX_COUNT = 65536;
    /**
     * Default maximum face count for a model.
     */
    public static final int DEFAULT_MAX_FACE_COUNT = 65536;

    /**
     * Sets a limit on the number of vertices that a model being loaded can
     * have.
     *
     * @param count maximum number of vertices
     */
    public void setMaxVertexCount(int count);

    /**
     * Returns the maximum number of vertices that a model being loaded can
     * have.
     * <p>
     * By default this value is equal to
     * {@link IOffLoader#DEFAULT_MAX_VERTEX_COUNT}.
     *
     * @return maximum number of vertices
     */
    public int getMaxVertexCount();

    /**
     * Sets the maximum number of faces that a model being loaded can have.
     *
     * @param count maximum number of faces
     */
    public void setMaxFaceCount(int count);

    /**
     * Returns the maximum number of faces that a model being loaded can have.
     * <p>
     * By default this value is equal to
     * {@link IOffLoader#DEFAULT_MAX_FACE_COUNT}.
     *
     * @return maximum number of faces
     */
    public int getMaxFaceCount();

    /**
     * Parses an OFF resource from the specified {@link InputStream} and returns
     * the OFF model.
     *
     * @param in input stream from which the model will be read.
     * @return an instance of {@link OffObject}
     * @throws OffException if an error occurs during Off parsing
     * @throws IOException if an I/O error occurs
     */
    public OffObject load(InputStream in) throws OffException, IOException;
}
