package com.example.amibackend.services;
import com.example.amibackend.DTO.AgentDTO;
import com.example.amibackend.entities.Agent;

import com.example.amibackend.entities.Client;
import com.example.amibackend.enums.Role;
import com.example.amibackend.repositories.AgentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Agent> getAgents(){
        return this.agentrepo.findAll();
    }
    public Agent getById(Long id){
        return this.agentrepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent not found with id: " + id));
    }
    public Agent addAgent(Agent agent){
      if (agentrepo.findAgentByEmail(agent.getEmail()).isPresent() || agentrepo.findAgentByMatricule(agent.getMatricule()).isPresent())
        {
            throw new IllegalStateException("agent already exists");
        }
        else{
          var user= Agent.builder()
                  .prenom(agent.getPrenom())
                  .nom(agent.getNom())
                  .email(agent.getEmail())
                  .password(passwordEncoder.encode(agent.getPassword()))
                  .role(Role.AGENT)
                  .absence(agent.getAbsence())
                  .telephone(agent.getTelephone())
                  .matricule(agent.getMatricule())
                  .build();
          return  this.agentrepo.save(user);

        }

    }
    @Transactional
    public void deleteAgent(int mat){
        if (this.agentrepo.findAgentByMatricule(mat).isPresent())
            this.agentrepo.deleteAgentByMatricule(mat);
        else
            throw new RuntimeException("no Agent with matricule"+mat);

    }
    public Agent updateAgent(int mat, AgentDTO agent){
        Agent ag=this.agentrepo.findAgentByMatricule(mat).orElseThrow(() -> new EntityNotFoundException("Agent not found with id: " + mat));
        if(agent.getTelephone() !=null && agent.getTelephone().length()==8 && agent.getTelephone().matches("\\d"))
            ag.setTelephone(agent.getTelephone());

        if(agent.getEmail()!=ag.getEmail()){
            ag.setEmail(agent.getEmail());

        }
        if (agent.getAbsence() >= 0) {
            ag.setAbsence(agent.getAbsence());
        }

        if (agent.getNom() != null && !agent.getNom().isEmpty()) {
            ag.setNom(agent.getNom());
        }

        if (agent.getPrenom() != null && !agent.getPrenom().isEmpty()) {
            ag.setPrenom(agent.getPrenom());
        }

        if (agent.getMatricule()>100) {
            ag.setMatricule(agent.getMatricule());
        }

        if (agent.getPassword() != null && !agent.getPassword().isEmpty()) {
            ag.setPassword(agent.getPassword());
        }

        return this.agentrepo.save(ag);
    }

    public long getAgentsNumber(){
        return this.agentrepo.count();

    }
}
