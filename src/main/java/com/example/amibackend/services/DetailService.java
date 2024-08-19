package com.example.amibackend.services;


import com.example.amibackend.entities.Detail;
import com.example.amibackend.repositories.DetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    private DetailRepository detailRepo;
    public List<Detail> getDetails(){

        return this.detailRepo.findAll();
    }
    public Detail getById(Long id){
        return this.detailRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Detail not found with id: " + id));
    }
    public Detail addDetail(Detail detail){

        return this.detailRepo.save(detail);
    }

    public void deleteDetail(Long id){
        if (this.detailRepo.existsById(id))
            this.detailRepo.deleteById(id);
        else
            throw new RuntimeException("No Detail with id"+id);

    }
    public Detail updateDetail(Long id,Detail detail){
        Detail dl=this.detailRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Detail not found with id: " + id));


        if(dl.getText()!=detail.getText()){
            dl.setText(detail.getText());

        }
        return dl;
    }

}
