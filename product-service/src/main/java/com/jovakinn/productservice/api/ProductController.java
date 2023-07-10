package com.jovakinn.productservice.api;

import com.jovakinn.productservice.domain.data.ProductRequest;
import com.jovakinn.productservice.domain.data.ProductResponse;
import com.jovakinn.productservice.domain.data.SearchData;
import com.jovakinn.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ProductResponse>> allProducts(SearchData searchData) {
        return ResponseEntity.ok(productService.getAllProducts(searchData));
    }
}
