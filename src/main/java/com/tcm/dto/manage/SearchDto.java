package com.tcm.dto.manage;

import java.time.Month;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchDto {

    private String selectYear;
    private Month selectMonth;
    private String name;
    private String approvalStatus;

}
