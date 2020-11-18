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

    private void validar(Quadra quadra) {
        if (quadra == null)
            throw new RuntimeException("Uma quadra válida deve ser informada");
        if ((quadra.getNome() == null) || (quadra.getNome().equals("")))
            throw new RuntimeException("Nome do usuário deve ser informado");
        if ((quadra.getPreco() == null))
            throw new RuntimeException("O preço da hora da quadra deve ser informado");
        if ((quadra.getEndereco() == null) || (quadra.getEndereco().equals("")))
            throw new RuntimeException("O endereço da quadra deve ser informado");
    }

}
