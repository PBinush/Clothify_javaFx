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
@Table(name = "customers")
public class CustomerEntity {
    @Id
    private String id;
    private String title;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    @ElementCollection
    private List<OrderEntity> orderEntityList;
}
