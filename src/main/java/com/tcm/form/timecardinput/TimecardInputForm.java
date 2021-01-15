package com.tcm.form.timecardinput;

import java.sql.Time;
import java.util.List;

import lombok.Data;

@Data
public class TimecardInputForm {
	private Time dateFrom;
	private Time dateTo;
	private String txt;

	/** 勤怠情報. */
	private List<sampleKitaiDto> kintaiDtoList;
}
