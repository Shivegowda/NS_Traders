package com.traders.nst.exception.enums;

public enum ResponseEnum {
    SERVER_ERROR(1001,500,"Something went wrong, Please try again later"),
    BAD_REQUEST_ERROR(1002,400,"Bad Request"),
    UNAUTHORIZED_ERROR(1003,401,"Unauthorized"),
    INVALID_USER_CREDS(1004,401,"Invalid user credentials"),;


    private final Integer errorCode;
    private final Integer statusCode;
    private final String message;
    ResponseEnum(Integer errorCode, Integer statusCode, String message) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
    }
    public Integer getErrorCode() {
        return errorCode;
    }
    public Integer getStatusCode() {
        return statusCode;
    }
    public String getMessage() {
        return message;
    }

}
