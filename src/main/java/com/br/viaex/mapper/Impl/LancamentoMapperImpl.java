/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Lancamento;
import com.br.viaex.model.dto.LancamentoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class LancamentoMapperImpl implements CustomObjectMapper<Lancamento, LancamentoDTO> {

    @Override
    public LancamentoDTO converterParaDto(Lancamento entity) {

        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setData(entity.getData());
        dto.setObservacao(entity.getObservacao());
        dto.setTipoLancamento(entity.getTipoLancamento());
        dto.setViagem(entity.getViagem());
        dto.setStatusLancamento(entity.getStatusLancamento());
        dto.setIdAprovador(entity.getIdAprovador());
        dto.setIdOperacional(entity.getIdOperacional());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Lancamento converterParaEntidade(LancamentoDTO dto) {

        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setValor(dto.getValor());
        lancamento.setData(dto.getData());
        lancamento.setObservacao(dto.getObservacao());
        lancamento.setTipoLancamento(dto.getTipoLancamento());
        lancamento.setViagem(dto.getViagem());
        lancamento.setStatusLancamento(dto.getStatusLancamento());
        lancamento.setIdAprovador(dto.getIdAprovador());
        lancamento.setIdOperacional(dto.getIdOperacional());
        lancamento.setDataCriacao(dto.getDataCriacao());
        lancamento.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return lancamento;
    }

    @Override
    public List<LancamentoDTO> converterParaListaDeDtos(List<Lancamento> entityList) {

        List<LancamentoDTO> Lista = new ArrayList<>();
        for (Lancamento entity : entityList) {
            Lista.add(converterParaDto(entity));
        }
        return Lista;
    }


}
