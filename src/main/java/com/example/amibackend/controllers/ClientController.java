package com.example.amibackend.controllers;

import com.example.amibackend.DTO.ClientDTO;
import com.example.amibackend.entities.Client;
import com.example.amibackend.entities.Reclamation;
import com.example.amibackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @GetMapping("/get")
    public ResponseEntity<List<Client>> getClients(){
        List<Client> Clients=this.clientService.getClients();
        return new ResponseEntity<>(Clients, HttpStatus.OK);
    }
    @GetMapping("/get/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable Long clientId){
        Client Client=this.clientService.getById(clientId);
        return new ResponseEntity<>(Client,HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Client>addClient(@RequestBody Client client){
        Client cl=this.clientService.addClient(client);
        return new ResponseEntity<>(cl,HttpStatus.CREATED);

    }
    @PutMapping("/put/{clientId}")
    public ResponseEntity<Client>updateClient(@PathVariable int clientId,@RequestBody ClientDTO client){
        Client cl=this.clientService.updateClient(clientId,client);
        return new ResponseEntity<>(cl,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<Client>deleteClient(@PathVariable int clientId){
    this.clientService.deleteClient(clientId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/reclam/{clientId}")
    public ResponseEntity<Integer>getReclamsNum(@PathVariable Long clientId){
       int num= this.clientService.getReclamsNum(clientId);
       return new ResponseEntity<>(num,HttpStatus.OK);
    }
    @GetMapping("/getNum")
    public long getClientsNumber(){
        return this.clientService.getClientsNumber();
    }

}
