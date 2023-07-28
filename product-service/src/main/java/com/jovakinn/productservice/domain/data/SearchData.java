package com.jovakinn.productservice.domain.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchData {
    private int page = 0;
    private int size = 10;
    private Object sortedObject;
}
