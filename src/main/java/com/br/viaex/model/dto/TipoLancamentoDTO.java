/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Carlos Fernandes
 */
public class TipoLancamentoDTO {
    
    private Long idTipoLancamento;
    
    @NotNull(message = "O nome completo precisa ser informado!")
    @NotBlank(message = "O nome completo não pode estar em branco!")
    private String nomeTipoLancamento;
    
    @NotNull(message = "O nome completo precisa ser informado!")
    @NotBlank(message = "O nome completo não pode estar em branco!")
    private boolean statusTipoLancamento;

    public TipoLancamentoDTO (){
    }

    public TipoLancamentoDTO(Long idTipoLancamento, String nomeTipoLancamento, boolean statusTipoLancamento) {
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

    
}
