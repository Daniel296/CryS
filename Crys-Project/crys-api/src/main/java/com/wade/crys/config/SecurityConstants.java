package com.wade.crys.config;

public final class SecurityConstants {

    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    public static final long EXPIRATION_TIME = 86_400_000; // 24 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_LOGIN_URL = "api/user/login";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
