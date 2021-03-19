package com.tcm.controller.timecardinput;

import org.springframework.stereotype.Component;

import com.tcm.dto.timecardinput.ApprovalDto;
import com.tcm.form.timecardinput.ApprovalForm;

@Component
public class TimecardInputHelper {

    public ApprovalDto mappingApprovalDto(ApprovalForm form) {
        return ApprovalDto.builder()
                .userId(form.getUserId())
                .yearMonth(form.getYearMonth())
                .build();
    }
}
