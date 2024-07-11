package org.application.kingphobe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private int id;

    @NotBlank(message = "Category name must not be blank")
    private String name;

    private String image;
}
