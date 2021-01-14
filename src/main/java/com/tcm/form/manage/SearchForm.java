package com.tcm.form.manage;

import java.time.Month;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForm {

    private String selectYear;
    private Month selectMonth;
    private String name;
    private String approvalStatus;

}
