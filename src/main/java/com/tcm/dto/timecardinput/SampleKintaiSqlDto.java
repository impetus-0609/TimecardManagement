package com.tcm.dto.timecardinput;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

/**
 * サンプルです.
 * @author ttzzk
 *
 */
@Data
@Builder
public class SampleKintaiSqlDto {
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
}
