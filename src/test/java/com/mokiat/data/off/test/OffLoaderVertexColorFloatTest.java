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

import static com.mokiat.data.off.test.util.OffLoaderTestUtil.assertVertexColorEquals;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OffLoaderVertexColorFloatTest extends OffLoaderBaseTest {

	@Override
	protected String getResourceName() {
		return "valid_vertex_color_float.off";
	}

	@Override
	public void testLoadVertexColor() throws Exception {
		assertThat(object.getHasVertexColors(), is(true));
		assertVertexColorEquals(1.0f, 0.0f, 0.0f, 1.0f, object.getVertex(0));
		assertVertexColorEquals(0.0f, 1.0f, 0.0f, 0.5f, object.getVertex(1));
		assertVertexColorEquals(0.0f, 0.0f, 1.0f, 1.0f, object.getVertex(2));
		assertVertexColorEquals(1.0f, 1.0f, 1.0f, 0.5f, object.getVertex(3));
	}
}
