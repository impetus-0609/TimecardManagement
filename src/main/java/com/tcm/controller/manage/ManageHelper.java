package com.tcm.controller.manage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tcm.dto.manage.SearchDto;
import com.tcm.form.manage.SearchForm;

@Component
public class ManageHelper {

    public List<Integer> getIntYearsList(int loopCount) {
        Calendar calendar = Calendar.getInstance();
        List<Integer> retList = new ArrayList<>();
        retList.add(calendar.get(Calendar.YEAR));
        for (int i = 0 ; i < loopCount ; i++) {
            calendar.add(Calendar.YEAR, -1);
            retList.add(calendar.get(Calendar.YEAR));
        }
        return retList;
    }

    public SearchDto mappingSearchDto(SearchForm form) {
        return SearchDto.builder()
//                .selectYearMonth(form.getSelectYear() + form.getSelectMonth())
                .selectYearMonth(form.getSelectYear() + "03")
                .name(form.getName())
                .approvalStatus(form.getApprovalStatus()).build();
    }
}
