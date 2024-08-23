/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.viaex.service;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Carlos
 */
public interface UsuarioService<T> {
    
    ResponseEntity<Object> listar() throws Exception;
    
    ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception;
    
    ResponseEntity<Object> retorno(Long idObjeto) throws Exception;
}
