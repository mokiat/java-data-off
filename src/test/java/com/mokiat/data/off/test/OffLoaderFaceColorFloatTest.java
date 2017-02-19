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
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OffLoaderFaceColorFloatTest extends OffLoaderBaseTest {

    @Override
    protected String getResourceName() {
        return "valid_face_color_float.off";
    }

    @Override
    public void testLoadFaceColor() throws Exception {
        assertTrue(object.getFaces().get(0).getHasColor());
        assertTrue(object.getFaces().get(1).getHasColor());
        assertFaceColorEquals(1.0f, 0.5f, 0.0f, 0.5f, object.getFaces().get(0));
        assertFaceColorEquals(0.0f, 0.0f, 1.0f, 1.0f, object.getFaces().get(1));
    }
}
