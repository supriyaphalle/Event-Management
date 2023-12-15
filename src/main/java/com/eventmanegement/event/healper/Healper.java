package com.eventmanegement.event.healper;

import com.eventmanegement.event.dto.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Healper {

    public static <U, V> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type) {
        List<U> entityList = page.getContent();
        List<V> dtoList = entityList.stream().map(object -> new ModelMapper().map(object, type)).collect(Collectors.toList());

        PageableResponse<V> response = new PageableResponse<>();
        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElement(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }
}