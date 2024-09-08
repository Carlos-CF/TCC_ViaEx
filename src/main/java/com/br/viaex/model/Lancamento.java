/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model;

import com.br.viaex.model.enumerated.StatusLancamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
@Entity
public class Lancamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    
    @Column
    @NotNull
    private Long valor;
    
    @Column
    private LocalDate data;
    
    @Column
    private String observacao;
    
    @ManyToOne
    @JoinColumn(name = "idTipoLancamento")
    private TipoLancamento tipoLancamento;
    
    @ManyToOne
    @JoinColumn(name = "idViagem")
    private Viagem viagem;
    
    @Column
    @Enumerated(EnumType.STRING)
    private StatusLancamento statusLancamento;
    
    @ManyToOne
    @JoinColumn(name = "idAprovador")
    private Aprovador idAprovador;
    
    @ManyToOne
    @JoinColumn(name = "idOperacional")
    private Operacional idOperacional;
    
    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime ultimaAtualizacao;

    public Lancamento() {
    }

    public Lancamento(Long id) {
        this.id = id;
    }

    public Lancamento(Long id, Long valor, LocalDate data, String observacao, TipoLancamento tipoLancamento, Viagem viagem, StatusLancamento statusLancamento, Operacional idOperacional) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.observacao = observacao;
        this.tipoLancamento = tipoLancamento;
        this.viagem = viagem;
        this.statusLancamento = statusLancamento;
        this.idOperacional = idOperacional;
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoLancamento getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamento tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public StatusLancamento getStatusLancamento() {
        return statusLancamento;
    }

    public void setStatusLancamento(StatusLancamento statusLancamento) {
        this.statusLancamento = statusLancamento;
    }

    public Aprovador getIdAprovador() {
        return idAprovador;
    }

    public void setIdAprovador(Aprovador idAprovador) {
        this.idAprovador = idAprovador;
    }

    public Operacional getIdOperacional() {
        return idOperacional;
    }

    public void setIdOperacional(Operacional idOperacional) {
        this.idOperacional = idOperacional;
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
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
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
        final Lancamento other = (Lancamento) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
    
}
