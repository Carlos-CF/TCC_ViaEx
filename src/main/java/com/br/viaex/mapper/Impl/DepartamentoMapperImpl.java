/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Departamento;
import com.br.viaex.model.dto.DepartamentoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos Fernandes
 */
@Component
public class DepartamentoMapperImpl implements CustomObjectMapper<Departamento, DepartamentoDTO> {

    @Override
    public DepartamentoDTO converterParaDto(Departamento entity) {

        DepartamentoDTO dto = new DepartamentoDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Departamento converterParaEntidade(DepartamentoDTO dto) {

        Departamento departamento = new Departamento();

        departamento.setId(dto.getId());
        departamento.setNome(dto.getNome());
        departamento.setStatus(dto.isStatus());
        departamento.setDataCriacao(dto.getDataCriacao());
        departamento.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return departamento;
    }

    @Override
    public List<DepartamentoDTO> converterParaListaDeDtos(List<Departamento> entityList) {

        List<DepartamentoDTO> lista = new ArrayList<>();
        for (Departamento entity : entityList) {
            lista.add(converterParaDto(entity));
        }
        return lista;
    }

}
