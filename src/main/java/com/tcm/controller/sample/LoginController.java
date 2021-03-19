package com.tcm.controller.sample;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.login.UserModel;
import com.tcm.enums.Role;
import com.tcm.repository.SampleMapper;
import com.tcm.repository.SampleXmlVersionMapper;

@Controller
public class LoginController {

	@Autowired SampleMapper mapper;
	@Autowired SampleXmlVersionMapper xmlVersionMapper;

    @RequestMapping ("/login_success")
    public String success(@AuthenticationPrincipal UserModel userModel, ModelAndView mv) {
        Collection<? extends GrantedAuthority> authorities = userModel.getAuthorities();
        List<Role> roles = authorities.stream()
                .map(role -> Role.valueOf(role.getAuthority()))
                .collect(Collectors.toList());
        if (roles.contains(Role.ROLE_USER)) {
            return "redirect:/timecard-input/init";
        }
        return "redirect:/ManagementScreen";
    }

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("iserror", true);
		return "login"; // login.htmlを表示
	}
}