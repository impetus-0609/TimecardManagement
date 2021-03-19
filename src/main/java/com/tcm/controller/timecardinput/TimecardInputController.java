package com.tcm.controller.timecardinput;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.timecardinput.TimecardInputSqlDto;
import com.tcm.form.timecardinput.TimecardInputDto;
import com.tcm.form.timecardinput.TimecardInputForm;
import com.tcm.repository.TimecardInputMapper;

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
	/** モーダル処理. */
	private static final String ACTION_PATH_MODAL = "modal";

	@Autowired TimecardInputMapper mapper;

	@RequestMapping(value = ACTION_PATH_INIT, method = RequestMethod.GET)
	public ModelAndView init(
			@RequestParam(name = "yearMonth", required = false) String yearMonth,
			@RequestParam(name = "userId", required = false) String userId) throws ParseException {
		String targetYearMonth = Objects.nonNull(yearMonth) ? yearMonth : getNowYm();
		String targetUserId = Objects.nonNull(userId) ? userId : getLoginUserId();

		// TODO 2/19手塚 元々のソースでは引数のユーザID, 年月に固定値が渡されていたため改修が必要
		// TODO 2/19手塚 今回は画面表示確認のため別途作成した固定文字のメソッドを呼ぶ.
//		var form = new TimecardInputForm();
//		form.setTimecardInputDtoList(createTmpKintaiDtoList());

		TimecardInputForm form = getInitData(targetYearMonth, targetUserId);

		return createModelAndView(form);
	}

	/**
	 * 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得しformにセットする.
	 * @return 画面form
	 */
	private TimecardInputForm getInitData(String targetMonth, String id) {
		//TODO 2/19手塚 initの処理をそのままコピーしているため引数を使用していません。②の改修時に直す対象になります。

		// 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得.
		List<TimecardInputSqlDto> dtoList = mapper.select(id, targetMonth);
		// 取得データを画面用DTOに詰め替える
		var form = new TimecardInputForm();
		// 表示確認用に値詰め替え
		SimpleDateFormat year = new SimpleDateFormat("yyyy年");
		SimpleDateFormat day = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat from = new SimpleDateFormat("HH:mm");
		SimpleDateFormat to = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();

		var result = new ArrayList<TimecardInputDto>();
		for (TimecardInputSqlDto dto: dtoList) {
			var input = new TimecardInputDto();
			input.setNen(year.format(dto.getWork_day()));
			input.setHizuke(day.format(dto.getWork_day()));
			cal.setTime(dto.getWork_day());
			input.setYoubi(getWeekDay(cal));
			input.setGozen(from.format(dto.getWork_from()));
			input.setGogo(to.format(dto.getWork_to()));
			result.add(input);
		}
		form.setTimecardInputDtoList(result);

		return form;
	}

	/**
	 * 表示確認用のメソッドです.
	 * @return 勤怠情報DTOリスト
	 */
	private List<TimecardInputDto> createTmpKintaiDtoList() {
		Date d = new Date();
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy年MM月");
       String c2 = d2.format(d);
		var result = new ArrayList<TimecardInputDto>();
		var youbiList = Arrays.asList("月", "火", "水", "木", "金", "土", "日");
		var hi = 1;
		for (var week = 0; week < 4; week++) {
			for (var youbi = 0; youbi < youbiList.size(); youbi++) {
				var r = new TimecardInputDto();
				r.setNen(c2);
				r.setGozen("10:00");
				r.setGogo("20:00");
				r.setHizuke(String.valueOf(hi) + "日");
				r.setYoubi("(" + youbiList.get(youbi) + ")");
				result.add(r);
				hi++;
			}
		}
		return result;
	}

	/**
	 * ログインユーザIDを取得し返却.
	 * @return ログインユーザID
	 */
	private String getLoginUserId() {
		// TODO ログインユーザIDを返却.
		return "1";
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

	private ModelAndView createModelAndView(TimecardInputForm form) {
		ModelAndView r = new ModelAndView();
		r.setViewName(TIMECARD_INPUT_HTML);
		r.addObject(TIMECARD_INPUT_DATA, form);
		return r;
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
		Date date1 = new Date();
		SimpleDateFormat sdformat1
		= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		String nowDate = sdformat1.format(date1);
		String from = form.getModalDateFrom();
		String to = form.getModalDateTo();
		String date = form.getModalDate();
		String currentTimestampToStringFrom = "2020/01/01 " + from;
		String currentTimestampToStringTo = "2020/01/01 " + to;
		Timestamp timestampFrom = new Timestamp(toDate(currentTimestampToStringFrom,"yyyy/MM/dd HH:mm").getTime());
		Timestamp timestampTo = new Timestamp(toDate(currentTimestampToStringTo,"yyyy/MM/dd HH:mm").getTime());
		Timestamp timestampNowdate = new Timestamp(toDate(nowDate,"yyyy/MM/dd HH:mm").getTime());
		mapper.updateWorkDay("1", timestampFrom, timestampTo, timestampNowdate);
		TimecardInputForm data = getInitData("", "");
		return createModelAndView(data);
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
}
