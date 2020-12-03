package ru.dolinini.springsecuritydemoproject.model;

public enum Permission {
    PERSONS_READ("permission:read"),PERSONS_WRITE("permission:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
