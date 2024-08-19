package com.example.amibackend.repositories;

import com.example.amibackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    public Optional <Client> findClientByEmail(String email);
    public Optional <Client> findClientByCin(int cin);
    public Optional <Client> deleteClientByCin(int cin);
    public long count();


}
