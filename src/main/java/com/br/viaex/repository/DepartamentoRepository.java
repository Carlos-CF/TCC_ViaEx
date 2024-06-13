/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.viaex.repository;

import com.br.viaex.model.Departamento;
import com.br.viaex.model.TipoLancamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Fernandes
 */

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
    
    public boolean existsByNome(String nome); 
 
    public Optional<TipoLancamento> findByNome(String nome);
}
