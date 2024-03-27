/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.TipoLancamento;
import com.br.viaex.model.dto.TipoLancamentoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos Fernandes
 */

@Component
public class TipoLancamentoMapperImpl implements CustomObjectMapper<TipoLancamento, TipoLancamentoDTO>{

    @Override
    public TipoLancamentoDTO converterParaDto(TipoLancamento entity) {
       
        TipoLancamentoDTO dto = new TipoLancamentoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public TipoLancamento converterParaEntidade(TipoLancamentoDTO dto) {
        
        TipoLancamento tipoLancamento = new TipoLancamento();
        tipoLancamento.setId(dto.getId());
        tipoLancamento.setNome(dto.getNome());
        tipoLancamento.setStatus(dto.isStatus());
        tipoLancamento.setDataCriacao(dto.getDataCriacao());
        tipoLancamento.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return tipoLancamento;
    }

    @Override
    public List<TipoLancamentoDTO> converterParaListaDeDtos(List<TipoLancamento> entityList) {
        
        List<TipoLancamentoDTO> lista = new ArrayList<>();
        for(TipoLancamento entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
    
}
