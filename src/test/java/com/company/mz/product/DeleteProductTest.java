package com.company.mz.product;

import com.company.mz.base.enums.Category;
import com.company.mz.dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest extends ProductBase {

    @SneakyThrows
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().vegetable())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int)(Math.random()*1000));
        Response<Product> productCreated = productService.createProduct(this.product).execute();
        assertThat(productCreated.isSuccessful(), CoreMatchers.is(true));
        id = productCreated.body().getId();
    }

    @SneakyThrows
    @Test
    void deleteProductPositiveTest() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteNonexistentProductTest() {
        Response<ResponseBody> bodyResponse = productService.deleteProduct(id).execute();
        assertThat(bodyResponse.isSuccessful(), CoreMatchers.is(true));
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), CoreMatchers.is(500));
    }

    @SneakyThrows
    @Test
    void deleteProductIdNullTest() {
        Integer nullId = null;
        Response<ResponseBody> response;
        try{
            response = productService.deleteProduct(nullId).execute();
        }catch (NullPointerException e){
            assertThat(e , CoreMatchers.instanceOf(NullPointerException.class));
        }
        Response<ResponseBody> bodyResponse = productService.deleteProduct(id).execute();
        assertThat(bodyResponse.isSuccessful(), CoreMatchers.is(true));
    }
}
