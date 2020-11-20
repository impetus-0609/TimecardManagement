package com.tcm.controller.timecardinput;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.form.timecardinput.TimecardInputForm;

@Controller
public class TimecardInputController {
	/** HTML */
	private static final String TIMECARD_INPUT_HTML = "timecard-input";
	/** data */
	private static final String TIMECARD_INPUT_DATA = "timecardInputData";

	@RequestMapping(value = "/timecard-input", method = RequestMethod.GET)
	public ModelAndView init() {
		// 初期表示用の情報を取得 ユーザ情報を基に勤怠情報を取得.
		// サービス呼び出し var DTO = service.hogehoge
		// 取得データを画面用DTOに詰め替える



		return createModelAndView(
				new TimecardInputForm());
	}

	@RequestMapping(value = "/timecard-input", method = RequestMethod.POST)
	public ModelAndView entry(TimecardInputForm form) {
		// formの値が取り出せる
		String txt = form.getTxt();

		// 入力内容をもとに

		return createModelAndView(
				new TimecardInputForm());
	}





	private ModelAndView createModelAndView(TimecardInputForm form) {
		ModelAndView r = new ModelAndView();
		r.setViewName(TIMECARD_INPUT_HTML);
		r.addObject(TIMECARD_INPUT_DATA, form);
		return r;
	}
}
