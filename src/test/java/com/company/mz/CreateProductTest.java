package com.company.mz;

import com.company.mz.base.enums.Category;
import com.company.mz.dto.Product;
import com.company.mz.services.ProductService;
import com.company.mz.utility.RetrofitUtil;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtil.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().vegetable())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int)(Math.random()*1000));
    }

    @SneakyThrows
    @Test
    void createProductInFoodCategoryTest() {
        Response<Product> productResponse = productService.createProduct(product)
                .execute();
        id = productResponse.body().getId();
        assertThat(productResponse.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        final Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
