package com.example.amibackend.controllers;

import com.example.amibackend.entities.Alerte;
import com.example.amibackend.entities.Client;
import com.example.amibackend.repositories.ClientRepository;
import com.example.amibackend.services.AlerteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/alerte")
public class AlerteController {
    @Autowired
    private AlerteService alerteService;

    @GetMapping("/get")
    public ResponseEntity<List<Alerte>> getAlertes(){
        List<Alerte> alertes=this.alerteService.getAlertes();
        return new ResponseEntity<>(alertes, HttpStatus.OK);
    }
    @GetMapping("/get/{alerteId}")
    public ResponseEntity<Alerte> getAlertes(@PathVariable Long alerteId){
        Alerte alerte=this.alerteService.getById(alerteId);
        return new ResponseEntity<>(alerte,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<Alerte>addAlerte(@RequestBody Alerte alerte){
        Alerte al=this.alerteService.addAlerte(alerte);

        return new ResponseEntity<>(al,HttpStatus.CREATED);
    }
    @PutMapping("/put/{alerteId}")
    public ResponseEntity<Alerte>updateAlerte(@PathVariable Long alerteId,@RequestBody Alerte alerte){
        Alerte al=this.alerteService.updateAlerte(alerteId,alerte);
        return new ResponseEntity<>(al,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{alerteId}")
    public ResponseEntity<Alerte>deleteAlerte(@PathVariable Long alerteId){
        this.alerteService.deleteAlerte(alerteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
