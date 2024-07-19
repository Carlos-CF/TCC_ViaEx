/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model;

import com.br.viaex.model.enumerated.Genero;
import com.br.viaex.model.enumerated.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Carlos Fernandes
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoUsuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "tipoUsuario", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column
    @NotNull
    @NotBlank
    private String nomeCompleto;

    @Column
    @NotNull
    private LocalDate dataNascimento;

    @Column(unique = true)
    @NotNull
    @CPF
    private String cpf;

    @Column
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column
    @NotNull
    @NotBlank
    @JsonIgnore
    private String senha;

    @Column
    private boolean status;

    @Column
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Column
    @NotNull
    @NotBlank
    private String endereco;

    @Column
    @NotNull
    @NotBlank
    private String cidade;

    @Column
    @NotNull
    @NotBlank
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    @JsonIgnoreProperties({"nome", "status", "dataCriacao", "ultimaAtualizacao"})
    private Departamento departamento;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime ultimaAtualizacao;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, TipoUsuario tipoUsuario, String nomeCompleto, LocalDate dataNascimento, String cpf, String email, String senha, boolean status, Genero genero, String endereco, String cidade, String estado, Departamento departamento) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.status = status;
        this.genero = genero;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.departamento = departamento;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        status = true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }
    
    

}
