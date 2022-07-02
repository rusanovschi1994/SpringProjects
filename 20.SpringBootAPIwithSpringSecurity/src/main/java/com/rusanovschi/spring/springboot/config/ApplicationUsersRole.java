package com.rusanovschi.spring.springboot.config;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUsersRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.PERSON_READ,
                            ApplicationUserPermission.PERSON_WRITE)),

    ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.PERSON_READ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUsersRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
