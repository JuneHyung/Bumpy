package com.bump.bumpy.security.principal;

import com.bump.bumpy.database.entity.UsrMUsr;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class PrincipalDetails implements UserDetails {
    private static final String ROLE_USER = "USER";

    private UsrMUsr usrMUsr;

    public PrincipalDetails(UsrMUsr usrMUsr) {
        this.usrMUsr = usrMUsr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_USER));
        return authorities;
    }

    @Override
    public String getPassword() {
        return usrMUsr.getPassword();
    }

    @Override
    public String getUsername() {
        return usrMUsr.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usrMUsr.getUseYn();
    }
}
