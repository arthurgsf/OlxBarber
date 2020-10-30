package com.arthurgsf.olxbarber.service;

import com.arthurgsf.olxbarber.model.repositorios.UsuarioRepository;

import java.util.Optional;

import com.arthurgsf.olxbarber.model.entidades.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public boolean efetuarLogin(String email, String Senha){
        Optional<Usuario> usr = usuarioRepository.findByEmail(email);
        if(!usr.isPresent())
            throw new RuntimeException("Erro de Autenticação. E-mail não cadastrado.");
        if(!usr.get().getSenha().equals(Senha))
            throw new RuntimeException("Erro de Autenticação. Senha inválida.");
        return true;
    }

    public void validar(Usuario usuario){

    }

}
