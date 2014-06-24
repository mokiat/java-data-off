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

/**
 * Represents a vertex in the OFF model.
 *
 * @author Momchil Atanasov
 */
public class OffVertex {

    /**
     * The X coordinate of this vertex.
     */
    public float x = 0.0f;
    /**
     * The Y coordinate of this vertex.
     */
    public float y = 0.0f;
    /**
     * The Z coordinate of this vertex.
     */
    public float z = 0.0f;
    /**
     * The Red component of the color of this vertex.
     */
    public float r = 0.0f;
    /**
     * The Green component of the color of this vertex.
     */
    public float g = 0.0f;
    /**
     * The Blue component of the color of this vertex.
     */
    public float b = 0.0f;
    /**
     * The Alpha component of the color of this vertex.
     */
    public float a = 0.0f;

    /**
     * Creates a new {@link OffVertex}.
     */
    public OffVertex() {
        super();
    }

    /**
     * Creates a new {@link OffVertex} with the specified coordinates.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public OffVertex(float x, float y, float z) {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OffVertex)) {
            return false;
        }
        final OffVertex other = (OffVertex) obj;
        return (Float.floatToRawIntBits(x) == Float.floatToRawIntBits(other.x))
                && (Float.floatToRawIntBits(y) == Float.floatToRawIntBits(other.y))
                && (Float.floatToRawIntBits(z) == Float.floatToRawIntBits(other.z))
                && (Float.floatToRawIntBits(r) == Float.floatToRawIntBits(other.r))
                && (Float.floatToRawIntBits(g) == Float.floatToRawIntBits(other.g))
                && (Float.floatToRawIntBits(b) == Float.floatToRawIntBits(other.b))
                && (Float.floatToRawIntBits(a) == Float.floatToRawIntBits(other.a));
    }

    @Override
    public int hashCode() {
        int result = Float.floatToRawIntBits(x);
        result += result * 31 + Float.floatToRawIntBits(y);
        result += result * 31 + Float.floatToRawIntBits(z);
        result += result * 31 + Float.floatToRawIntBits(r);
        result += result * 31 + Float.floatToRawIntBits(g);
        result += result * 31 + Float.floatToRawIntBits(b);
        result += result * 31 + Float.floatToRawIntBits(a);
        return result;
    }
}
