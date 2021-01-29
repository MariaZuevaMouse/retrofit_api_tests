package com.company.mz.product;

import com.company.mz.dto.Product;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductsInfoTest extends ProductBase {

    @SneakyThrows
    @Test
    void getAllProducts() {
        Response<List<Product>> products = productService.getAllProducts().execute();
        assertThat(products.isSuccessful(), CoreMatchers.is(true));
    }
}
