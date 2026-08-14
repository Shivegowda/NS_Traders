package com.traders.nst.exception;


public class NSTCustomException extends RuntimeException {
    private final Integer errorCode;

    public NSTCustomException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
