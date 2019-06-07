package com.igoosd.util;

/**
 * 静态交通业务异常类
 */
public class RoadPricingException extends RuntimeException {

    public RoadPricingException(String message) {
        super(message);
    }

    public RoadPricingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
