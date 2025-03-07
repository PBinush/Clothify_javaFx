package edu.icet.dto;

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
    private String name;
    private String size;
    private Integer qty;
    private String category;
    private Double price;
//    private String imagePath;
}
