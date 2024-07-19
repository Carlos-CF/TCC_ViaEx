/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Carlos
 */
public class LoginDTO {
    
    @NotNull(message = "O email precisa ser informado!")
    @NotBlank(message = "O email não pode estar em branco!")
    private String email;

    @NotNull(message = "A senha precisa informada!")
    @NotBlank(message = "A senha não pode estar em branco!")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
}
