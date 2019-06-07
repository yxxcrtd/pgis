package com.igoosd.exception;

/**
 * 静态交通业务异常类
 */
public class StaticException extends RuntimeException {

    public StaticException(String message) {
        super(message);
    }

    public StaticException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
