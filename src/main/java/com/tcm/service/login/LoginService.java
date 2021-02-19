package com.tcm.service.login;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tcm.dto.login.UserAccount;
import com.tcm.dto.login.UserModel;
import com.tcm.enums.Role;
import com.tcm.repository.login.LoginRepository;

@Component
public class LoginService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(userId)) throw new UsernameNotFoundException("");
        UserAccount userAccount = loginRepository.selectByUser(userId);
        if (userAccount == null) throw new UsernameNotFoundException("");
        List<Role> roles = loginRepository.findAuthorityByUserId(userId);
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
        return new UserModel(userAccount,authorities,true);
    }
}
