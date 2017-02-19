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
package com.mokiat.data.off.test;

import static com.mokiat.data.off.test.util.OffLoaderTestUtil.assertFaceColorEquals;
import static com.mokiat.data.off.test.util.OffLoaderTestUtil.assertFaceVerticesEquals;
import static com.mokiat.data.off.test.util.OffLoaderTestUtil.assertVertexCoordEquals;
import static com.mokiat.data.off.test.util.OffLoaderTestUtil.loadResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.mokiat.data.off.OffObject;

@RunWith(JUnit4.class)
public class OffLoaderBaseTest {

    protected OffObject object;

    @Before
    public void setUp() throws Exception {
        object = loadResource(getResourceName());
    }

    @Test
    public void testLoadVertices() throws Exception {
        assertEquals(4, object.getVertices().size());
        assertVertexCoordEquals(-1.0f, 1.0f, 1.0f, object.getVertex(0));
        assertVertexCoordEquals(-1.0f, -1.0f, -1.0f, object.getVertex(1));
        assertVertexCoordEquals(1.0f, -1.0f, 1.0f, object.getVertex(2));
        assertVertexCoordEquals(1.0f, 1.0f, -1.0f, object.getVertex(3));
    }

    @Test
    public void testLoadFaces() throws Exception {
        assertEquals(2, object.getFaces().size());
        assertFaceVerticesEquals(0, 1, 2, object.getFaces().get(0));
        assertFaceVerticesEquals(0, 2, 3, object.getFaces().get(1));
    }

    @Test
    public void testLoadVertexColor() throws Exception {
        assertFalse(object.getHasVertexColors());
    }

    @Test
    public void testLoadFaceColor() throws Exception {
        assertFalse(object.getFaces().get(0).getHasColor());
        assertFalse(object.getFaces().get(1).getHasColor());
        // When no face color is specified, default is gray
        assertFaceColorEquals(0.666f, 0.666f, 0.666f, 0.666f, object.getFaces().get(0));
        assertFaceColorEquals(0.666f, 0.666f, 0.666f, 0.666f, object.getFaces().get(1));
    }

    /**
     * Override this method to test variations of the basic OFF file.
     */
    protected String getResourceName() {
        return "valid_basic.off";
    }
}
