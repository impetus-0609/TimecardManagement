package com.tcm.service.input;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcm.dto.timecardinput.ApprovalDto;
import com.tcm.entity.Approval;
import com.tcm.enums.ApprovalStatus;
import com.tcm.repository.input.ApprovalRepository;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

    public void approval(ApprovalDto dto) {
        Date now = new Date();
        Approval approval =approvalRepository.findByUserIdAndYearMonth(dto);
        if (approval != null) {
            updateApproval(approval, now, dto.getStatus());
        } else {
            insertApproval(dto, now);
        }
    }

    private void updateApproval(Approval approval, Date now, ApprovalStatus status ) {
        approval.setApprovalStatusCd(status.getKey());
        approval.setUpdateDate(now);
        approvalRepository.update(approval);
    }

    private void insertApproval(ApprovalDto dto, Date now) {
        approvalRepository.insert(Approval.builder()
                .userId(dto.getUserId())
                .yearMonth(dto.getYearMonth())
                .approvalStatusCd(dto.getStatus().getKey())
                .approvalUserId(null)
                .approvalDate(now)
                .createDate(now)
                .updateDate(now)
                .build());
    }
}