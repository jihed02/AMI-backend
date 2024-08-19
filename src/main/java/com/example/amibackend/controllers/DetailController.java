package com.example.amibackend.controllers;
import com.example.amibackend.entities.Detail;
import com.example.amibackend.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
public class DetailController {
@Autowired
    private DetailService detailService;
    @GetMapping("/get")
    public ResponseEntity<List<Detail>> getDetails(){
        List<Detail> details=this.detailService.getDetails();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
    @GetMapping("/get/{detailId}")
    public ResponseEntity<Detail> getDetails(@PathVariable Long detailId){
        Detail detail=this.detailService.getById(detailId);
        return new ResponseEntity<>(detail,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<Detail>addDetail(@RequestBody Detail detail){
        Detail dl=this.detailService.addDetail(detail);
        return new ResponseEntity<>(dl,HttpStatus.CREATED);
    }
    @PutMapping("/put/{detailId}")
    public ResponseEntity<Detail>updateDetail(@PathVariable Long detailId,@RequestBody Detail detail){
        Detail dl=this.detailService.updateDetail(detailId,detail);
        return new ResponseEntity<>(dl,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{detailId}")
    public ResponseEntity<Detail>deleteDetail(@PathVariable Long detailId){
        this.detailService.deleteDetail(detailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
