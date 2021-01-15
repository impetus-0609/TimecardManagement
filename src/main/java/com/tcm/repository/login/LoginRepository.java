package com.tcm.repository.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.login.UserModel;

@Mapper
public interface LoginRepository {

    @Select("select"
            + "  us.user_id as id"
            + "  , us.user_name as name"
            + "  , pw.password  "
            + "from"
            + "  users us"
            + "  inner join password pw"
            + "    on us.user_id = pw.user_id  "
            + "where"
            + "  us.user_id = #{userId}")
    public UserModel selectByUser(String userId);

}
