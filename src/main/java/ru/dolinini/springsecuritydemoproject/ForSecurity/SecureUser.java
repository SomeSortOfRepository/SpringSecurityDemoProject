package ru.dolinini.springsecuritydemoproject.ForSecurity;

import lombok.Data;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dolinini.springsecuritydemoproject.model.Status;
import ru.dolinini.springsecuritydemoproject.model.User;

import java.util.Collection;
import java.util.List;

@Data
public class SecureUser implements UserDetails {

    private final String userName;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecureUser(String userName, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails userDetailsFromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getFirstname(),
                user.getPassword(),
                Status.ACTIVE.equals(user.getStatus()),
                Status.ACTIVE.equals(user.getStatus()),
                Status.ACTIVE.equals(user.getStatus()),
                Status.ACTIVE.equals(user.getStatus()),
                user.getRole().getAuthorities()
        );
    }

}
