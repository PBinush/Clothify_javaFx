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
@Table(name = "orders")
@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private String orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity custId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity empId;

//    private Boolean isReterned;
    
    @ElementCollection
    private List<OrderDetailsEntity> orderList;
}
