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
 * Represents a face in the OFF model.
 *
 * @author Momchil Atanasov
 */
public class OffFace {

    /**
     * The Red component of the color of this face.
     */
    public float r = 0.666f;
    /**
     * The Green component of the color of this face.
     */
    public float g = 0.666f;
    /**
     * The Blue component of the color of this face.
     */
    public float b = 0.666f;
    /**
     * The Alpha component of the color of this face.
     */
    public float a = 0.666f;
    private final List<Integer> vertexReferences = new ArrayList<Integer>(4);
    private boolean hasColor = false;

    /**
     * Creates a new {@link OffFace}.
     */
    public OffFace() {
        super();
    }

    /**
     * Sets whether this face has color information.
     *
     * @param hasColor <code>true</code> if this face has color information,
     * <code>false</code> otherwise.
     */
    public void setHasColor(boolean hasColor) {
        this.hasColor = hasColor;
    }

    /**
     * Returns whether this face has color information.
     * <p>
     * By default faces don't have color information.
     *
     * @return <code>true</code> if this face has color information,
     * <code>false</code> otherwise.
     */
    public boolean getHasColor() {
        return hasColor;
    }

    /**
     * Returns a modifiable list of vertex references.
     *
     * @return a non-null writable {@link List}
     */
    public List<Integer> getVertexReferences() {
        return vertexReferences;
    }
}
