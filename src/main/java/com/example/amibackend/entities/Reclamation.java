package com.example.amibackend.entities;

import com.example.amibackend.enums.Categorie;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReclamationId;
    private boolean status;
    private Categorie categorie;
    private LocalDateTime date;
    @ManyToOne(fetch =FetchType.EAGER )
    @JsonBackReference
    private Client client;
    @OneToOne(cascade = CascadeType.ALL)
    private Detail detail;
}
