package com.tcm.dto.manage;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.tcm.enums.ApprovalStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultDto {

    private Date yearMonth;
    private String userName;
    private String approvalStatus;

    public String getYearMonthStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        return sdf.format(this.yearMonth);
    }

    public String getStatusName() {
        for (ApprovalStatus status : ApprovalStatus.values()) {
            if(status.getKey().equals(approvalStatus)) return status.getName();
        }
        throw new IllegalArgumentException("undefinedStatus : key=" + approvalStatus);
    }

}
