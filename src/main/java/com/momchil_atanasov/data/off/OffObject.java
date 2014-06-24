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

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an object in the OFF model.
 *
 * @author Momchil Atanasov
 */
public class OffObject {

    private final List<OffVertex> vertices = new ArrayList<OffVertex>();
    private final List<OffFace> faces = new ArrayList<OffFace>();
    private boolean hasVertexColors = false;

    /**
     * Creates a new {@link OffObject}.
     */
    public OffObject() {
        super();
    }

    /**
     * Returns a modifiable list of vertices.
     *
     * @return a non-null {@link List}
     */
    public List<OffVertex> getVertices() {
        return vertices;
    }

    /**
     * Helper method that returns the vertex with the specified index.
     *
     * @param index index of the vertex
     * @return the vertex at the specified index
     */
    public OffVertex getVertex(int index) {
        return vertices.get(index);
    }

    /**
     * Returns a modifiable list of faces.
     *
     * @return a non-null {@link List}
     */
    public List<OffFace> getFaces() {
        return faces;
    }

    /**
     * Sets whether this object has color information per each vertex.
     *
     * @param hasVertexColors if <code>true</code> then each vertex of this
     * object will have color information, otherwise <code>false</code>
     */
    public void setHasVertexColors(boolean hasVertexColors) {
        this.hasVertexColors = hasVertexColors;
    }

    /**
     * Returns whether this object has color information per each vertex.
     *
     * @return <code>true</code> if each face is vertex-colored,
     * <code>false</code> otherwise.
     */
    public boolean getHasVertexColors() {
        return hasVertexColors;
    }
}
