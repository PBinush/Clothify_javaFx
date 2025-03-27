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
public class Supplier {
    private String id;
    private String title;

    @NotNull(message = "Name is required")
    private String name;
    private String company;

    @NotNull(message = "Email is required")
    private String email;
    
    @NotNull(message = "Name is required")
    private String productId;
}
