package com.tcm.dto.sample;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleSqlDto {
    private String authority_id;
    private String authority_name;

}
