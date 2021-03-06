package com.rusanovschi.spring.springboot.config;

public enum ApplicationUserPermission {

    PERSON_READ("person:read"),
    PERSON_WRITE("person:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
