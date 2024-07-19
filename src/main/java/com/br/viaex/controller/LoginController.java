/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.controller;

import com.br.viaex.model.dto.LoginDTO;
import com.br.viaex.service.impl.LoginServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    
    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping(value = "/token")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO dto) {
        return loginService.login(dto);
    }
    
}
