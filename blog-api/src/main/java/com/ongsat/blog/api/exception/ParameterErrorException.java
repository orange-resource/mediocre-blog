package com.ongsat.blog.api.exception;

/**
 * 参数错误异常
 */
public class ParameterErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParameterErrorException() {
        super();
    }

    public ParameterErrorException(String msg) {
        super(msg);
    }

    public ParameterErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ParameterErrorException(Throwable cause) {
        super(cause);
    }
}
