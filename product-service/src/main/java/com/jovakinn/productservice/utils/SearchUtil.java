package com.jovakinn.productservice.utils;

import com.jovakinn.productservice.domain.data.SearchData;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@UtilityClass
public class SearchUtil {

    public static Pageable getPageable(SearchData searchData) {
        if (Objects.isNull(searchData.getSortedObject())) {
            return PageRequest.of(searchData.getPage(), searchData.getSize());
        }
        return PageRequest.of(searchData.getPage(), searchData.getSize(), (Sort) searchData.getSortedObject());
    }
}
