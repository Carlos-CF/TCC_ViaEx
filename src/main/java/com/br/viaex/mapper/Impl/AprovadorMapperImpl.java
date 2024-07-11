/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Aprovador;
import com.br.viaex.model.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class AprovadorMapperImpl implements CustomObjectMapper<Aprovador, UsuarioDTO>{

    @Override
    public UsuarioDTO converterParaDto(Aprovador entity) {
       
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
    public Aprovador converterParaEntidade(UsuarioDTO dto) {
        
        Aprovador aprovador = new Aprovador();
        aprovador.setId(dto.getId());
        aprovador.setNomeCompleto(dto.getNomeCompleto());
        aprovador.setDataNascimento(dto.getDataNascimento());
        aprovador.setCpf(dto.getCpf());
        aprovador.setEmail(dto.getEmail());
        aprovador.setSenha(dto.getSenha());
        aprovador.setGenero(dto.getGenero());
        aprovador.setEndereco(dto.getEndereco());
        aprovador.setCidade(dto.getCidade());
        aprovador.setEstado(dto.getEstado());
        aprovador.setStatus(dto.isStatus());
        aprovador.setDepartamento(dto.getDepartamento());
        aprovador.setDataCriacao(dto.getDataCriacao());
        aprovador.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return aprovador;
    }

    @Override
    public List<UsuarioDTO> converterParaListaDeDtos(List<Aprovador> entityList) {
       
        List<UsuarioDTO> Lista = new ArrayList<>();
        for(Aprovador entity : entityList){
            Lista.add(converterParaDto(entity));
        }
        return Lista;
    }
    
}
