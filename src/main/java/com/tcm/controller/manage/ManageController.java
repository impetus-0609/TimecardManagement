package com.tcm.controller.manage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.login.UserModel;
import com.tcm.dto.manage.SearchDto;
import com.tcm.dto.manage.SearchResultDto;
import com.tcm.enums.Role;
import com.tcm.form.manage.SearchForm;
import com.tcm.service.manage.SearchService;

@Controller
public class ManageController {

    @Autowired
    private ManageHelper manageHelper;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/ManagementScreen")
    public ModelAndView init(@AuthenticationPrincipal UserModel userModel, SearchForm form, ModelAndView mv) throws Exception {

        Collection<? extends GrantedAuthority> authorities = userModel.getAuthorities();
        List<Role> roles = authorities.stream()
                .map(role -> Role.valueOf(role.getAuthority()))
                .collect(Collectors.toList());
        if (roles.contains(Role.ROLE_USE)) {
        	mv.setViewName("error-page");
        	return mv;
        }

        mv.addObject("searchResult",new SearchResultDto());
        mv.addObject("yearsList",manageHelper.getIntYearsList(2));
        mv.setViewName("manage/manage");
        return mv;
    }

    @RequestMapping("/ManagementScreen/search")
    public ModelAndView search(SearchForm form, ModelAndView mv) {

        SearchDto dto = manageHelper.mappingSearchDto(form);
        List<SearchResultDto> aaa = searchService.search(dto);

        mv.addObject("searchResultList",searchService.search(dto));
        mv.addObject("yearsList",manageHelper.getIntYearsList(2));
        mv.setViewName("manage/manage");
        return mv;
    }

    @RequestMapping("/error-page")
    public String error(ModelAndView mv) {
    	return "error";
    }
}