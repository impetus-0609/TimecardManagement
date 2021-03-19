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
            + "  user_id = #{dto.userId}"
            + "  and year_month = #{dto.yearMonth} ")
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
            + "  entity.userId "
            + "  , entity.yearMonth "
            + "  , entity.approvalStatusCd "
            + "  , entity.approvalUserId "
            + "  , entity.approvalDate "
            + "  , entity.createDate "
            + "  , entity.updateDate )" )
    public int insert(Approval entity);

    @Update("UPDATE approval "
            + "SET  "
            + "  approval_id = entity.approvalId "
            + "  , user_id = entity.userId "
            + "  , year_month = entity.yearMonth "
            + "  , approval_status_cd = entity.approvalStatusCd "
            + "  , approval_user_id = entity.approvalUserId "
            + "  , approval_date = entity.approvalDate "
            + "  , create_date = entity.createDate "
            + "  , update_date = entity.updateDate ")
    public int update(Approval entity);
}