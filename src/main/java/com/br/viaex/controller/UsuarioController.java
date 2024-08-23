/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.controller;

import com.br.viaex.model.dto.UsuarioDTO;
import com.br.viaex.service.UsuarioService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos
 */

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return usuarioService.listar();
    }

    @GetMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return usuarioService.listarPorId(idObjeto);
    }
    
    @GetMapping("/retorno/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> retorno(@PathVariable Long idObjeto) throws Exception {
        return usuarioService.retorno(idObjeto);
    }
}
