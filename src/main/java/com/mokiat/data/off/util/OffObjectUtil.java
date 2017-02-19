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
package com.mokiat.data.off.util;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;

import com.mokiat.data.off.OffObject;
import com.mokiat.data.off.OffVertex;

/**
 * Utility class for working with {@link OffObject} models.
 *
 * @author Momchil Atanasov
 */
public class OffObjectUtil {

    /**
     * Positions the specified {@link OffObject} in the center of the coordinate
     * system.
     * <p>
     * This implementation does not use the center of gravity approach. It does
     * a centering of the imaginary bounding box of the model.
     *
     * @param offObject OFF model to center
     */
    public static void centerObject(OffObject offObject) {
        float minX = 0.0f;
        float maxX = 0.0f;
        float minY = 0.0f;
        float maxY = 0.0f;
        float minZ = 0.0f;
        float maxZ = 0.0f;
        for (OffVertex vertex : offObject.getVertices()) {
            minX = min(minX, vertex.x);
            maxX = max(maxX, vertex.x);
            minY = min(minY, vertex.y);
            maxY = max(maxY, vertex.y);
            minZ = min(minZ, vertex.z);
            maxZ = max(maxZ, vertex.z);
        }
        float centerX = (maxX + minX) / 2.0f;
        float centerY = (maxY + minY) / 2.0f;
        float centerZ = (maxZ + minZ) / 2.0f;
        offsetObject(offObject, -centerX, -centerY, -centerZ);
    }

    /**
     * Moves the specified {@link OffObject} with the specified offset.
     *
     * @param offObject OFF model to translate
     * @param x X offset
     * @param y Y offset
     * @param z Z offset
     */
    public static void offsetObject(OffObject offObject, float x, float y, float z) {
        for (OffVertex vertex : offObject.getVertices()) {
            vertex.x += x;
            vertex.y += y;
            vertex.z += z;
        }
    }

    /**
     * Scale the specified {@link OffObject} so that is fits within a sphere
     * with the specified radius.
     *
     * @param offObject OFF model to scale
     * @param radius radius within the object should fit.
     */
    public static void scaleObjectToFit(OffObject offObject, float radius) {
        float maxRadiusSqr = 0.0f;
        for (OffVertex vertex : offObject.getVertices()) {
            final float radiusSqr = 
                    vertex.x * vertex.x
                    + vertex.y * vertex.y
                    + vertex.z * vertex.z;
            if (radiusSqr > maxRadiusSqr) {
                maxRadiusSqr = radiusSqr;
            }
        }
        final float maxRadius = (float) sqrt(maxRadiusSqr);
        if (maxRadius > 0.0001f) {
            scaleObject(offObject, radius / maxRadius);
        }
    }

    /**
     * Scales the specified {@link OffObject} with the specified amount.
     *
     * @param offObject OFF model to scale
     * @param amount a float number that specifies the amount of scale.
     */
    public static void scaleObject(OffObject offObject, float amount) {
        for (OffVertex vertex : offObject.getVertices()) {
            vertex.x *= amount;
            vertex.y *= amount;
            vertex.z *= amount;
        }
    }

    protected OffObjectUtil() {
        super();
    }
}
