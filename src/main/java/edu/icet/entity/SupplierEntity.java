package edu.icet.entity;

import edu.icet.dto.Products;
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
//    @ManyToOne(optional = false)
//    private Products product;
}
