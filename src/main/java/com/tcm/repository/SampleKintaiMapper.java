package com.tcm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
