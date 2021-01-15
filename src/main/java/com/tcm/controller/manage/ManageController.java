package com.tcm.controller.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.manage.SearchDto;
import com.tcm.dto.manage.SearchResultDto;
import com.tcm.form.manage.SearchForm;
import com.tcm.service.manage.SearchService;

@Controller
public class ManageController {

    @Autowired
    private ManageHelper manageHelper;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/ManagementScreen")
    public ModelAndView init(SearchForm form, ModelAndView mv) {

        mv.addObject("searchResult",new SearchResultDto());
        mv.addObject("yearsList",manageHelper.getIntYearsList(2));
        mv.setViewName("manage/manage");
        return mv;
    }

    @RequestMapping("/ManagementScreen/search")
    public ModelAndView search(SearchForm form, ModelAndView mv) {

        SearchDto dto = manageHelper.mappingSearchDto(form);

        mv.addObject("searchResultList",searchService.search(dto));
        mv.addObject("yearsList",manageHelper.getIntYearsList(2));
        mv.setViewName("manage/manage");
        return mv;
    }
}