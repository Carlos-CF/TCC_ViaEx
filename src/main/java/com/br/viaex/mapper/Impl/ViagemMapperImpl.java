/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.mapper.Impl;

import com.br.viaex.mapper.CustomObjectMapper;
import com.br.viaex.model.Viagem;
import com.br.viaex.model.dto.ViagemDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */

@Component
public class ViagemMapperImpl implements CustomObjectMapper<Viagem, ViagemDTO>{

    @Override
    public ViagemDTO converterParaDto(Viagem entity) {
         
        ViagemDTO dto = new ViagemDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setValorTotal(entity.getValorTotal());
        dto.setAdiantamento(entity.isFinalizado());
        dto.setValorAdiantamento(entity.getValorAdiantamento());
        dto.setFinalizado(entity.isFinalizado());
        dto.setUsuario(entity.getUsuario());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public Viagem converterParaEntidade(ViagemDTO dto) {
        
        Viagem viagem = new Viagem();
        viagem.setId(dto.getId());
        viagem.setNome(dto.getNome());
        viagem.setValorTotal(dto.getValorTotal());
        viagem.setAdiantamento(dto.isFinalizado());
        viagem.setValorAdiantamento(dto.getValorAdiantamento());
        viagem.setFinalizado(dto.isFinalizado());
        viagem.setUsuario(dto.getUsuario());
        viagem.setDataCriacao(dto.getDataCriacao());
        viagem.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return viagem;
    }

    @Override
    public List<ViagemDTO> converterParaListaDeDtos(List<Viagem> entityList) {
       
        List<ViagemDTO> Lista = new ArrayList<>();
        for(Viagem entity : entityList){
            Lista.add(converterParaDto(entity));
        }
        return Lista;
    }
    
}
