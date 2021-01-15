package com.tcm.dto.timecardinput;



import lombok.Builder;
import lombok.Data;

/**
 * 勤怠入力画面のDTO.
 * @author ttzzk
 *
 */
@Data
@Builder
public class TimecardInputDto {
	private String sampleStr;
	private Integer suuzi;

}
