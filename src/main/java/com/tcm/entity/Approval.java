package com.tcm.entity;



import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Approval {

    private String approvalId;
    private String userId;
    private String yearMonth;
    private String approvalStatusCd;
    private String approvalUserId;
    private Date approvalDate;
    private Date createDate;
    private Date updateDate;

}
