/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.model.dto.LoginDTO;
import com.br.viaex.service.util.ApiResponse;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos
 */

@Service
public class LoginServiceImpl {
    
    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<Object> login(LoginDTO dto) {

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(dto.getEmail(),
                dto.getSenha());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        Instant now = Instant.now();
        long expiry = 36000L;

        String scope = authenticationResponse.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authenticationResponse.getName())
                .claim("scope", scope)
                .claim("principal", dto.getEmail())
                .build();

        String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuarioLogado", authenticationResponse.getName());
        response.put("permissoes", authenticationResponse.getAuthorities().stream().map(GrantedAuthority::getAuthority));

        return ResponseEntity.ok(new ApiResponse<>(response));
        
    }
    
}
