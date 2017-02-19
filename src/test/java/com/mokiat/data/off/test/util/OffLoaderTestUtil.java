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
package com.mokiat.data.off.test.util;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.mokiat.data.off.IOffLoader;
import com.mokiat.data.off.OffFace;
import com.mokiat.data.off.OffLoader;
import com.mokiat.data.off.OffObject;
import com.mokiat.data.off.OffVertex;

public class OffLoaderTestUtil {

	private static final String RESOURCE_PACKAGE = "/com/mokiat/data/off/test/resources/";

	public static OffObject loadResource(String name) throws Exception {
		final IOffLoader loader = new OffLoader();
		try (InputStream in = openResource(name)) {
			return loader.load(in);
		}
	}

	public static void assertVertexCoordEquals(float x, float y, float z, OffVertex vertex) {
		assertThat(vertex.x, isEqualTo(x));
		assertThat(vertex.y, isEqualTo(y));
		assertThat(vertex.z, isEqualTo(z));
	}

	public static void assertVertexColorEquals(float r, float g, float b, float a, OffVertex vertex) {
		assertThat(vertex.r, isEqualTo(r));
		assertThat(vertex.g, isEqualTo(g));
		assertThat(vertex.b, isEqualTo(b));
		assertThat(vertex.a, isEqualTo(a));
	}

	public static void assertVertexColorEquals(int r, int g, int b, int a, OffVertex vertex) {
		assertVertexColorEquals(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f, vertex);
	}

	public static void assertFaceVerticesEquals(int v0, int v1, int v2, OffFace face) {
		assertThat(face.getVertexReferences(), hasSize(3));
		assertThat(face.getVertexReferences().get(0).intValue(), is(v0));
		assertThat(face.getVertexReferences().get(1).intValue(), is(v1));
		assertThat(face.getVertexReferences().get(2).intValue(), is(v2));
	}

	public static void assertFaceColorEquals(float r, float g, float b, float a, OffFace face) {
		assertThat(face.r, isEqualTo(r));
		assertThat(face.g, isEqualTo(g));
		assertThat(face.b, isEqualTo(b));
		assertThat(face.a, isEqualTo(a));
	}

	public static void assertFaceColorEquals(int r, int g, int b, int a, OffFace face) {
		assertFaceColorEquals(r / 255.0f, g / 255.0f, b / 255.0f, a / 255.0f, face);
	}

	public static Matcher<Float> isEqualTo(final float expectedValue) {
		final float error = 0.0001f;
		return new BaseMatcher<Float>() {

			@Override
			public boolean matches(Object other) {
				if (other instanceof Float) {
					final float otherValue = ((Float) other).floatValue();
					return Math.abs(expectedValue - otherValue) < error;
				} else {
					return false;
				}
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(String.valueOf(expectedValue));
			}
		};
	}

	private static InputStream openResource(String name) {
		final InputStream result = OffLoaderTestUtil.class.getResourceAsStream(RESOURCE_PACKAGE + name);
		if (result == null) {
			throw new IllegalStateException("Could not find resource '" + name + "'.");
		}
		return result;
	}

	private OffLoaderTestUtil() {
		// Prevent instantiation
	}
}
