package com.tcm.controller.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcm.dto.sample.SampleSqlDto;
import com.tcm.repository.SampleMapper;
import com.tcm.repository.SampleXmlVersionMapper;

@Controller
public class LoginController {

	@Autowired SampleMapper mapper;
	@Autowired SampleXmlVersionMapper xmlVersionMapper;

	@RequestMapping("/login")
	public String login() {
		SampleSqlDto dto1 = mapper.select("1");
		SampleSqlDto dto2 = xmlVersionMapper.select("1");
		return "login"; // login.htmlを表示
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("iserror", true);
		return "login"; // login.htmlを表示
	}
}