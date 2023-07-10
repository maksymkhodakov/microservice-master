package com.jovakinn.productservice.repositories;

import com.jovakinn.productservice.domain.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
