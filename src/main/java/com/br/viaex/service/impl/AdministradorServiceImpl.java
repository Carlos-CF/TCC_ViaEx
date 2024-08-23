/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Administrador;
import com.br.viaex.model.Departamento;
import com.br.viaex.model.dto.UsuarioDTO;
import com.br.viaex.repository.AdministradorRepository;
import com.br.viaex.repository.DepartamentoRepository;
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
import com.br.viaex.service.AdministradorService;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Carlos
 */
@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private CustomObjectMapper<Administrador, UsuarioDTO> usuarioMapper;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        Administrador objeto = administradorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("administrador com ID " + idObjeto + " não encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Administrador objetoAtualizado = administradorRepository.saveAndFlush(objeto);

        UsuarioDTO objetoDTO = usuarioMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(UsuarioDTO objeto) throws Exception {

        if (administradorRepository.existsByEmail(objeto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o administrador, Já existe outro email com o mesmo nome."));
        }

        if (administradorRepository.existsByCpf(objeto.getCpf())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o administrador, Já existe outro CPF igual"));
        }

        if (objeto.getSenha().length() < 8) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("A senha precisa ter pelomenos 8 digitos"));
        }
        
       
        Administrador administrador = new Administrador();
        BeanUtils.copyProperties(objeto, administrador, "id", "tipoUsuario");
        administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));
        Administrador objetoCriado = administradorRepository.saveAndFlush(administrador);
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

        List<Administrador> administradores = administradorRepository.findAll();
        if (administradores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Não existem administradores cadastrados no sistema!"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaListaDeDtos(administradores)));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, UsuarioDTO objeto) throws Exception {

        Administrador dadosDto = usuarioMapper.converterParaEntidade(objeto);
        Administrador paraEditar = administradorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O administrador com ID " + idObjeto + "não foi encontrada!"));

        Long idUsuarioEditado = objeto.getId();

        // Verifique se há outro usuário com o mesmo email, exceto o próprio usuário que está sendo editado
        if (administradorRepository.existsByEmailAndIdNot(objeto.getEmail(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o administrador, já existe outro email com o mesmo nome."));
        }

        // Verifique se há outro usuário com o mesmo CPF, exceto o próprio usuário que está sendo editado
        if (administradorRepository.existsByCpfAndIdNot(objeto.getCpf(), idUsuarioEditado)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possível editar o administrador, já existe outro CPF igual."));
        }

        objeto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(objeto, paraEditar, "id", "tipoUsuario");
        paraEditar.setSenha(passwordEncoder.encode(paraEditar.getSenha()));
        administradorRepository.saveAndFlush(paraEditar);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(paraEditar));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Administrador administrador = administradorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O administrador com ID " + idObjeto + " não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(administrador)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        administradorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O administrador com o ID " + idObjeto + " não foi encontrada!"));

        administradorRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O administrador foi excluída com sucesso."));
    }

}
