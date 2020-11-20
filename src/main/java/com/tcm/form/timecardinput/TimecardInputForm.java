package com.tcm.form.timecardinput;

import java.sql.Time;

import lombok.Data;

@Data
public class TimecardInputForm {
	private Time dateFrom;
	private Time dateTo;
	private String txt;

}
