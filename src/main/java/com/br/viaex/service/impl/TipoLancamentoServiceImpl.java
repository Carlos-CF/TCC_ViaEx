/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.service.impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.TipoLancamento;
import com.br.viaex.model.dto.TipoLancamentoDTO;
import com.br.viaex.repository.TipoLancamentoRepository;
import com.br.viaex.service.TipoLancamentoService;
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
public class TipoLancamentoServiceImpl implements TipoLancamentoService {

    @Autowired
    private TipoLancamentoRepository tipoLancamentoRepository;

    @Autowired
    private CustomObjectMapper<TipoLancamento, TipoLancamentoDTO> tipoLancamentoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        TipoLancamento objeto = tipoLancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Lançamento com ID " + idObjeto + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        TipoLancamento objetoAtualizado = tipoLancamentoRepository.saveAndFlush(objeto);

        // Converte o objeto atualizado para DTO
        TipoLancamentoDTO objetoDTO = tipoLancamentoMapper.converterParaDto(objetoAtualizado);

        // Retorna a resposta com o objeto atualizado
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(TipoLancamentoDTO objeto) throws Exception {

        if (tipoLancamentoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Lançamento. Já existe outro Tipo Lançamento com o mesmo nome."));
        }

        TipoLancamento tipoLancamento = new TipoLancamento();
        tipoLancamento.setNome(objeto.getNome());
        tipoLancamento.setStatus(objeto.isStatus());

        TipoLancamento objetoCriado = tipoLancamentoRepository.saveAndFlush(tipoLancamento);
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

        List<TipoLancamentoDTO> tipoLancamentoDTOs = tipoLancamentoMapper.converterParaListaDeDtos(tipoLancamentoRepository.findAll());
        if (tipoLancamentoDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Tipo Lançamentos cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoLancamentoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, TipoLancamentoDTO objeto) throws Exception {
        TipoLancamento dadosDto = tipoLancamentoMapper.converterParaEntidade(objeto);
        TipoLancamento paraEditar = tipoLancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Lançamento com ID " + idObjeto + " não foi encontrada!"));

        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        TipoLancamento objetoAtualizado = tipoLancamentoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoLancamentoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        TipoLancamento tipoLancamento = tipoLancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Lançamento com ID " + idObjeto + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoLancamentoMapper.converterParaDto(tipoLancamento)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        tipoLancamentoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Lançamento com ID " + idObjeto + " não foi encontrada!"));

        tipoLancamentoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Tipo Lançamento foi excluído com sucesso."));
    }

}
