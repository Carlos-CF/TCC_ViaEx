
package com.br.viaex.service.impl;

import com.br.viaex.model.Usuario;
import com.br.viaex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Carlos
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário foi encontrado com esse email!"));
        return new UserDetailsImpl(usuario.getId(), usuario.getCpf(), usuario.getNomeCompleto(), usuario.getSenha(), usuario.getTipoUsuario());
    }
    
}
