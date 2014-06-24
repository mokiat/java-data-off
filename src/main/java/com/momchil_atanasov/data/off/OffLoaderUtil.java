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

import java.io.BufferedReader;
import java.io.IOException;

import com.momchil_atanasov.data.off.error.OffCorruptException;

class OffLoaderUtil {

    private static final char COMMENT_SYMBOL = '#';
    private static final String WHITE_SPACE_PATTERN = "[\\s]+";

    public static float parseColorSegmentSafe(String text) throws OffCorruptException {
        try {
            return Integer.parseInt(text) / 255.0f;
        } catch (NumberFormatException ex) {
            // So it's not an integer. Will try with float
            return parseFloatSafe(text);
        }
    }

    public static int parseIntSafe(String text) throws OffCorruptException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new OffCorruptException("Expected an integer but read differently.", ex);
        }
    }

    public static float parseFloatSafe(String text) throws OffCorruptException {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException ex) {
            throw new OffCorruptException("Expected a float but read differently.", ex);
        }
    }

    public static String[] readContentLineMultiple(BufferedReader reader) throws IOException {
        final String line = readContentLineSingle(reader);
        return line.split(WHITE_SPACE_PATTERN);
    }

    public static String readContentLineSingle(BufferedReader reader) throws IOException {
        String line = readCleanedUpLine(reader);
        while (line.isEmpty()) {
            line = readCleanedUpLine(reader);
        }
        return line;
    }

    private static String readCleanedUpLine(BufferedReader reader) throws IOException {
        final String result = reader.readLine();
        if (result == null) {
            throw new OffCorruptException("The file has ended unexpectedly.");
        }
        return removeCommentFromLine(result.trim());
    }

    private static String removeCommentFromLine(String line) {
        final int commentIndex = line.indexOf(COMMENT_SYMBOL);
        if (commentIndex != -1) {
            return line.substring(0, commentIndex).trim();
        }
        return line;
    }
}
