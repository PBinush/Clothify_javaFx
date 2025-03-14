package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    private String id;
    private String title;
    private String name;
    private String company;
    private String email;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
