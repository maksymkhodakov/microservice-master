package com.jovakinn.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jovakinn.productservice.domain.data.ProductRequest;
import com.jovakinn.productservice.domain.data.SearchData;
import com.jovakinn.productservice.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ProductServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductService productService;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

    }

    @Test
    void shouldCreateProduct() throws Exception {
        final String content = getContent();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
        Assertions.assertEquals(2, productService.getAllProducts(new SearchData()).getTotalElements());
    }

    private String getContent() throws JsonProcessingException {
        final ProductRequest request = getProductRequest();
        return mapper.writeValueAsString(request);
    }

    @Test
    void shouldGetProduct() throws Exception {
        final ProductRequest request = getProductRequest();
        final String content = mapper.writeValueAsString(new SearchData());
        productService.createProduct(request);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(content))
                .andExpect(status().isOk());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("A")
                .description("B")
                .price(BigDecimal.valueOf(777))
                .build();
    }

}
