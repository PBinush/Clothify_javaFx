package edu.icet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Name is required")
    private String size;

    @NotNull(message = "Name is required")
    private Integer qty;

    @NotNull(message = "Name is required")
    private String category;

    @NotNull(message = "Name is required")
    private Double price;
    private String imgPath;
}
