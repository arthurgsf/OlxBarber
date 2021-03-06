package com.arthurgsf.oqb.model;

import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.model.repo.UsuarioRepository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repo;

    @Test
    public void verificarSalvarUsuario(){
        //cenário
        Usuario user = Usuario.builder().nome("Teste")
                                        .telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();

        //ação
        Usuario salvo = repo.save(user);  //salva?

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(user.getNome(), salvo.getNome());
        Assertions.assertEquals(user.getTelefone(), salvo.getTelefone());
        Assertions.assertEquals(user.getEmail(), salvo.getEmail());
        Assertions.assertEquals(user.getSenha(), salvo.getSenha());
    }

    @Test
    public void verificarDeletarUsuario(){
        //cenário
        Usuario user = Usuario.builder().nome("Teste")
                                        .telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();

        //ação
        Usuario salvo = repo.save(user);  //salva
        Long id = salvo.getId();
        repo.deleteById(salvo.getId());

        //verificação
        Optional<Usuario> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
