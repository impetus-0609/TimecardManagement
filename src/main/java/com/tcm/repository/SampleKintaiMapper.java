package com.tcm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.timecardinput.SampleKintaiSqlDto;

@Mapper
public interface SampleKintaiMapper {

	/**
	 * サンプルです.
	 * @return
	 */
	@Select("select * from work_day")
	SampleKintaiSqlDto select();
}
