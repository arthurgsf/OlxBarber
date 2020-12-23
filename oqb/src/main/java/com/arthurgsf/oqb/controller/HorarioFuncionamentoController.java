package com.arthurgsf.oqb.controller;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.arthurgsf.oqb.model.dto.HorarioFuncionamentoDTO;
import com.arthurgsf.oqb.model.entity.HorarioFuncionamento;
import com.arthurgsf.oqb.model.entity.Quadra;
import com.arthurgsf.oqb.service.HorarioFuncionamentoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionamento")
public class HorarioFuncionamentoController {
    @Autowired
    HorarioFuncionamentoService hrService;

    /**
     * Cria um horário de funcionamento relacionando um dia da semana, um horário e uma quadra
     * @param horario
     * @return Ok ou error
     */
    @PostMapping
    public ResponseEntity SalvaHorarioFuncionamento(@RequestBody HorarioFuncionamentoDTO horario){
        HorarioFuncionamento horarioFuncionamento = 
            HorarioFuncionamento.builder()
                                .diaSemana(horario.getDiaSemana())
                                .hora(LocalTime.of(horario.getHora(), horario.getMinuto()))
                                .quadra(Quadra.builder().id(horario.getIdQuadra()).build())
                                .build();
        try{
            HorarioFuncionamento salvo = hrService.salvar(horarioFuncionamento);
            return new ResponseEntity(salvo, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Lista os horários de funcionamento de uma quadra específica
     * @param id_quadra
     * @return List<HorarioFuncionamento>
     */
    @GetMapping
    public ResponseEntity ListarHorariosFuncionamento(@RequestParam int id_quadra){
        Optional<List<HorarioFuncionamento>> horariosFuncionamento = hrService.buscarPorQuadra(Quadra.builder().id(id_quadra).build());
        try{
            if (horariosFuncionamento.isPresent()){
                return new ResponseEntity(horariosFuncionamento.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity(Collections.emptyList(), HttpStatus.OK);
            }
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
