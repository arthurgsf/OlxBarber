package com.arthurgsf.oqb.model;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.repo.QuadraRepository;
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
public class QuadraRepositoryTest {
    @Autowired
    QuadraRepository repo;

    @Autowired
    UsuarioRepository userepo;

    @Test
    public void verificarSalvarQuadra(){
        //cenário

        //criar usuario
        Usuario user = Usuario.builder().nome("Teste")
                                        .telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();
                              
        Usuario usersalvo = userepo.save(user); //salvar o usuario criado acima

        Quadra qdr = Quadra.builder().nome("Quadra Mix")
                                    .endereco("Rua das Carmélias")
                                    .descricao("Ótima quadra para quem quer festejar entre amigos")
                                    .esportes("Basquete, Volêi, Futsal")
                                    .preco(50.00)
                                    .usuario(usersalvo).build();

        //ação
        Quadra salvo = repo.save(qdr);  //salva?

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(qdr.getNome(), salvo.getNome());
        Assertions.assertEquals(qdr.getEndereco(), salvo.getEndereco());
        Assertions.assertEquals(qdr.getDescricao(), salvo.getDescricao());
        Assertions.assertEquals(qdr.getEsportes(), salvo.getEsportes());
        Assertions.assertEquals(qdr.getPreco(), salvo.getPreco());

        repo.delete(salvo);

        userepo.delete(usersalvo);
        
    }
}
