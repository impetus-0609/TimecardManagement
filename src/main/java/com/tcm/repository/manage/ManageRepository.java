package com.tcm.repository.manage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.manage.SearchDto;
import com.tcm.dto.manage.SearchResultDto;

@Mapper
public interface ManageRepository {

    @Select("<script>  "
            + "select"
            + "  ym.year_month as year_month , "
            + "  us.user_name as user_name , "
            + "  ym.approval_status as approval_status "
            + "from "
            + "  year_month ym "
            + "  inner join users us"
            + "    on ym.user_id = us.user_id  "
            + "where"
            + "  1 = 1"
            + "  <if test=\"dto.selectYear != null and dto.selectYear != ''\">"
            + "    and CAST(EXTRACT(YEAR FROM ym.year_month)as VARCHAR) = #{dto.selectYear}"
            + "  </if>"
            + "  <if test=\"dto.selectMonth != null\">"
            + "    and CAST(EXTRACT(MONTH FROM ym.year_month)as integer) = #{dto.selectMonth}"
            + "  </if>"
            + "  <if test=\"dto.name != null and dto.name != ''\">"
            + "    and us.user_name = #{dto.name}"
            + "  </if>"
            + "  <if test=\"dto.approvalStatus != null and dto.approvalStatus != ''\">"
            + "    and ym.approval_status = #{dto.approvalStatus}"
            + "  </if>"
            + "</script>")
    public List<SearchResultDto> searchManage(@Param("dto")SearchDto dto);

    @Select("select"
            + "  app.year_month as year_month , "
            + "  us.user_name as user_name , "
            + "  app.approval_status_cd as approval_status "
            + "from"
            + "  approval app "
            + "  inner join users us"
            + "    on app.user_id = us.user_id  "
            + "where"
//            + "  <if test=\"dto.selectYear != null and dto.selectYear != ''\">"
            + "    app.year_month = #{dto.selectYearMonth}")
//            + "  </if>"
//            + "  <if test=\"dto.name != null and dto.name != ''\">"
//            + "    and us.user_name = #{dto.name}"
//            + "  </if>"
//            + "  <if test=\"dto.approvalStatus != null and dto.approvalStatus != ''\">"
//            + "    and app.approval_status_cd = #{dto.approvalStatus}")
//            + "  </if>")
    public List<SearchResultDto> searchUserList(@Param("dto")SearchDto dto);

}
