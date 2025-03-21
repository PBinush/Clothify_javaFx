package edu.icet.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @NonNull
    private String name;
    private String username;
    @NonNull
    private String email;
    private String password;
    private String role;
}
