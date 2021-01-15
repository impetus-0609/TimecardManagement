package com.tcm.controller.timecardinput;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.timecardinput.SampleKintaiSqlDto;
import com.tcm.form.timecardinput.TimecardInputForm;
import com.tcm.form.timecardinput.sampleKitaiDto;
import com.tcm.repository.SampleKintaiMapper;

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

	@Autowired SampleKintaiMapper mapper;

	@RequestMapping(value = ACTION_PATH_INIT, method = RequestMethod.GET)
	public ModelAndView init() throws ParseException {
		// 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得.
		// サービス呼び出し var DTO = service.hogehoge
		// 取得データを画面用DTOに詰め替える
		var form = new TimecardInputForm();
		// 表示確認用に値詰め替え
		form.setKintaiDtoList(createTmpKintaiDtoList());

		// ------ ここからサービスに切り出してください -----
		// 表示対象の年月設定 実装に際して適切な形にしてください.
		String targetMonth = "202011";
		// サンプル DBから値取得 便宜上対象年月は文字列で一旦渡している
		// 取得されたリストをもとに画面へバインディングする変換処理を作成してください.
		List<SampleKintaiSqlDto> test = mapper.select("1234", targetMonth);

		// ------------------------------------------------
		return createModelAndView(form);
	}

	@RequestMapping(value = ACTION_PATH_UPDATE, method = RequestMethod.POST)
	public ModelAndView entry(TimecardInputForm form) {
		// formの値が取り出せる
		String txt = form.getTxt();

		// 入力内容をもとにサービスを呼び出す

		return new ModelAndView(ACTION_PATH_INIT);
	}



	/**
	 * 表示確認用のメソッドです.
	 * @return 勤怠情報DTOリスト
	 */
	private List<sampleKitaiDto> createTmpKintaiDtoList() {
		var result = new ArrayList<sampleKitaiDto>();
		var youbiList = Arrays.asList("月", "火", "水", "木", "金", "土", "日");
		var hi = 1;
		for (var week = 0; week < 4; week++) {
			for (var youbi = 0; youbi < youbiList.size(); youbi++) {
				var r = new sampleKitaiDto();
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

	private ModelAndView createModelAndView(TimecardInputForm form) {
		ModelAndView r = new ModelAndView();
		r.setViewName(TIMECARD_INPUT_HTML);
		r.addObject(TIMECARD_INPUT_DATA, form);
		return r;
	}
}
