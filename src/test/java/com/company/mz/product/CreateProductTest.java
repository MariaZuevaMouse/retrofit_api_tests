package com.company.mz.product;

import com.company.mz.base.enums.Category;
import com.company.mz.dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProductTest extends ProductBase {

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
        deleteProduct();
    }

    @SneakyThrows
    @Test
    void creteProductWithAlreadyExistId() {
        product.setId(1);
        Response<Product> productResponse = productService.createProduct(product)
                .execute();
        assertThat(productResponse.code(), CoreMatchers.is(400));
    }

    @SneakyThrows
    @Test
    @DisplayName(value = "Create product with nonesxistent category: change error code required from 500 to 400 range")
    void creteProductNonexistentCategory() {
        product.setCategoryTitle("1");
        Response<Product> productResponse = productService.createProduct(product)
                .execute();
        assertThat(productResponse.code(), CoreMatchers.is(500));
    }

    @SneakyThrows
    @Test
    void creteProductWithNonFilledInFieldsTest() {
        product = new Product();
        Response<Product> productResponse = productService.createProduct(product).execute();
        assertThat(productResponse.code(), CoreMatchers.is(500));
    }

    @SneakyThrows
    void deleteProduct() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
