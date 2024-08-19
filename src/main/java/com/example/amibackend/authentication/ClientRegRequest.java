package com.example.amibackend.authentication;

import com.example.amibackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegRequest {
    private String prenom;
    private String nom;
    private String email;
    private String password;
    private int cin;
    private int age;
    private String telephone;
    private Role role;
}
