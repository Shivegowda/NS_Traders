package com.traders.nst.exception.enums;

public enum ResponseErrorCodeEnum {
    SERVER_ERROR(1001,500,"Something went wrong, Please try again later"),
    BAD_REQUEST_ERROR(1002,400,"Bad Request"),
    UNAUTHORIZED_ERROR(1003,401,"Unauthorized"),
    INVALID_USER_CREDS(1004,401,"Invalid user credentials"),
    INVALID_ORDER_REQUEST(1005,500,"Invalid order request"),
    PRODUCT_ALREADY_EXISTS(1006,500,"Product already exists"),
    INVALID_PRODUCT_SELECTED(1007,500,"Invalid product selected");

    private final Integer errorCode;
    private final Integer statusCode;
    private final String message;
    ResponseErrorCodeEnum(Integer errorCode, Integer statusCode, String message) {
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
