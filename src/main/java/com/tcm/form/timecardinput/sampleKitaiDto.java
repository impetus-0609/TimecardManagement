package com.tcm.form.timecardinput;

import lombok.Data;

@Data
public class sampleKitaiDto {
	/** 年月. */
	private String nen;
	/** 日付. */
	private String hizuke;
	/** 曜日. */
	private String youbi;
	/** 午前. */
	private String gozen;
	/** 午後. */
	private String gogo;

	public String getHizuke() {
		return hizuke;
	}

	public void setHizuke(String hizuke) {
		this.hizuke = hizuke;
	}

}
