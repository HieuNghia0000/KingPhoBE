package org.application.kingphobe.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private CategoryDTO category;
}
