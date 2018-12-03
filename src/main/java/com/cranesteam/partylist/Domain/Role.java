package com.cranesteam.partylist.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
