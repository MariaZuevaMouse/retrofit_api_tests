package com.company.mz.product;

import com.company.mz.dto.Product;
import com.company.mz.services.ProductService;
import com.company.mz.utility.RetrofitUtil;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

public class ProductBase {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtil.getRetrofit()
                .create(ProductService.class);
    }
}
