package com.example.amibackend.controllers;


import com.example.amibackend.DTO.ReclamationDTO;
import com.example.amibackend.entities.Client;
import com.example.amibackend.entities.Reclamation;
import com.example.amibackend.repositories.ClientRepository;
import com.example.amibackend.services.ReclamationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/get")
    public ResponseEntity<List<ReclamationDTO>> getReclamations(){
        List<ReclamationDTO> reclamations=this.reclamationService.findAllWithClientDetails();
        return new ResponseEntity<>(reclamations, HttpStatus.OK);
    }
    @GetMapping("/get/{reclamationId}")
    public ResponseEntity<Reclamation> getReclamation(@PathVariable Long reclamationId){
        Reclamation reclamation=this.reclamationService.getById(reclamationId);
        return new ResponseEntity<>(reclamation,HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Reclamation>>getClientReclamations(Principal principal){
        String email=principal.getName();
        List<Reclamation>reclamations=this.reclamationService.getClietnReclamations(email);
        return new ResponseEntity<>(reclamations,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<Reclamation>addReclamation(@RequestBody Reclamation reclamation, Principal principal){
        String email= principal.getName();
        Client client=this.clientRepository.findClientByEmail(email).get();
        reclamation.setClient(client);
        Reclamation rec=this.reclamationService.addReclamation(reclamation);
        return new ResponseEntity<>(rec,HttpStatus.CREATED);
    }
    @PutMapping("/put/{reclamationId}")
    public ResponseEntity<Reclamation>updateReclamation(@PathVariable Long reclamationId,@RequestBody Reclamation reclamation){
        Reclamation rec=this.reclamationService.updateReclamation(reclamationId,reclamation);
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reclamationId}")
    public ResponseEntity<Reclamation>deleteReclamation(@PathVariable Long reclamationId){
        this.reclamationService.deleteReclamation(reclamationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/handle/{id}")
    public  ResponseEntity<Reclamation>gererReclamation(@PathVariable Long id){
        Reclamation rec=this.reclamationService.gererReclamation(id);
        return new ResponseEntity<>(rec,HttpStatus.OK);
    }

}
