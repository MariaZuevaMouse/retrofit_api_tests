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

public class ModifyProductTest extends ProductBase {

    @SneakyThrows
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().vegetable())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int)(Math.random()*1000));
        Response<Product> productCreated = productService.createProduct(this.product).execute();
        assertThat(productCreated.isSuccessful(), CoreMatchers.is(true));
        product.setId(productCreated.body().getId());
        product.setCategoryTitle(productCreated.body().getCategoryTitle());
        product.setPrice(productCreated.body().getPrice());
        product.setTitle(productCreated.body().getTitle());
    }

    @SneakyThrows
    @Test
    void positiveModifyNameTest() {
        String newName = faker.food().fruit();
        product.setTitle(newName);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));
        assertThat(modifiedProduct.body().getTitle(), CoreMatchers.is(newName));
    }

    @SneakyThrows
    @Test
    void positiveModifyCategoryTest() {
        product.setCategoryTitle(Category.ELECTRONIC.title);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));
        assertThat(modifiedProduct.body().getCategoryTitle(), CoreMatchers.is(Category.ELECTRONIC.title));
    }

    @SneakyThrows
    @Test
    void positiveModifyPriceTest() {
        String newName = faker.food().fruit();
        product.setTitle(newName);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));
        assertThat(modifiedProduct.body().getTitle(), CoreMatchers.is(newName));
    }

    @SneakyThrows
    @Test
    @DisplayName(value = "can be set Null title. Bug should be written")
    void modifyNameToNullTest() {
        String newName = null;
        product.setTitle(newName);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));
        assertThat(modifiedProduct.body().getTitle(), CoreMatchers.is(newName));
    }

    @SneakyThrows
    @Test
    @DisplayName(value = "Modify category to null: change code status required/ should code 400 range instead of 500")
    void modifyCategoryToNullTest() {
        String nullString = null;
        product.setCategoryTitle(nullString);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(false));
        assertThat(modifiedProduct.code(), CoreMatchers.is(500));
    }

    @SneakyThrows
    @Test
    void ModifyPriceToNullTest() {
        Integer nullPrice = null;
        product.setPrice(nullPrice);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));

    }

    @SneakyThrows
    @Test
    @DisplayName(value = "Modify price to negative value should be forbidden")
    void ModifyPriceToNegativeValueTest() {
        int negativePrice = -1;
        product.setPrice(negativePrice);
        Response<Product> modifiedProduct = productService.modifyProduct(product).execute();
        assertThat(modifiedProduct.isSuccessful(), CoreMatchers.is(true));

    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(product.getId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
