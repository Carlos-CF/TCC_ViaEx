/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;


import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Usuario;
import com.br.viaex.model.dto.UsuarioDTO;
import com.br.viaex.repository.UsuarioRepository;
import com.br.viaex.service.UsuarioService;
import com.br.viaex.service.util.ApiResponse;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private CustomObjectMapper<Usuario, UsuarioDTO> usuarioMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity listar() throws Exception {

        List<Usuario> usuario = usuarioRepository.findAll();
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Não existem Usuario cadastrados no sistema!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaListaDeDtos(usuario)));
    }

    @Override
    public ResponseEntity listarPorId(Long idObjeto) throws Exception {

        Usuario usuario = usuarioRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Usuario com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(usuario)));
    }

    @Override
    public ResponseEntity retorno(Long idObjeto) throws Exception {

        Usuario usuario = usuarioRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Usuario com ID " + idObjeto + " não foi encontrado!"));

        if (usuario.getTipoUsuario() == usuario.getTipoUsuario().ADMINISTRADOR
                || usuario.getTipoUsuario() == usuario.getTipoUsuario().APROVADOR) {

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(false));
    }

}
