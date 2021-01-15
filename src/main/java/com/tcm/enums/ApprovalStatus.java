package com.tcm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovalStatus {

    Unapproved("00","未承認"),
    Approval("01","承認"),
    ;

    private final String key;
    private final String name;

}
