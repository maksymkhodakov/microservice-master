package com.jovakinn.productservice.service;

import com.jovakinn.productservice.domain.data.ProductRequest;
import com.jovakinn.productservice.domain.data.ProductResponse;
import com.jovakinn.productservice.domain.data.SearchData;
import org.springframework.data.domain.Page;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    Page<ProductResponse> getAllProducts(SearchData searchData);
}
