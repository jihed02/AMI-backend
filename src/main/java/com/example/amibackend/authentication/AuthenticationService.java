package com.example.amibackend.authentication;

import com.example.amibackend.config.JwtService;
import com.example.amibackend.entities.Agent;
import com.example.amibackend.entities.Client;
import com.example.amibackend.enums.Role;
import com.example.amibackend.repositories.AgentRepository;
import com.example.amibackend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerClient(ClientRegRequest request){
        var user= Client.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .telephone(request.getTelephone())
                .age(request.getAge())
                .cin(request.getCin())
                .build();
        clientRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerAgent(AgentRegRequest request){
        var user= Agent.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.AGENT)
                .matricule(request.getMatricule())
                .absence(request.getAbsence())
                .build();
        agentRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticateClient(ClientAuthReq request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = clientRepository.findClientByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Client not found with email: " + request.getEmail()));
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        } catch (Exception e) {
            log.error("Error during authentication: " + e.getMessage());
            throw e;
        }
    }

    public AuthenticationResponse authenticateAgent(AgentAuthReq request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = agentRepository.findAgentByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Agent not found with email: " + request.getEmail()));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
