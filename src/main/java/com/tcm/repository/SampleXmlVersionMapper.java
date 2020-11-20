package com.tcm.repository;

import org.apache.ibatis.annotations.Mapper;

import com.tcm.dto.sample.SampleSqlDto;

@Mapper
public interface SampleXmlVersionMapper {

    SampleSqlDto select(String id);

}
