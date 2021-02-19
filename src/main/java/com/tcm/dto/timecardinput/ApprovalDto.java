package com.tcm.dto.timecardinput;

import com.tcm.enums.ApprovalStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApprovalDto {

    private String userId;
    private String yearMonth;
    private ApprovalStatus status;
}
