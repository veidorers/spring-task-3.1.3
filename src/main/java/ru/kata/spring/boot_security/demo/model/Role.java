package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private String title;

    Role(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
