package edu.icet.entity;

import jakarta.persistence.*;
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
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String id;
    private String title;
    private String name;
    private String position;
    private String address;
    private String phoneNumber;
    private String email;

    @ElementCollection
    private List<OrderEntity> orderEntityList;
}
