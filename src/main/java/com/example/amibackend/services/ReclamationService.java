package com.example.amibackend.services;

import com.example.amibackend.DTO.ReclamationDTO;
import com.example.amibackend.entities.Client;
import com.example.amibackend.entities.Reclamation;
import com.example.amibackend.enums.Status;
import com.example.amibackend.repositories.ClientRepository;
import com.example.amibackend.repositories.ReclamationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepo;
    @Autowired
    private ClientRepository clientRepository;

    public List<Reclamation> getReclamations(){

        return this.reclamationRepo.findAll();
    }
    public List<ReclamationDTO> findAllWithClientDetails() {
        List<Reclamation> reclamations = reclamationRepo.findAllWithClientDetails();

        return reclamations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ReclamationDTO convertToDTO(Reclamation reclamation) {
        ReclamationDTO dto = new ReclamationDTO();
        dto.setReclamationId(reclamation.getReclamationId());
        dto.setStatus(reclamation.isStatus());
        dto.setCategorie(reclamation.getCategorie());
        dto.setDate(reclamation.getDate());
        dto.setDetail(reclamation.getDetail());
        if (reclamation.getClient() != null) {
            dto.setClientNom(reclamation.getClient().getNom());
            dto.setClientPrenom(reclamation.getClient().getPrenom());
        }
        return dto;
    }

    public Reclamation getById(Long id){
        return this.reclamationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + id));
    }
    public List<Reclamation>getClietnReclamations(String email){
        Client client=this.clientRepository.findClientByEmail(email).get();
        return client.getReclamations();
    }
    public Reclamation addReclamation(Reclamation reclam){
        var reclamation=Reclamation.builder().categorie(reclam.getCategorie())
                .detail(reclam.getDetail())
                .client(reclam.getClient())
                .status(false)
                .date(LocalDateTime.now())
                .build();
            return this.reclamationRepo.save(reclamation);
        }

    public void deleteReclamation(Long id){
        if (this.reclamationRepo.existsById(id))
            this.reclamationRepo.deleteById(id);
        else
            throw new RuntimeException("no Reclamation with id"+id);

    }
    public Reclamation updateReclamation(Long id,Reclamation reclamation){
        Reclamation rec=this.reclamationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + id));


        return rec;
    }
    public Reclamation gererReclamation(Long id){
        Reclamation rec=this.reclamationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Reclamation not found with id: " + id));
        rec.setStatus(true);
        return this.reclamationRepo.save(rec);
        }
}
