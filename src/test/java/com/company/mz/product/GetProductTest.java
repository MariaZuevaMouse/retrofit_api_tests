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

public class GetProductTest extends ProductBase {

    @SneakyThrows
    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().vegetable())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int)(Math.random()*1000));
        Response<Product> productCreated = productService.createProduct(this.product).execute();
        id = productCreated.body().getId();
    }

    @SneakyThrows
    @Test
    void getProductPositiveTest() {
        Response<Product> productResponse = productService.getById(id).execute();
        assertThat(productResponse.isSuccessful(), CoreMatchers.is(true));
        assertThat(productResponse.body().getId(), CoreMatchers.notNullValue());
        assertThat(productResponse.body().getId(), CoreMatchers.is(id));

//        Response<ResponseBody> response = productService.deleteProduct(id).execute();
//        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getNonexistentProductTest() {
        Response<ResponseBody>response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
//        UtilAPIOperation.removeProductById(id);
        Response<Product> productResponse = productService.getById(id).execute();
        assertThat(productResponse.code(), CoreMatchers.is(404));
    }

    @SneakyThrows
    @Test
    void getWithNullParameter() {
        Integer nullId = null;
        Response<ResponseBody> response;
        try{
            productService.getById(nullId);
        }catch (NullPointerException e){
            assertThat(e , CoreMatchers.instanceOf(NullPointerException.class));
        }
        response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

//    @SneakyThrows
//    @AfterEach
//    void tearDown() {
//        Response<ResponseBody> response = productService.deleteProduct(id).execute();
//        assertThat(response.isSuccessful(), CoreMatchers.is(true));
//
//    }
}
