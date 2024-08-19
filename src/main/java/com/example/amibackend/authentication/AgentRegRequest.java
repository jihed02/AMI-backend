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
public class AgentRegRequest {
    private String prenom;
    private String nom;
    private int matricule;
    private String email;
    private String password;
    private Role role;
    private int absence;

}
