package com.tcm.controller.sample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcm.dto.sample.SampleDto;
import com.tcm.form.sample.SampleForm;


public class SampleController {


    @RequestMapping("/sample")
    public ModelAndView input(SampleForm form, ModelAndView mv) {
        mv.setViewName("sample/sample");
        mv.addObject("dto", SampleDto.builder().msg("こんちは").build());
        return mv;
    }
}
