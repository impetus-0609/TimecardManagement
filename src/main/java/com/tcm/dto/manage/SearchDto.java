package com.tcm.dto.manage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchDto {

    private String selectYearMonth;
    private String name;
    private String approvalStatus;

}
