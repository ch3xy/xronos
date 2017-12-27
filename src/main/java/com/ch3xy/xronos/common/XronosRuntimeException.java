package com.ch3xy.xronos.common;

public class XronosRuntimeException extends RuntimeException {

    public XronosRuntimeException(String message) {
        super(message);
    }

    public XronosRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
