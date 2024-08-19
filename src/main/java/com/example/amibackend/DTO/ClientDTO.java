package com.example.amibackend.DTO;

import lombok.Data;

@Data
public class ClientDTO {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private int cin;
    private int age;
    private String telephone;
}
