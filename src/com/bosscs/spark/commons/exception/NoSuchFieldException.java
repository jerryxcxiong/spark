/*
 * Copyright 2016, Jerry Xiong, BOSSCS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bosscs.spark.commons.exception;

/**
 * Unchecked variant of {@link NoSuchFieldException}.
 *
 * @author Jerry Xiong
 */
public class NoSuchFieldException extends RuntimeException {
    private static final long serialVersionUID = 4552228341917254900L;

    /**
     * Public constructor.
     */
    public NoSuchFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Public constructor.
     */
    public NoSuchFieldException(Throwable cause) {
        super(cause);
    }

    /**
     * Public constructor.
     */
    public NoSuchFieldException(String cause) {
        super(cause);
    }

}
