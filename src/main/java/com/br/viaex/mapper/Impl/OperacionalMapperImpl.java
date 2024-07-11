/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Operacional;
import com.br.viaex.model.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */

@Component
public class OperacionalMapperImpl implements CustomObjectMapper<Operacional, UsuarioDTO>{

    @Override
    public UsuarioDTO converterParaDto(Operacional entity) {
     
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNomeCompleto());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setCpf(entity.getCpf());
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());
        dto.setGenero(entity.getGenero());
        dto.setEndereco(entity.getEndereco());
        dto.setCidade(entity.getCidade());
        dto.setEstado(entity.getEstado());
        dto.setStatus(entity.isStatus());
        dto.setDepartamento(entity.getDepartamento());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public Operacional converterParaEntidade(UsuarioDTO dto) {
        
        Operacional operacional = new Operacional();
        operacional.setId(dto.getId());
        operacional.setNomeCompleto(dto.getNomeCompleto());
        operacional.setDataNascimento(dto.getDataNascimento());
        operacional.setCpf(dto.getCpf());
        operacional.setEmail(dto.getEmail());
        operacional.setSenha(dto.getSenha());
        operacional.setGenero(dto.getGenero());
        operacional.setEndereco(dto.getEndereco());
        operacional.setCidade(dto.getCidade());
        operacional.setEstado(dto.getEstado());
        operacional.setStatus(dto.isStatus());
        operacional.setDepartamento(dto.getDepartamento());
        operacional.setDataCriacao(dto.getDataCriacao());
        operacional.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return operacional;
    }

    @Override
    public List<UsuarioDTO> converterParaListaDeDtos(List<Operacional> entityList) {
       
        List<UsuarioDTO> Lista = new ArrayList<>();
        for(Operacional entity : entityList){
            Lista.add(converterParaDto(entity));
        }
        return Lista;
    }
    
}
