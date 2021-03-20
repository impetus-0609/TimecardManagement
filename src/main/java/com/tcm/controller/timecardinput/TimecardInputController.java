package com.tcm.controller.timecardinput;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.login.UserAccount;
import com.tcm.dto.login.UserModel;
import com.tcm.dto.timecardinput.ApprovalDto;
import com.tcm.dto.timecardinput.KeyValueDto;
import com.tcm.dto.timecardinput.TimecardInputSqlDto;
import com.tcm.entity.Users;
import com.tcm.enums.Role;
import com.tcm.form.timecardinput.ApprovalForm;
import com.tcm.form.timecardinput.TimecardInputDto;
import com.tcm.form.timecardinput.TimecardInputForm;
import com.tcm.repository.TimecardInputMapper;
import com.tcm.service.LoginUserService;
import com.tcm.service.input.ApprovalService;

import lombok.var;

@Controller
@RequestMapping("/timecard-input")
public class TimecardInputController {
	/** HTML */
	private static final String TIMECARD_INPUT_HTML = "timecard-input";
	/** data */
	private static final String TIMECARD_INPUT_DATA = "timecardInputData";
	/** 初期表示. */
	private static final String ACTION_PATH_INIT = "init";
	/** 更新処理. */
	private static final String ACTION_PATH_UPDATE = "update";
	/** 承認処理. */
    private static final String ACTION_PATH_APPROVAL = "approval";
	/** モーダル処理. */
	private static final String ACTION_PATH_MODAL = "modal";

	@Autowired TimecardInputMapper mapper;

	@Autowired
	ApprovalService approvalService;

   @Autowired
    LoginUserService loginUserService;

	@Autowired
	TimecardInputHelper timecardInputHelper;

	/**
	 * 初期表示処理
	 * ・ログイン画面から遷移時
	 * 　ログインユーザID,現在年月から勤怠データを取得し表示.
	 * ・管理画面から遷移時
	 * 　指定されたユーザID,指定年月から勤怠データを取得し表示
	 * @param yearMonth 年月
	 * @param userId ユーザID
	 * @return 画面
	 * @throws ParseException
	 */
	@RequestMapping(value = ACTION_PATH_INIT, method = RequestMethod.GET)
	public ModelAndView init(
			@RequestParam(name = "yearMonth", required = false) String yearMonth,
			@RequestParam(name = "userId", required = false) String userId,
			@AuthenticationPrincipal UserModel userModel) throws ParseException {
	    if (userModel == null) {
	        throw new NullPointerException("不正なログイン情報");
	    }
	    UserAccount userAccount = userModel.getUserAccount();


		String targetYearMonth = Objects.nonNull(yearMonth) ? yearMonth : getNowYm();
		String targetUserId = Objects.nonNull(userId) ? userId : userAccount.getId();

		Collection<? extends GrantedAuthority> authorities = userModel.getAuthorities();
		List<Role> roles = authorities.stream()
				.map(role -> Role.valueOf(role.getAuthority()))
				.collect(Collectors.toList());
		if (roles.contains(Role.ROLE_USE)) {
			if (!targetUserId.equals(userAccount.getId())) {
				ModelAndView r = new ModelAndView();
				r.setViewName("error-page");
				return r;
			}
		}

		TimecardInputForm form = getInitData(targetYearMonth, targetUserId);

		return createModelAndView(form);
	}

	/**
	 * 承認処理
	 * @param yearMonth
	 * @param userId
	 * @return
	 * @throws ParseException
	 */
    @PostMapping(ACTION_PATH_APPROVAL)
    public ModelAndView approval(ApprovalForm form, ModelAndView mv) {
        ApprovalDto dto = timecardInputHelper.mappingApprovalDto(form);
        approvalService.approval(dto);
		String param = "?yearMonth=";
		param += form.getYearMonth();
		param += "&userId=";
		param += form.getUserId();
		return new ModelAndView("redirect:" + ACTION_PATH_INIT + param);
    }

