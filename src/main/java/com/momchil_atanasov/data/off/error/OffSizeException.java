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
package com.momchil_atanasov.data.off.error;

/**
 * This exception is used to indicate an OFF file or model that is too large to
 * be loaded.
 *
 * @author Momchil Atanasov
 */
public class OffSizeException extends OffException {

    private static final long serialVersionUID = 1L;

    public OffSizeException() {
        super();
    }

    public OffSizeException(String message) {
        super(message);
    }

    public OffSizeException(Throwable ex) {
        super(ex);
    }

    public OffSizeException(String message, Throwable ex) {
        super(message, ex);
    }
}
