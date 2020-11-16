package com.arthurgsf.oqb.service;

import java.util.Optional;

import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.model.repo.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repo;

    public boolean login(String email, String senha){
        Optional<Usuario> usr = repo.findByEmail(email);
        if(!usr.isPresent())
            throw new RuntimeException("Erro de Autenticação. E-mail não cadastrado.");
        if(!usr.get().getSenha().equals(senha))
            throw new RuntimeException("Erro de Autenticação. Senha inválida.");
        return true;
    }

    public Usuario salvar(Usuario usuario){
        validar(usuario);
        return repo.save(usuario);
    }

    public void deletar(String Email){
        Optional<Usuario> usr = repo.findByEmail(Email);
        if(!usr.isPresent())
            throw new RuntimeException("Erro ao deletar. Usuário inexistente.");
        
        repo.delete(usr.get());
    }

    // só vai ter update se a gente quiser enfeitar depois
    // public void update(Usuario usuario){
    //     Optional<Usuario> usr = repo.findByEmail(Email);
    //     repo.
    // }

    private void validar(Usuario usuario) {
        if (usuario == null)
            throw new RuntimeException("Um usuário válido deve ser informado");                
        if ((usuario.getNome() == null) || (usuario.getNome().equals("")))
            throw new RuntimeException("Nome do usuário deve ser informado");    
        if ((usuario.getEmail() == null) || (usuario.getEmail().equals("")))
            throw new RuntimeException("Email deve ser informado");          
        boolean emailJaExiste = repo.existsByEmail(usuario.getEmail());
        if (emailJaExiste)
            throw new RuntimeException("Email informado já existe na base");               
        if ((usuario.getSenha() == null) || (usuario.getSenha().equals("")))
            throw new RuntimeException("Usuário deve possui senha");
    }
}
