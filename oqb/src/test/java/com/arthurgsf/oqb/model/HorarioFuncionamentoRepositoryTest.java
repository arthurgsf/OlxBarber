package com.arthurgsf.oqb.model;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.repo.QuadraRepository;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.model.repo.UsuarioRepository;
import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.repo.HorarioFuncionamentoRepository;
import com.arthurgsf.oqb.model.entity.Reserva;
import com.arthurgsf.oqb.model.repo.ReservaRepository;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HorarioFuncionamentoRepositoryTest {
    @Autowired
    HorarioFuncionamentoRepository repo;

    @Autowired
    QuadraRepository quadrarepo;

    @Autowired
    UsuarioRepository userepo;

    @Test
public void verificarSalvarHorario(){
        //cenário

        //criar usuario
        Usuario user = Usuario.builder().nome("Teste")
                                        .telefone("98989898")
                                        .email("teste@teste.com")
                                        .senha("123").build();

        //salvar o usuario criado acima                              
        Usuario usersalvo = userepo.save(user);



        Quadra qdr = Quadra.builder().nome("Quadra Mix")
                                    .endereco("Rua das Carmélias")
                                    .descricao("Ótima quadra para quem quer festejar entre amigos")
                                    .esportes("Basquete, Volêi, Futsal")
                                    .preco(50.00)
                                    .usuario(usersalvo).build();

        Quadra quadrasalvo = quadrarepo.save(qdr);

    

        HorarioFuncionamento horio = HorarioFuncionamento.builder().diaSemana(1)
                                        .hora(LocalTime.now())
                                        .quadra(quadrasalvo).build();

        //ação
        HorarioFuncionamento salvo = repo.save(horio);  //salva?

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(horio.getDiaSemana(), salvo.getDiaSemana());
        Assertions.assertEquals(horio.getHora(), salvo.getHora());
        

        repo.delete(salvo);
        quadrarepo.delete(quadrasalvo);
        userepo.delete(usersalvo);
    }
}
