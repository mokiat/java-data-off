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
package com.mokiat.data.off.error;

/**
 * This exception is used to indicate an unsupported OFF file format.
 *
 * @author Momchil Atanasov
 */
public class OffUnsupportedException extends OffException {

    private static final long serialVersionUID = 1L;

    public OffUnsupportedException() {
        super();
    }

    public OffUnsupportedException(String message) {
        super(message);
    }

    public OffUnsupportedException(Throwable ex) {
        super(ex);
    }

    public OffUnsupportedException(String message, Throwable ex) {
        super(message, ex);
    }
}
