package com.github.bus.upms.model.oauth;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {

    private static final long serialVersionUID = -6058060376656180793L;

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
