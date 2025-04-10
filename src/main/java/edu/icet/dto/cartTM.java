package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class cartTM {
    private String orderId;
    private String productName;
    private Integer qtyOnHand;
    private Double price;
}
