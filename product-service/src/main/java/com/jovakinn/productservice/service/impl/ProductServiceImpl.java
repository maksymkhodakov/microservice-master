package com.jovakinn.productservice.service.impl;

import com.jovakinn.productservice.domain.data.ProductRequest;
import com.jovakinn.productservice.domain.data.ProductResponse;
import com.jovakinn.productservice.domain.data.SearchData;
import com.jovakinn.productservice.domain.entities.Product;
import com.jovakinn.productservice.repositories.ProductRepository;
import com.jovakinn.productservice.service.ProductService;
import com.jovakinn.productservice.utils.SearchUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        final Product savedProduct = productRepository.save(new Product(productRequest));
        log.info("Product saved with id: {}", savedProduct.getId());
    }

    @Override
    public Page<ProductResponse> getAllProducts(SearchData searchData) {
        return productRepository
                .findAll(SearchUtil.getPageable(searchData))
                .map(ProductResponse::new);
    }

}
