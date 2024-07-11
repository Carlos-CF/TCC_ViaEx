/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Aprovador;
import com.br.viaex.model.dto.UsuarioDTO;
import com.br.viaex.repository.AprovadorRepository;
import com.br.viaex.service.AprovadorService;
import com.br.viaex.service.util.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Carlos
 */

@Service
public class AprovadorServiceImpl implements AprovadorService{
    
    @Autowired
    private CustomObjectMapper<Aprovador, UsuarioDTO> usuarioMapper;

    @Autowired
    private AprovadorRepository aprovadorRepository;


    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
       
        Aprovador objeto = aprovadorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Aprovador com ID " + idObjeto + " não encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Aprovador objetoAtualizado = aprovadorRepository.saveAndFlush(objeto);

        UsuarioDTO objetoDTO = usuarioMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(UsuarioDTO objeto) throws Exception {
        
        if (aprovadorRepository.existsByEmail(objeto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Aprovador, Já existe outro email com o mesmo nome."));
        }

        if (aprovadorRepository.existsByCpf(objeto.getCpf())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Aprovador, Já existe outro CPF igual"));
        }

        if (objeto.getSenha().length() < 8) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("A senha precisa ter pelomenos 8 digitos"));
        }

        Aprovador aprovador = new Aprovador();
        BeanUtils.copyProperties(objeto, aprovador, "id", "tipoUsuario");
        aprovador.setSenha(aprovador.getSenha());
        Aprovador objetoCriado = aprovadorRepository.saveAndFlush(aprovador);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> listar() throws Exception {
        
     List<Aprovador> aprovadores = aprovadorRepository.findAll();
        if (aprovadores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Não existem Aprovadores cadastrados no sistema!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaListaDeDtos(aprovadores)));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, UsuarioDTO objeto) throws Exception {
        
        Aprovador dadosDto = usuarioMapper.converterParaEntidade(objeto);
        Aprovador paraEditar = aprovadorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Aprovador com ID " + idObjeto + "não foi encontrada!"));

        Long idUsuarioEditado = objeto.getId();

        // Verifique se há outro usuário com o mesmo email, exceto o próprio usuário que está sendo editado
        if (aprovadorRepository.existsByEmailAndIdNot(objeto.getEmail(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o Aprovador, já existe outro email com o mesmo nome."));
        }

        // Verifique se há outro usuário com o mesmo CPF, exceto o próprio usuário que está sendo editado
        if (aprovadorRepository.existsByCpfAndIdNot(objeto.getCpf(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o Aprovador, já existe outro CPF igual."));
        }

        objeto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(objeto, paraEditar, "id", "tipoUsuario");
        paraEditar.setSenha(paraEditar.getSenha());
        aprovadorRepository.saveAndFlush(paraEditar);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(paraEditar));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
       
        Aprovador aprovador = aprovadorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Aprovador com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(aprovador)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
       
        aprovadorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Aprovador com o ID " + idObjeto + " não foi encontrada!"));

        aprovadorRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Aprovador foi excluída com sucesso."));
    }
    
}
