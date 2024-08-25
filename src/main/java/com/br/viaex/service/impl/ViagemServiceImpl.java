/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Viagem;
import com.br.viaex.model.dto.ViagemDTO;
import com.br.viaex.repository.ViagemRepository;
import com.br.viaex.service.ViagemService;
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
public class ViagemServiceImpl implements ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private CustomObjectMapper<Viagem, ViagemDTO> viagemMapper;

    @Override
    public ResponseEntity<Object> cadastrar(ViagemDTO objeto) throws Exception {
   
        Viagem viagem = new Viagem();
        viagem.setNome(objeto.getNome());
        viagem.setAdiantamento(objeto.isAdiantamento());
        viagem.setValorAdiantamento(objeto.getValorAdiantamento());
        viagem.setUsuario(objeto.getUsuario());
        

        Viagem objetoCriado = viagemRepository.saveAndFlush(viagem);
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

        List<ViagemDTO> viagemDTOs = viagemMapper.converterParaListaDeDtos(viagemRepository.findAll());
        if (viagemDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Viagens cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(viagemDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, ViagemDTO objeto) throws Exception {
       
        Viagem dadosDto = viagemMapper.converterParaEntidade(objeto);
        Viagem paraEditar = viagemRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Viagem com ID " + idObjeto + "não foi encontrada!"));
        
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(objeto,"id");
        viagemRepository.saveAndFlush(paraEditar);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(paraEditar));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
        
        Viagem viagem = viagemRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Viagem com ID " + idObjeto + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(viagemMapper.converterParaDto(viagem)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        
        viagemRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Viagem com ID " + idObjeto + " não foi encontrada!"));

        viagemRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Viagem foi excluído com sucesso."));
    }

}
