package com.company.mz.product;

import com.company.mz.dto.Product;
import com.company.mz.orm.db.dao.ProductsMapper;
import com.company.mz.services.ProductService;
import com.company.mz.utility.DbUtility;
import com.company.mz.utility.RetrofitUtil;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class ProductBase {
    static ProductService productService;
    static ProductsMapper productsMapper;
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtil.getRetrofit()
                .create(ProductService.class);
        try {
            productsMapper = DbUtility.getProductsMapper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
