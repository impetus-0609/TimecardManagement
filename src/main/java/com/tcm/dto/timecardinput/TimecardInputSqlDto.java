package com.tcm.dto.timecardinput;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

/**
 * 勤怠入力画面のDTO.
 * @author ttzzk
 *
 */
@Data
@Builder
public class TimecardInputSqlDto {
	/** 日ID. */
	private String work_day_id;
	/** ユーザID. */
	private String user_id;
	/** 年月日. */
	private Timestamp work_day;
	/** 出勤時間. */
	private Timestamp work_from;
	/** 退勤時間. */
	private Timestamp work_to;

	public String getWork_day_id() {
		return work_day_id;
	}

	public void setWork_day_id(String work_day_id) {
		this.work_day_id = work_day_id;
	}

}
