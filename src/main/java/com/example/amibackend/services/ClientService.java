package com.example.amibackend.services;

import com.example.amibackend.DTO.ClientDTO;
import com.example.amibackend.entities.Client;
import com.example.amibackend.enums.Role;
import com.example.amibackend.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Client> getClients(){
        return this.clientrepo.findAll();
    }
    public Client getById(Long id){
       return this.clientrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + id));
    }
    public Client addClient(Client client){
        if(clientrepo.findClientByEmail(client.getEmail()).isPresent())
        {
            throw new IllegalStateException("email already exists");
        }
        else{
           var user=Client.builder()
                   .nom(client.getNom())
                   .prenom(client.getPrenom())
                   .cin(client.getCin())
                   .role(Role.CLIENT)
                   .email(client.getEmail())
                   .password(passwordEncoder.encode(client.getPassword()))
                   .adresse(client.getAdresse())
                   .age(client.getAge())
                   .telephone(client.getTelephone())
                   .reclamations(client.getReclamations())
                   .build();
           return this.clientrepo.save(user);
        }

    }
    @Transactional
    public void deleteClient(int cin){
        if (this.clientrepo.findClientByCin(cin).isPresent())
            this.clientrepo.deleteClientByCin(cin);
        else
            throw new RuntimeException("no Client with id "+cin);

        }
        @Transactional
    public Client updateClient( int cin, ClientDTO client){
        Client cl=this.clientrepo.findClientByCin(cin).orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + cin));
        if(client.getTelephone() !=null && client.getTelephone().length()==8 && client.getTelephone().matches("\\d"))
            cl.setTelephone(client.getTelephone());

        if(client.getAdresse()!=null){
            cl.setAdresse(client.getAdresse());

        }
        if(client.getAge()!=cl.getAge()){
            cl.setAge(client.getAge());

        }
        if(client.getEmail()!=cl.getEmail()){
            cl.setEmail(client.getEmail());

        }
        return this.clientrepo.save(cl);
    }
    public int getReclamsNum(Long id){
        Optional<Client> client=this.clientrepo.findById(id);
         return client.get().getReclamations().size();
    }
    public long getClientsNumber(){
        return this.clientrepo.count();
    }
}
