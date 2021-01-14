package com.tcm.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcm.dto.manage.SearchDto;
import com.tcm.dto.manage.SearchResultDto;
import com.tcm.repository.manage.ManageRepository;

@Service
public class SearchService {

    @Autowired
    private ManageRepository manageRepository;

    public List<SearchResultDto> search(SearchDto dto) {
        return manageRepository.searchManage(dto);
    }

}