	/**
	 * 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得しformにセットする.
	 * @return 画面form
	 */
	private TimecardInputForm getInitData(String targetMonth, String id) {

		// 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得.
		List<TimecardInputSqlDto> dtoList = mapper.select(id, targetMonth);
		// 取得データを画面用DTOに詰め替える
		var form = new TimecardInputForm();
		// 表示確認用に値詰め替え
		SimpleDateFormat year = new SimpleDateFormat("yyyy年");
		SimpleDateFormat day = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat from = new SimpleDateFormat("HH:mm");
		SimpleDateFormat to = new SimpleDateFormat("HH:mm");
		SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		var result = new ArrayList<TimecardInputDto>();
		for (TimecardInputSqlDto dto: dtoList) {
			var input = new TimecardInputDto();
			input.setWorkDayId(dto.getWork_day_id());
			input.setNen(year.format(dto.getWork_day()));
			input.setHizuke(day.format(dto.getWork_day()));
			cal.setTime(dto.getWork_day());
			input.setYoubi(getWeekDay(cal));
			input.setGozen(from.format(dto.getWork_from()));
			input.setGogo(to.format(dto.getWork_to()));
			input.setYmd(yyyymmdd.format(dto.getWork_day()));
			result.add(input);
		}
		form.setTimecardInputDtoList(result);

		// 承認ステータスコード取得
		String cd = mapper.getApprovalStatusCd(id, targetMonth);
		form.setApprovalStatusCd(cd);

		// 対象年月取得
		form.setYearMonth(targetMonth);

		// 勤怠表選択プルダウンの設定
		List<KeyValueDto> selectKintaiPulldownDtoList = new ArrayList<KeyValueDto>();
		List<String> kintaiList = mapper.selectWorkDayList(id);
		for (String month : kintaiList) {
			KeyValueDto dto = new KeyValueDto();
			dto.setKey(month.replace("-", ""));
			dto.setValue(month);
			selectKintaiPulldownDtoList.add(dto);
		}
		form.setSelectKintaiPulldownDtoList(selectKintaiPulldownDtoList);
		// 3/19追加
		Users users = loginUserService.findUsers(id);
		form.setUserName(users.getUserName());
		form.setUserId(users.getUserId());

		return form;
	}

	/**
	 * 現在年月を返却.
	 * @return 年月
	 */
	private String getNowYm() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		return dateFormat.format(Calendar.getInstance().getTime());
	}

	/**
	 * 日付をもとに曜日を返却.
	 * @param cal 対象日付
	 * @return 曜日文字列
	 */
	private String getWeekDay(Calendar cal) {
		String weekDay[] = {"(日)","(月)","(火)","(水)","(木)","(金)","(土)"};
		return weekDay[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}


	@RequestMapping("/timecard-input/modal")
	public String modal() {
		System.out.print("OK");
		return "timecard-input"; // timecard-input.htmlを表示
	}

	/**
	 * 勤怠情報を更新
	 */
	@RequestMapping(value = ACTION_PATH_MODAL, method = RequestMethod.POST)
	public ModelAndView modal(TimecardInputForm form) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDt = format.format(new Date());
		// YYYY-MM-DD
		String targetDateStr = form.getModalDate();
		// HH:MM
		String dateFrom = form.getModalDateFrom();
		String dateTo = form.getModalDateTo();
		// 更新対象PK
		String targetId = form.getModalWorkDayId();

		// 更新対象データの整形
		Timestamp updateFrom = formatTimestamp(targetDateStr + " " + dateFrom);
		Timestamp updateTo = formatTimestamp(targetDateStr + " " + dateTo);
		Timestamp updateDate = formatTimestamp(nowDt);

		mapper.updateWorkDay(targetId, updateFrom, updateTo, updateDate);
		String param = "?yearMonth=";
		param += targetDateStr.replace("-", "").substring(0,6);
		param += "&userId=";
		param += form.getModalUserId();
		return new ModelAndView("redirect:" + ACTION_PATH_INIT + param);
	}

	/**
	 * 引数の文字列をタイムスタンプ型で返却する.
	 * @param target 対象文字列
	 * @return Timestamp
	 */
	private Timestamp formatTimestamp(String target) {
		return new Timestamp(toDate(target,"yyyy-MM-dd HH:mm").getTime());
	}

	/**
	 * String型の日付を指定のdate型に変更
	 */
	public static Date toDate(String value, String format) {
		  SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		  dateFormat.setLenient(false);// 日付や時刻を厳密にチェックする
		  try {
		    return dateFormat.parse(value);
		  } catch ( ParseException e ) {
		    return null;
		  } finally {
		    dateFormat = null;
		  }
	}

	private ModelAndView createModelAndView(TimecardInputForm form) {
		ModelAndView r = new ModelAndView();
		r.setViewName(TIMECARD_INPUT_HTML);
		r.addObject(TIMECARD_INPUT_DATA, form);
		return r;
	}
}
