/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.viaex.service;

import com.br.viaex.model.dto.DepartamentoDTO;
import com.br.viaex.service.util.CrudService;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Carlos Fernandes
 */
public interface DepartamentoService extends CrudService<DepartamentoDTO>{
    
    ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;
}
