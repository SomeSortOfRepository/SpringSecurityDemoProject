package ru.dolinini.springsecuritydemoproject.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.PERSONS_READ)),ADMIN(Set.of(Permission.PERSONS_READ, Permission.PERSONS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(p->new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

}
