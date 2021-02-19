package com.tcm.form.timecardinput;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApprovalForm {

    private String userId;
    private String yearMonth;
}
