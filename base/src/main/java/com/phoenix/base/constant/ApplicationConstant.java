package com.phoenix.base.constant;

public class ApplicationConstant {
    public static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";
    public static final String JWT_TOKEN_TYPE = "Bearer ";

    public static final String BASE_PACKAGE_NAME = "com.phoenix";

    public static final String PASSWORD_ENCODER_PBKDF2_ID = "pbkdf2";
    public static final String PASSWORD_ENCODER_BCRYPT_ID = "bcrypt";
    public static final String PASSWORD_ENCODER_SCRYPT_ID = "scrypt";
    public static final String PASSWORD_ENCODER_RAW_ID = "raw";
    public static final String PASSWORD_ENCODER_DEFAULT_ = PASSWORD_ENCODER_BCRYPT_ID;

    public static final String USER_DETAILS_STATUS_LOCKED = "LOCKED";
    public static final String USER_DETAILS_STATUS_ENABLED = "ENABLED";
    public static final String USER_DETAILS_STATUS_EXPIRED = "EXPIRED";
    public static final String USER_DETAILS_STATUS_DISABLED = "DISABLED";


    public static final String PARAM_KEY_PUBLIC_URLS_MATCHER = "PUBLIC_URLS_MATCHER";
    public static final String PARAM_KEY_DEFAULT_PASSWORD_SALT = "DEFAULT_PASSWORD_SALT";
    public static final String PARAM_KEY_DEFAULT_ACCOUNT_LENGTH = "DEFAULT_ACCOUNT_LENGTH";
}
