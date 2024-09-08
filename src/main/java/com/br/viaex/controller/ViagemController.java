/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.controller;

import com.br.viaex.model.dto.ViagemDTO;
import com.br.viaex.service.ViagemService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping(value = "/viagem")
public class ViagemController {
    
    @Autowired
    private ViagemService viagemService;

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody ViagemDTO objeto) throws Exception {
        return viagemService.cadastrar(objeto);
    }

    @GetMapping
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return viagemService.listar();
    }

    @GetMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return viagemService.listarPorId(idObjeto);
    }

    @PutMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> editar(@PathVariable Long idObjeto, @RequestBody ViagemDTO objeto) throws Exception {
        return viagemService.editar(idObjeto, objeto);
    }

    @DeleteMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ViagemDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> excluir(@PathVariable Long idObjeto) throws Exception {
        return viagemService.excluir(idObjeto);
    }
}
