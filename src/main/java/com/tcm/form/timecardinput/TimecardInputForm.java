package com.tcm.form.timecardinput;

import java.sql.Time;
import java.util.List;

import com.tcm.dto.timecardinput.KeyValueDto;

import lombok.Data;

@Data
public class TimecardInputForm {
    private String userName;
    private String userId;
    private Time dateFrom;
    private Time dateTo;
    private String txt;
    private String workDayId;
    private String modalDate;
    private String modalDateFrom;
    private String modalDateTo;
    private String modalWorkDayId;
    private String modalUserId;
    private String approvalStatusCd;
    private String yearMonth;

	/** 勤怠情報. */
	private List<TimecardInputDto> timecardInputDtoList;

	/** 勤怠選択用プルダウン */
	private List<KeyValueDto> selectKintaiPulldownDtoList;
}
