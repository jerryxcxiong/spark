package com.bosscs.spark.commons.exception;

/**
 * Created by Jerry Xiong on 22/12/15.
 */
public class ExtractorInitializationException extends RuntimeException {

    /**
     * Default constructor.
     */
    public ExtractorInitializationException() {
        super();
    }

    /**
     * Public constructor.
     */
    public ExtractorInitializationException(String message) {
        super(message);
    }

    /**
     * Public constructor.
     */
    public ExtractorInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Public constructor.
     */
    public ExtractorInitializationException(Throwable cause) {
        super(cause);
    }
}
