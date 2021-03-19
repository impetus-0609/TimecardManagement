package com.tcm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tcm.entity.Users;

@Mapper
public interface LoginUserRepository {

    @Select("select "
            + "  us.user_id "
            + "  , us.user_name "
            + "  , us.last_login_date  "
            + "from "
            + "  users us "
            + "where "
            + "  us.user_id = #{userId} ")
    public List<Users> findByPk(@Param("userId")String userId);

}
