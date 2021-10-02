package ru.cyanide3d.bot.config;

import java.util.Arrays;

public enum  Permission {
    USER(0),
    ADMIN(1);

    private final int code;

    Permission(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Permission getPermissionByCode(int code) {
        return Arrays.stream(values()).filter(permission -> permission.getCode() == code).findFirst().orElse(USER);
    }
}
