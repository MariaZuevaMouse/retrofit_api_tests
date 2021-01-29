package com.company.mz.category;

import com.company.mz.base.enums.Category;
import com.company.mz.dto.GetCategoryResponse;
import com.company.mz.services.CategoryService;
import com.company.mz.utility.RetrofitUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CategoryTest {
    static CategoryService categoryService;
    @BeforeAll
    static void beforeAll() {
        categoryService = RetrofitUtil.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void getContentByIdPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();
        assertThat(response.isSuccessful(), is(true));
    }

    @SneakyThrows
    @Test
    void getContentWithResponseAssertionPositiveTest() {
        Response<GetCategoryResponse> response = categoryService.getCategory(1).execute();
        assertThat(response.isSuccessful(), is(true));
        assertThat(response.body().getId(), equalTo(Category.FOOD.id));
        assertThat(response.body().getTitle(), equalTo(Category.FOOD.title));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo(Category.FOOD.title)));
    }
}
