package com.rusanovschi.spring.springboot.config;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUsersRole {

    ADMIN(Sets.newHashSet()),
    STUDENT(Sets.newHashSet(ApplicationUserPermission.PERSON_READ,
                            ApplicationUserPermission.PERSON_WRITE,
                            ApplicationUserPermission.COURSE_READ,
                            ApplicationUserPermission.COURSE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUsersRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
