package edu.icet.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String size;
    private Integer qty;
    private String category;
    private Double price;
    private String imgPath;

    @ElementCollection
    List<OrderDetailsEntity> orderDetailsEntityList;

    @ElementCollection
    List<SupplierEntity> productList;
}

