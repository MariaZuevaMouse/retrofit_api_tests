package com.company.mz.product;

import com.company.mz.base.enums.Category;
import com.company.mz.dto.Product;
import com.company.mz.orm.db.model.Products;
import com.company.mz.orm.db.model.ProductsExample;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

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
        product.setId(id);
    }

    @SneakyThrows
    @Test
    void getProductPositiveTest() {
        Response<Product> productResponse = productService.getById(id).execute();
        assertThat(productResponse.isSuccessful(), CoreMatchers.is(true));
        assertThat(productResponse.body().getId(), CoreMatchers.notNullValue());
        assertThat(productResponse.body().getId(), CoreMatchers.is(id));

        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getNonexistentProductTest() {
        Response<ResponseBody>response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
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

    @Test
    void getProductFromDBTest() {
        ProductsExample example = new ProductsExample();
        example.createCriteria().andIdEqualTo(Long.valueOf(product.getId()));
        List<Products> products = productsMapper.selectByExample(example);
        Products testProduct = products.get(0);


        assertThat(testProduct.getId(), CoreMatchers.is(Long.valueOf(product.getId())));
        assertThat(testProduct.getTitle(), CoreMatchers.equalTo(product.getTitle()));
        assertThat(testProduct.getPrice(), CoreMatchers.is(product.getPrice()));

        int i = productsMapper.deleteByPrimaryKey(Long.valueOf(id));
        assertThat(i, CoreMatchers.is(1));

    }


}
