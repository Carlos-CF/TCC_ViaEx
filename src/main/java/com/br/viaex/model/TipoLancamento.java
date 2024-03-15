/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author Carlos Fernandes
 */

@Entity
public class TipoLancamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idTipoLancamento;
    
    
    @Column
    @NotNull
    @NotBlank
    private String nomeTipoLancamento;
    
    
    @Column(insertable = false)
    @NotNull
    @NotBlank
    private boolean statusTipoLancamento;

    public TipoLancamento() {
    }

    public TipoLancamento(Long idTipoLancamento, String nomeTipoLancamento, boolean statusTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
        this.nomeTipoLancamento = nomeTipoLancamento;
        this.statusTipoLancamento = statusTipoLancamento;
    }

    public Long getIdTipoLancamento() {
        return idTipoLancamento;
    }

    public void setIdTipoLancamento(Long idTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
    }

    public String getNomeTipoLancamento() {
        return nomeTipoLancamento;
    }

    public void setNomeTipoLancamento(String nomeTipoLancamento) {
        this.nomeTipoLancamento = nomeTipoLancamento;
    }

    public boolean isStatusTipoLancamento() {
        return statusTipoLancamento;
    }

    public void setStatusTipoLancamento(boolean statusTipoLancamento) {
        this.statusTipoLancamento = statusTipoLancamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idTipoLancamento);
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
        final TipoLancamento other = (TipoLancamento) obj;
        return Objects.equals(this.idTipoLancamento, other.idTipoLancamento);
    }

    

    
}
