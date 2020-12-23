package com.arthurgsf.oqb.model;

import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.repo.QuadraRepository;
import com.arthurgsf.oqb.model.entity.Usuario;
import com.arthurgsf.oqb.model.repo.UsuarioRepository;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.repo.HorarioFuncionamentoRepository;
import com.arthurgsf.oqb.model.entity.Reserva;
import com.arthurgsf.oqb.model.repo.ReservaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalTimeConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservaRepositoryTest {
    @Autowired
    ReservaRepository repo;

    @Autowired
    HorarioFuncionamentoRepository horiorepo;

    @Autowired
    QuadraRepository quadrarepo;

    @Autowired
    UsuarioRepository userepo;

    @Test
    public void verificarSalvarReserva(){
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

        Quadra quadrasalvo = quadrarepo.save(qdr); //salvar o quadra criado acima



        HorarioFuncionamento horio = HorarioFuncionamento.builder().diaSemana(1)
                                                        .hora(LocalTime.now())
                                                        .quadra(quadrasalvo).build();


        HorarioFuncionamento horiosalvo = horiorepo.save(horio);

        


        Reserva reser = Reserva.builder().data(LocalDate.now())
                                        .hora(LocalTime.now())
                                        .usuario(usersalvo)
                                        .quadra(quadrasalvo)
                                        .horarioFuncionamento(horiosalvo).build();

        //ação
        Reserva salvo = repo.save(reser);  //salva?

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(reser.getData(), salvo.getData());
        Assertions.assertEquals(reser.getHora(), salvo.getHora());
        
        
        repo.delete(salvo);
        horiorepo.delete(horiosalvo);
        quadrarepo.delete(quadrasalvo);
        userepo.delete(usersalvo);
    }
}

