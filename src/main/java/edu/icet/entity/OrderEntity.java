package edu.icet.entity;

import edu.icet.dto.OrderDetails;
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
@Table(name = "order")
@Entity
public class OrderEntity {
    @Id
    private String id;
    private String orderDate;
    private String custId;
    private String empId;
    private List<OrderDetails> orderList;
}
