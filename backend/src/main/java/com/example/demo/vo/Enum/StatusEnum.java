package com.example.demo.vo.Enum;

public enum StatusEnum {
    OK(200,"OK"),
    BAD_REQUEST(400,"BAD_REQUEST"),
    NOT_FOUND(404,"NOT_FOUND"),
    INTERNAL_SERVER_ERROOR(500,"INTERVAL_SERVER_ERROR"),
    UNAUTHORIZED(401,"UNAUTHORIZED");

    int statusCode;
    String code;

    StatusEnum(int statusCode ,String code){
        this.statusCode = statusCode;
        this.code=code;
    }
}
