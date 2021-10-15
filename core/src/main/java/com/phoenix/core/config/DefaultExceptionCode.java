package com.phoenix.core.config;

public class DefaultExceptionCode {
    //~ Nhóm lỗi liên quan đến tài khoản và bảo mật 0___
    public static final String BAD_CREDENTIALS = "0000001";
    public static final String ACCOUNT_LOCKED = "0000002";
    public static final String ACCOUNT_EXPIRE = "0000003";
    public static final String ACCESS_DENIED = "0000004";
    public static final String INVALID_REFRESH_JWT = "0000005";
    public static final String ACCOUNT_CONFLICT = "0000006";
    public static final String ACCOUNT_LENGTH_REQUIRED = "0000007";
    // ~ ---------------------------------------------------------
    // ~ Nhóm lỗi liên quan đến database 1___
    public static final String DATABASE_ERROR = "1000001";
    // ~ ---------------------------------------------------------
    // ~ Nhóm lỗi khác 2___
    public static final String BAD_REQUEST = "2000001";
    public static final String INTERNAL_ERROR = "2000002";
    public static final String NOT_FOUND = "2000003";
    public static final String METHOD_NOT_ALLOWED = "2000004";
    public static final String NOT_ACCEPTABLE = "2000005";

}
