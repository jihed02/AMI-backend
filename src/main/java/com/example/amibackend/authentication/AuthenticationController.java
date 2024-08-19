package com.example.amibackend.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/registerClient")
    public ResponseEntity<AuthenticationResponse> registerClient(@RequestBody ClientRegRequest request){
        return ResponseEntity.ok(service.registerClient(request));
    }
    @PostMapping("/registerAgent")
    public ResponseEntity<AuthenticationResponse> registerAgent(@RequestBody AgentRegRequest request){
        return ResponseEntity.ok(service.registerAgent(request));
    }
    @PostMapping("/authenticateClient")
    public ResponseEntity<AuthenticationResponse>authenticateClient(@RequestBody ClientAuthReq request) throws Exception {
        return ResponseEntity.ok(service.authenticateClient(request));

    }
    @PostMapping("/authenticateAgent")
    public ResponseEntity<AuthenticationResponse>authenticateAgent(@RequestBody AgentAuthReq request){
        return ResponseEntity.ok(service.authenticateAgent(request));

    }
}
