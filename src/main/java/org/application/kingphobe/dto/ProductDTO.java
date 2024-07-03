package org.application.kingphobe.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private CategoryDTO category;
    private List<String> imageUrls;
}
