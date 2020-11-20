package com.tcm.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tcm.dto.sample.SampleSqlDto;

@Mapper
public interface SampleMapper {

	@Select("select authority_id, authority_name from authority where authority_id = #{id}")
    SampleSqlDto select(String id);

}
