package com.example.amibackend.DTO;

import com.example.amibackend.entities.Detail;
import com.example.amibackend.enums.Categorie;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReclamationDTO {
    private Long reclamationId;
    private boolean status;
    private Categorie categorie;
    private LocalDateTime date;
    private String clientNom;
    private String clientPrenom;
    private Detail detail;
}
