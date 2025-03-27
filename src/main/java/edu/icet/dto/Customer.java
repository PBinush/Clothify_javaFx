package edu.icet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String id;

    @NotNull(message = "Name is required")
    private String title;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Name is required")
    private String address;

    @NotNull(message = "Name is required")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;
}
