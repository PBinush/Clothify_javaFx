package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String id;
    private String title;
    private String name;
    private String position;
    private String address;
    private String phoneNumber;
    private String email;
}
