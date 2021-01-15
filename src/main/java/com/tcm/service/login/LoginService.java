package com.tcm.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tcm.dto.login.UserModel;
import com.tcm.repository.login.LoginRepository;

@Component
public class LoginService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(userId)) throw new UsernameNotFoundException("");
        UserModel userModel = loginRepository.selectByUser(userId);
        if (userModel == null) throw new UsernameNotFoundException("");
        userModel.setEnabled(true);
        return userModel;
    }
}
