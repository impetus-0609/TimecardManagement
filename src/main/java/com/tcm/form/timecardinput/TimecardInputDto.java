package com.tcm.form.timecardinput;

import lombok.Data;

@Data
public class TimecardInputDto {
	/** work_day id */
	private String workDayId;
	/** YYYY年. */
	private String nen;
	/** MM月dd日. */
	private String hizuke;
	/** 曜日. */
	private String youbi;
	/** 午前("HH:mm). */
	private String gozen;
	/** 午後("HH:mm). */
	private String gogo;
	/** 年月日 */
	private String ymd;

	public String getHizuke() {
		return hizuke;
	}

	public void setHizuke(String hizuke) {
		this.hizuke = hizuke;
	}
}
