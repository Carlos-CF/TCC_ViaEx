/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model.dto;

import com.br.viaex.model.Departamento;
import com.br.viaex.model.enumerated.Genero;
import com.br.viaex.model.enumerated.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Carlos Fernandes
 */
public class UsuarioDTO {
    
    
    private Long id;
    
    @NotNull(message = "O nome completo precisa ser informado!")
    @NotBlank(message = "O nome completo não pode estar em branco!")
    private String nomeCompleto;

   
    @NotNull(message = "A data de nascimento precisa ser informada!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(type = "string", pattern = "^\\d{4}-\\d{2}-\\d{2}", example = "2024-01-01")
    private LocalDate dataNascimento;

    
    @NotNull(message = "O CPF precisa ser informado!")
    @NotBlank
    @CPF(message = "O CPF precisa ser válido!")
    private String cpf;

    @NotNull(message = "O Email precisa ser informado!")
    @NotBlank(message = "O Email precisa ser informado!")
    @Email(message = "O Email precisa ser válido!")
    private String email;

    @NotNull(message = "A senha precisa ser informado!")
    @NotBlank(message = "A senha precisa ser informado!")
    private String senha;

    private boolean status;

    @NotNull(message = "O genero precisa ser informado!")
    @NotBlank(message = "O genero precisa ser informado!")
    private Genero genero;

    @NotNull(message = "O endereço precisa ser informado!")
    @NotBlank(message = "O endereço precisa ser informado!")
    private String endereco;

    @NotNull(message = "A cidade precisa ser informado!")
    @NotBlank(message = "A cidade precisa ser informado!")
    private String cidade;

    @NotNull(message = "O Estado precisa ser informado!")
    @NotBlank(message = "O Estado precisa ser informado!")
    private String estado;

   
    private Departamento departamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", pattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", example = "2024-01-01 12:00:00")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", pattern = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", example = "2024-01-01 12:00:00")
    private LocalDateTime ultimaAtualizacao;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
    
    
}
