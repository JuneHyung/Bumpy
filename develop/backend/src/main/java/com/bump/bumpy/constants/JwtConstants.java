package com.bump.bumpy.constants;

public final class JwtConstants {
    public static final long ACCESS_TOKEN_EXPIRY = 900L; // 15M
    public static final long REFRESH_TOKEN_EXPIRY = 3600L; // 1H
    private JwtConstants() {}
}
