package com.tcm.repository.input;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tcm.dto.timecardinput.ApprovalDto;
import com.tcm.entity.Approval;

@Mapper
public interface ApprovalRepository {

    @Select("SELECT  "
            + "  approval_id"
            + "  , user_id"
            + "  , year_month"
            + "  , approval_status_cd"
            + "  , approval_user_id"
            + "  , approval_date"
            + "  , create_date"
            + "  , update_date  "
            + "FROM  "
            + "  approval  "
            + "WHERE  "
            + "  user_id = #{userId}"
            + "  and year_month = #{yearMonth} ")
    public Approval findByUserIdAndYearMonth(ApprovalDto dto);

    @Insert("INSERT "
            + "INTO approval( "
            + "  , user_id"
            + "  , year_month"
            + "  , approval_status_cd"
            + "  , approval_user_id"
            + "  , approval_date"
            + "  , create_date"
            + "  , update_date  "
            + ") "
            + "VALUES ("
            + "  userId "
            + "  , yearMonth "
            + "  , approvalStatusCd "
            + "  , approvalUserId "
            + "  , approvalDate "
            + "  , createDate "
            + "  , updateDate )" )
    public int insert(Approval entity);

    @Update("UPDATE approval "
            + "SET  "
            + "  approval_status_cd = #{approvalStatusCd} "
            + "  where"
            + "  user_id = #{userId} "
            + "  and year_month = #{yearMonth}")
    public int update(Approval entity);
}