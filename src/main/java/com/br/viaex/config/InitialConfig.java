/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.viaex.config;

import com.br.viaex.model.Administrador;
import com.br.viaex.model.Departamento;
import com.br.viaex.model.enumerated.Genero;
import com.br.viaex.repository.AdministradorRepository;
import com.br.viaex.repository.DepartamentoRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Carlos
 */
/*
@Configuration
public class InitialConfig {
    
    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @EventListener
    public void appReady(ApplicationReadyEvent event) throws Exception {
        
        Departamento departamento = new Departamento();
        
        departamento.setNome("String");
        
        departamento = departamentoRepository.saveAndFlush(departamento);

        Administrador administrador = new Administrador();
        administrador.setCpf("08854272035");
        administrador.setDataNascimento(LocalDate.now());
        administrador.setSenha(passwordEncoder.encode("12345678"));
        administrador.setNomeCompleto("admin");
        administrador.setEndereco("String");
        administrador.setCidade("String");
        administrador.setEstado("String");
        administrador.setGenero(Genero.FEMININO);
        administrador.setDepartamento(departamento);
        administrador.setEmail("carlin@gmail.com");
        
        administradorRepository.save(administrador);
        
    }
        
    
}
*/