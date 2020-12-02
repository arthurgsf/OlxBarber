package com.arthurgsf.oqb.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.model.repo.HorarioFuncionamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioFuncionamentoService {
    @Autowired
    HorarioFuncionamentoRepository repo;

    public void deletar(HorarioFuncionamento horario){
        repo.delete(horario);
    }

    public HorarioFuncionamento salvar(HorarioFuncionamento horario){
        validar(horario);
        return repo.save(horario);
    }

    public Optional<List<HorarioFuncionamento>> buscarPorQuadra(Quadra quadra){
        return repo.findByQuadra(quadra);
    }

    public void validar(HorarioFuncionamento horario){
        // valida o inteiro que representa o dia da semana
        if(horario.getDiaSemana() < 1 || horario.getDiaSemana() > 7){
            throw new RuntimeException(
                "Dia da semana inválido, especifique um inteiro 1 <= x <=7"
            );
        }

        Quadra q = horario.getQuadra();
        Optional<List<HorarioFuncionamento>> opt = repo.findByQuadra(
            horario.getQuadra()
        );
        if(opt.isPresent()){
            // valida se o horário está em conflito com algum outro
            // Para comparar, primeiro verificamos se os dias da semana são iguais,
            // depois soma-se 1 hora ao horário que está tentando ser cadastrado
            // depois subtrai-se 1 hora ao horário que está tentando ser cadastrado
            // verifica se algum horário no banco já esteja cadastrado nesse 
            // intervalo de tempo +1 -1
            // se sim, deverá retornar erro na requisição
            List<HorarioFuncionamento> horariosFuncionamento = opt.get();
            for (HorarioFuncionamento h : horariosFuncionamento) {
                if(h.getDiaSemana() == horario.getDiaSemana()){
                    LocalTime tempoSubtraido = horario.getHora().minusHours(1);
                    LocalTime tempoSomado = horario.getHora().plusHours(1);
                    
                    if(h.getHora().isAfter(tempoSubtraido) && 
                    h.getHora().isBefore(tempoSomado)){
                        throw new RuntimeException(
                            "Erro ao cadastrar horário de funcionamento. Horários conflitantes"
                            );
                    }
                }
            }
        }
    }
}
