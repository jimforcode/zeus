package com.zeus.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AuthException
 */
public class AuthException extends RuntimeException {

    static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg, Object... args) {
        super(msg);
        logger.error(String.format(msg, args));
    }

    public AuthException(Throwable t, String msg, Object... args) {
        super(msg, t);
        logger.error(String.format(msg, args));
    }

}
