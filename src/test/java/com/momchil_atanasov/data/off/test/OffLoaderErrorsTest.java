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
package com.momchil_atanasov.data.off.test;

import static com.momchil_atanasov.data.off.test.util.OffLoaderTestUtil.loadResource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.momchil_atanasov.data.off.error.OffCorruptException;
import com.momchil_atanasov.data.off.error.OffSizeException;
import com.momchil_atanasov.data.off.error.OffUnsupportedException;

@RunWith(JUnit4.class)
public class OffLoaderErrorsTest {

    @Test(expected = OffCorruptException.class)
    public void testMissingHeader() throws Exception {
        loadResource("error_missing_header.off");
    }

    @Test(expected = OffCorruptException.class)
    public void testInvalidHeader() throws Exception {
        loadResource("error_invalid_header.off");
    }

    @Test(expected = OffUnsupportedException.class)
    public void testUnsupportedBinary() throws Exception {
        loadResource("error_unsupported_binary.off");
    }

    @Test(expected = OffUnsupportedException.class)
    public void testUnsupportedHeader() throws Exception {
        loadResource("error_unsupported_header.off");
    }

    @Test(expected = OffCorruptException.class)
    public void testNotANumber() throws Exception {
        loadResource("error_not_a_number.off");
    }

    @Test(expected = OffCorruptException.class)
    public void testEndOfFile() throws Exception {
        loadResource("error_eof.off");
    }

    @Test(expected = OffCorruptException.class)
    public void testMissingVertexColor() throws Exception {
        loadResource("error_missing_vertex_color.off");
    }

    @Test(expected = OffSizeException.class)
    public void testResourceTooLarge() throws Exception {
        loadResource("error_too_large.off");
    }
}
