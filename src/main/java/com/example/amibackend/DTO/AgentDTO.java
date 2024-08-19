package com.example.amibackend.DTO;

import lombok.Data;

@Data
public class AgentDTO {
    private int matricule;
    private String prenom;
    private String nom;
    private String email;
    private String telephone;
    private String password;
    private int absence;
}
