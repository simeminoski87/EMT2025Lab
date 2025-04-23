package mk.ukim.finki.emt2025.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN,ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}