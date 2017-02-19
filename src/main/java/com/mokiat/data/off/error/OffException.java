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

import java.io.IOException;

/**
 * This is a generic exception that indicates a problem with an OFF file or
 * model.
 * <p>
 * Possible use cases are when loading or saving an OFF model.
 *
 * @author Momchil Atanasov
 */
public class OffException extends IOException {

    private static final long serialVersionUID = 1L;

    public OffException() {
        super();
    }

    public OffException(String message) {
        super(message);
    }

    public OffException(Throwable ex) {
        super(ex);
    }

    public OffException(String message, Throwable ex) {
        super(message, ex);
    }
}
