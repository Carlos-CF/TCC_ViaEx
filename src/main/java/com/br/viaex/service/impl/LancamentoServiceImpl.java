/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Lancamento;
import com.br.viaex.model.dto.LancamentoDTO;
import com.br.viaex.repository.LancamentoRepository;
import com.br.viaex.service.LancamentoService;
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
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private CustomObjectMapper<Lancamento, LancamentoDTO> lancamentoMapper;

    @Override
    public ResponseEntity<Object> cadastrar(LancamentoDTO objeto) throws Exception {
        
        Lancamento lancamento = new Lancamento();
        lancamento.setValor(objeto.getValor());
        lancamento.setData(objeto.getData());
        lancamento.setObservacao(objeto.getObservacao());
        lancamento.setTipoLancamento(objeto.getTipoLancamento());
        lancamento.setViagem(objeto.getViagem());
        lancamento.setStatusLancamento(objeto.getStatusLancamento());
        lancamento.setIdOperacional(objeto.getIdOperacional());
        //lancamento.setIdAprovador(objeto.getIdAprovador());
        
        Lancamento objetoCriado = lancamentoRepository.saveAndFlush(lancamento);
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

        List<LancamentoDTO> lancamentoDTOs = lancamentoMapper.converterParaListaDeDtos(lancamentoRepository.findAll());
        if (lancamentoDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Lançamentos cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(lancamentoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, LancamentoDTO objeto) throws Exception {
        
        Lancamento dadosDto = lancamentoMapper.converterParaEntidade(objeto);
        Lancamento paraEditar = lancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Lançamento com ID " + idObjeto + "não foi encontrada!"));
        
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setValor(objeto.getValor());
        dadosDto.setData(objeto.getData());
        dadosDto.setObservacao(objeto.getObservacao());
        dadosDto.setDataCriacao(objeto.getDataCriacao());
        dadosDto.setTipoLancamento(objeto.getTipoLancamento());
        dadosDto.setViagem(objeto.getViagem());
        dadosDto.setStatusLancamento(objeto.getStatusLancamento());
        dadosDto.setIdOperacional(objeto.getIdOperacional());
        dadosDto.setIdAprovador(objeto.getIdAprovador());
        
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto,paraEditar,"id");
        Lancamento objetoAtualizado = lancamentoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoAtualizado));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Lancamento lancamento = lancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Lançamento com ID " + idObjeto + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(lancamentoMapper.converterParaDto(lancamento)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        lancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Lançamento com ID " + idObjeto + " não foi encontrada!"));

        lancamentoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Lançamento foi excluído com sucesso."));
    }

}
