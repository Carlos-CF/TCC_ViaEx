/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.viaex.repository;

import com.br.viaex.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    public boolean existsByEmail(String email);
    
    public boolean existsByCpf(String cpf);
    
    public Optional<Usuario> findByNomeCompleto(String nomeCompleto); 
}
