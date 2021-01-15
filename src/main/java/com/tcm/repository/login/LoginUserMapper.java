package com.tcm.repository.login;

import org.apache.ibatis.annotations.Mapper;

import com.tcm.dto.login.LoginUserDto;

@Mapper
public interface LoginUserMapper {

	LoginUserDto findUser(String id);

}
