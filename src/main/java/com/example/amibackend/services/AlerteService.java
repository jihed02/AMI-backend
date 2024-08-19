package com.example.amibackend.services;

import com.example.amibackend.entities.Alerte;
import com.example.amibackend.repositories.AlerteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlerteService {
    @Autowired
    private AlerteRepository alerteRepo;
    public List<Alerte> getAlertes(){

        return this.alerteRepo.findAll();
    }
    public Alerte getById(Long id){
        return this.alerteRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Alerte not found with id: " + id));
    }
    public Alerte addAlerte(Alerte alerte){

        return this.alerteRepo.save(alerte);
    }

    public void deleteAlerte(Long id){
        if (this.alerteRepo.existsById(id))
            this.alerteRepo.deleteById(id);
        else
            throw new RuntimeException("No Alerte with id"+id);

    }
    public Alerte updateAlerte(Long id,Alerte alerte){
        Alerte al=this.alerteRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Alerte not found with id: " + id));


        if(al.getType()!=alerte.getType()){
            al.setType(alerte.getType());

        }
        return al;
    }

}
