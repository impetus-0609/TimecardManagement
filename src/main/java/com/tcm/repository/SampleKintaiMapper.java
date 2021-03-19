package com.tcm.repository;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tcm.dto.timecardinput.SampleKintaiSqlDto;

@Mapper
public interface SampleKintaiMapper {

	/**
	 * サンプルです.
	 * 実装時は引数やSQLをまともなやつにしてください.
	 * @return
	 */
	@Select("select * from work_day where user_id = #{id} and to_char(work_day, 'YYYYMM') = #{targetMonth}")
	List<SampleKintaiSqlDto> select(String id, String targetMonth);
	
	@Update("update"
			+" work_day"
			+" set"
			+" work_from = #{from},"
			+" work_to = #{to},"
			+" update_date = #{update}"
			+" where"
			+" work_day_id = #{id}")
	void updateWorkDay(@Param("id") String id, @Param("from") Timestamp from, 
							@Param("to") Timestamp to, @Param("update") Timestamp update);
}
