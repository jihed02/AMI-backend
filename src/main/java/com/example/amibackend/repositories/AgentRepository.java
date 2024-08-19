package com.example.amibackend.repositories;

import com.example.amibackend.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
    public Optional <Agent> findAgentByEmail(String email);

    public Optional<Agent> findAgentByMatricule(int matricule);
    public void deleteAgentByMatricule(int mat);
   public long count();
}
