package com.rusanovschi.spring.springboot.config;

public enum ApplicationUserPermission {

    PERSON_READ("student:read"),
    PERSON_WRITE("student:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
