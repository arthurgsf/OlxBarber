package com.arthurgsf.oqb.service;

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
public class UsuarioServiceTest {
    
    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test
    public void verificarSalvarUsuario(){
        //cenário
        Usuario user = Usuario.builder().nome("Teste")
                                        .telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();

        //ação
        Usuario salvo = service.salvar(user);  //salva?

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());

        repository.delete(salvo);
    }

    @Test
    public void verificarSalvarSemNome(){
        //cenário
        Usuario user = Usuario.builder().telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();


        //verificação
        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(user), "Nome do usuário deve ser informado");
    }

    @Test
    public void verificarSalvarSemEmail(){
        //cenário
        Usuario user = Usuario.builder().telefone("98989898")
                                        .nome("teste")
                                        .senha("123").build();


        //verificação
        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(user), "Email deve ser informado");
    }

    @Test
    public void verificarSalvarSemSenha(){
        //cenário
        Usuario user = Usuario.builder().email("teste@teste.com")
                                        .nome("teste")
                                        .telefone("98989898").build();


        //verificação
        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(user), "Usuário deve possui senha");
    }

    @Test
    public void verificarDoisEmailsIguais(){
        //cenário
        Usuario user = Usuario.builder().nome("Teste")
                                        .email("teste@teste.com")
                                        .senha("123")
                                        .telefone("98989898").build();


        //ação                               
        Usuario salvo = service.salvar(user);

        //verificação
        Assertions.assertThrows(RuntimeException.class, () -> service.salvar(user));

        repository.delete(salvo);
    }


}
