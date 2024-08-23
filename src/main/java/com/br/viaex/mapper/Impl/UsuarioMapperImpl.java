/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Usuario;
import com.br.viaex.model.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */

@Component
public class UsuarioMapperImpl implements CustomObjectMapper<Usuario, UsuarioDTO>{

    @Override
    public UsuarioDTO converterParaDto(Usuario entity) {
        
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
    public Usuario converterParaEntidade(UsuarioDTO dto) {
        
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setGenero(dto.getGenero());
        usuario.setEndereco(dto.getEndereco());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setStatus(dto.isStatus());
        usuario.setDepartamento(dto.getDepartamento());
        usuario.setDataCriacao(dto.getDataCriacao());
        usuario.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return usuario;
    }

    @Override
    public List<UsuarioDTO> converterParaListaDeDtos(List<Usuario> entityList) {
        
        List<UsuarioDTO> Lista = new ArrayList<>();
        for(Usuario entity : entityList){
            Lista.add(converterParaDto(entity));
        }
        return Lista;
    }
    
}
