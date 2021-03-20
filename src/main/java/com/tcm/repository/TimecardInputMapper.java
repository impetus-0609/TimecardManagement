package com.tcm.repository;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tcm.dto.timecardinput.TimecardInputSqlDto;

@Mapper
public interface TimecardInputMapper {

	/**
	 * 勤怠表一覧取得
	 * @return
	 */
	@Select("select * from work_day where user_id = #{id} and to_char(work_day, 'YYYYMM') = #{targetMonth} order by work_day")
	List<TimecardInputSqlDto> select(@Param("id") String id, @Param("targetMonth") String targetMonth);

	/**
	 * 承認ステータス取得
	 * @return
	 */
	@Select("select approval_status_cd from approval where user_id = #{id} and year_month = #{year_month}")
	String getApprovalStatusCd(@Param("id") String id, @Param("year_month") String year_month);

	/**
	 * 勤怠選択プルダウン値取得
	 * @param id ユーザID
	 * @return 勤怠情報年月リスト
	 */
	@Select("SELECT to_char(work_day, 'YYYY-MM') AS Month"
			+ " FROM work_day"
			+ " WHERE user_id = #{id}"
			+ " GROUP BY to_char(work_day, 'YYYY-MM')"
			+ " ORDER BY to_char(work_day, 'YYYY-MM')")
	List<String> selectWorkDayList(@Param("id") String id);

	/**
	 * 勤怠表更新処理
	 * 対象となる1日分の勤怠を更新します。
	 * @param id
	 * @param from
	 * @param to
	 * @param update
	 */
	@Update("update"
			+" work_day"
			+" set"
			+" work_from = #{from},"
			+" work_to = #{to},"
			+" update_date = #{update}"
			+" where"
			+" work_day_id = #{id}")
	void updateWorkDay(@Param("id") String id, @Param("from") Timestamp from,
							@Param("to") Timestamp to, @Param("update") Timestamp update);

}
