package com.tcm.dto.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel implements UserDetails {

    private UserAccount userAccount;
    private Collection<GrantedAuthority> authorities;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userAccount.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO アカウントの期限きれ
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO アカウントのロック
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO 資格情報の期限切れ
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
