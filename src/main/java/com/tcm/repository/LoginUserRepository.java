package com.tcm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.entity.Users;

@Mapper
public interface LoginUserRepository {

    @Select("select "
            + "  us.user_id as id "
            + "  , us.user_name as name "
            + "from "
            + "  users us "
            + "where "
            + "  us.user_id = #{userId} ")
    public Users findByPk(String userId);

}
