/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Operacional;
import com.br.viaex.model.dto.UsuarioDTO;
import com.br.viaex.repository.OperacionalRepository;
import com.br.viaex.service.OperacionalService;
import com.br.viaex.service.util.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Carlos
 */
@Service
public class OperacionalServiceImpl implements OperacionalService {

    @Autowired
    private CustomObjectMapper<Operacional, UsuarioDTO> usuarioMapper;

    @Autowired
    private OperacionalRepository operacionalRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        Operacional objeto = operacionalRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Operacional com ID " + idObjeto + " não encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Operacional objetoAtualizado = operacionalRepository.saveAndFlush(objeto);

        UsuarioDTO objetoDTO = usuarioMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(UsuarioDTO objeto) throws Exception {

        if (operacionalRepository.existsByEmail(objeto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar operacional, Já existe outro email com o mesmo nome."));
        }

        if (operacionalRepository.existsByCpf(objeto.getCpf())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar operacional, Já existe outro CPF igual"));
        }

        if (objeto.getSenha().length() < 8) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("A senha precisa ter pelomenos 8 digitos"));
        }

        Operacional operacional = new Operacional();
        BeanUtils.copyProperties(objeto, operacional, "id", "tipoUsuario");
        operacional.setSenha(passwordEncoder.encode(operacional.getSenha()));
        Operacional objetoCriado = operacionalRepository.saveAndFlush(operacional);
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

        List<Operacional> Operacionais = operacionalRepository.findAll();
        if (Operacionais.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Não existem Operacionais cadastrados no sistema!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaListaDeDtos(Operacionais)));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, UsuarioDTO objeto) throws Exception {

        Operacional dadosDto = usuarioMapper.converterParaEntidade(objeto);
        Operacional paraEditar = operacionalRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Operacional com ID " + idObjeto + "não foi encontrada!"));

        Long idUsuarioEditado = objeto.getId();

        // Verifique se há outro usuário com o mesmo email, exceto o próprio usuário que está sendo editado
        if (operacionalRepository.existsByEmailAndIdNot(objeto.getEmail(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o Operacional, já existe outro email com o mesmo nome."));
        }

        // Verifique se há outro usuário com o mesmo CPF, exceto o próprio usuário que está sendo editado
        if (operacionalRepository.existsByCpfAndIdNot(objeto.getCpf(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o Operacional, já existe outro CPF igual."));
        }

        objeto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(objeto, paraEditar, "id");
        paraEditar.setSenha(passwordEncoder.encode(paraEditar.getSenha()));
        operacionalRepository.saveAndFlush(paraEditar);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(paraEditar));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Operacional operacional = operacionalRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O operacional com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(operacional)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        
        operacionalRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O operacional com o ID " + idObjeto + " não foi encontrada!"));

        operacionalRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O operacional foi excluída com sucesso."));
    }

}
