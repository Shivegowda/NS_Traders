package com.traders.nst.DTO.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private Integer errorCode;
    private String message;
    private DataDTO<T> data;
    public ResponseDTO() {}
    public ResponseDTO(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
    public ResponseDTO(Integer errorCode, String message, DataDTO<T> data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
    public ResponseDTO( String message, DataDTO<T> data) {
        this.message = message;
        this.data = data;
    }
}
