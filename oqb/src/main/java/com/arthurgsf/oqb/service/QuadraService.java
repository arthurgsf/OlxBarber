package com.arthurgsf.oqb.service;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.repo.QuadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuadraService {
    @Autowired
    QuadraRepository repo;

    public Quadra salvar(Quadra quadra){
        validar(quadra);
        return repo.save(quadra);
    }

    public boolean login(String nome, String senha){
        Optional<Quadra> quadra = repo.findByNome(nome);
        if(!quadra.isPresent())
            throw new RuntimeException("Erro de Autenticação. Nome não cadastrado.");
        /*if(!quadra.get().getSenha().equals(senha))
            throw new RuntimeException("Erro de Autenticação. Senha inválida.");*/
        return true;
    }

    public void deletar(String Nome){
        Optional<Quadra> quadra = repo.findByNome(Nome);
        if(!quadra.isPresent())
            throw new RuntimeException("Erro ao deletar. Quadra inexistente.");
        
        repo.delete(quadra.get());
    }

    private void validar(Quadra quadra) {
        if (quadra == null)
            throw new RuntimeException("Uma quadra válida deve ser informada");
        if ((quadra.getNome() == null) || (quadra.getNome().equals("")))
            throw new RuntimeException("Nome do usuário deve ser informado");
        if ((quadra.getPreco() == null))
            throw new RuntimeException("O preço da hora da quadra deve ser informado");
        if ((quadra.getEndereco() == null) || (quadra.getEndereco().equals("")))
            throw new RuntimeException("O endereço da quadra deve ser informado");
        if ((quadra.getEmail() == null) || (quadra.getEmail().equals("")))
            throw new RuntimeException("O e-mail da quadra deve ser informado");
        
        boolean nomeJaExiste = repo.existsByNome(quadra.getNome());
        if (nomeJaExiste)
            throw new RuntimeException("Nome informado já existe na base");               
        /*if ((quadra.getSenha() == null) || (quadra.getSenha().equals("")))
            throw new RuntimeException("Quadra deve possui senha");*/
    }
}
