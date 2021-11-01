package com.phoenix.core.config;

public class DefaultExceptionCode {
    /**
     * 000: lỗi không thuộc service nào || Lỗi xuất hiện trên nhiều service
     * 001: Lỗi của auth service
     * 002: Lỗi của user service
     */
    public static final String BAD_CREDENTIALS = "001001";
    public static final String ACCOUNT_LOCKED = "001002";
    public static final String ACCOUNT_EXPIRE = "001003";
    public static final String ACCESS_DENIED = "001004";
    public static final String INVALID_REFRESH_JWT = "001005";
    public static final String BAD_REQUEST = "000001";
    public static final String INTERNAL_ERROR = "000002";
    public static final String NOT_FOUND = "000003";
    public static final String METHOD_NOT_ALLOWED = "000004";
    public static final String NOT_ACCEPTABLE = "000005";
    public static final String DATABASE_ERROR = "000006";
}
