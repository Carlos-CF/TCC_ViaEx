/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Departamento;
import com.br.viaex.model.dto.DepartamentoDTO;
import com.br.viaex.repository.DepartamentoRepository;
import com.br.viaex.service.DepartamentoService;
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
 * @author Carlos Fernandes
 */
@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private CustomObjectMapper<Departamento, DepartamentoDTO> departamentoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        Departamento objeto = departamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Departamento com ID " + idObjeto + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Departamento objetoAtualizado = departamentoRepository.saveAndFlush(objeto);

        // Converte o objeto atualizado para DTO
        DepartamentoDTO objetoDTO = departamentoMapper.converterParaDto(objetoAtualizado);

        // Retorna a resposta com o objeto atualizado 
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(DepartamentoDTO objeto) throws Exception {

        if (departamentoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Departamento. Já existe outro Departamento com o mesmo nome."));
        }

        Departamento departamento = new Departamento();
        departamento.setNome(objeto.getNome());
        departamento.setStatus(objeto.isStatus());

        Departamento objetoCriado = departamentoRepository.saveAndFlush(departamento);
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
        
        List<DepartamentoDTO> departamentoDTOs = departamentoMapper.converterParaListaDeDtos(departamentoRepository.findAll());
        if(departamentoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Departamento cadastrados no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(departamentoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, DepartamentoDTO objeto) throws Exception {
        
        Departamento dadosDto = departamentoMapper.converterParaEntidade(objeto);
        Departamento paraEditar = departamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Departamento com ID " + idObjeto + " não foi encontrada!"));
        
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Departamento objetoAtualizado = departamentoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(departamentoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
        Departamento departamento = departamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O departamento com ID " + idObjeto + " não foi encontrado!"));
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(departamentoMapper.converterParaDto(departamento)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        departamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O departamento com ID " + idObjeto + " não foi encontrada!"));
        
        departamentoRepository.deleteById(idObjeto);
       return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O departamento foi excluído com sucesso."));
    }

}
