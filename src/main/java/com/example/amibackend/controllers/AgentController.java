package com.example.amibackend.controllers;
import com.example.amibackend.DTO.AgentDTO;
import com.example.amibackend.entities.Agent;
import com.example.amibackend.services.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent")

public class AgentController {
    @Autowired
    private AgentService agentService;
    @GetMapping("/get")
    public ResponseEntity<List<Agent>> getAgents(){
        List<Agent> agents=this.agentService.getAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }
    @GetMapping("/get/{agentId}")
    public ResponseEntity<Agent> getAgent(@PathVariable Long agentId){
        Agent agent=this.agentService.getById(agentId);
        return new ResponseEntity<>(agent,HttpStatus.OK);
    }
    @PostMapping("/post")
    public ResponseEntity<Agent>addAgent(@RequestBody Agent agent){
        Agent ag=this.agentService.addAgent(agent);
        return new ResponseEntity<>(ag,HttpStatus.CREATED);
    }
    @PutMapping("/put/{agentId}")
    public ResponseEntity<Agent>updateAgent(@PathVariable int agentId,@RequestBody AgentDTO agent){
        Agent ag=this.agentService.updateAgent(agentId,agent);
        return new ResponseEntity<>(ag,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{agentId}")
    public ResponseEntity<Agent>deleteAgent(@PathVariable int agentId){
        this.agentService.deleteAgent(agentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getNum")
    public long getAgentNumber(){
        return this.agentService.getAgentsNumber();
    }
}
