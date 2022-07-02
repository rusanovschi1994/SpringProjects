package com.rusanovschi.spring.springboot.config;

public enum ApplicationUserPermission {

    PERSON_READ("student:read"),
    PERSON_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
