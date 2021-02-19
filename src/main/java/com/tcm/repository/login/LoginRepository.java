package com.tcm.repository.login;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.login.UserAccount;
import com.tcm.enums.Role;

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
    public UserAccount selectByUser(String userId);

    @Select("select"
            + "  ar.authority_id  "
            + "from  "
            + "  authority_role ar  "
            + "where  "
            + "  ar.user_id = #{userId}  ")
    public List<Role> findAuthorityByUserId(String userId);
}
