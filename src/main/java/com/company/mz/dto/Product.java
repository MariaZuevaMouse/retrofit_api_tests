package com.company.mz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("categoryTitle")
    private String categoryTitle;

//    public Product withId(Integer id) {
//        this.id = id;
//        return this;
//    }
//
//    public Product withTitle(String title) {
//        this.title = title;
//
//        return this;
//    }
}
