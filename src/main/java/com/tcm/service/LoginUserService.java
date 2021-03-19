package com.tcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcm.entity.Users;
import com.tcm.repository.LoginUserRepository;

@Service
public class LoginUserService {

    @Autowired
    private LoginUserRepository loginUserRepository;

    public Users findUsers(String userId) {
        List<Users> usersList = loginUserRepository.findByPk(userId);
        return usersList.get(0);
    }
}
