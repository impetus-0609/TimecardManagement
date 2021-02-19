package com.tcm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.timecardinput.TimecardInputSqlDto;

@Mapper
public interface TimecardInputMapper {

	/**
	 * @return
	 */
	@Select("select * from work_day where user_id = #{id} and to_char(work_day, 'YYYYMM') = #{targetMonth}")
	List<TimecardInputSqlDto> select(String id, String targetMonth);

}
