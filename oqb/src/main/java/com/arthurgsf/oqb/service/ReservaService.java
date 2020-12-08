package com.arthurgsf.oqb.service;

import com.arthurgsf.oqb.model.entity.Reserva;
import com.arthurgsf.oqb.model.repo.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository repo;

    public Reserva salvar(Reserva reserva){
        validar(reserva);
        return repo.save(reserva);
    }

    private void validar(Reserva reserva) {
        Example<Reserva> example = Example.of(reserva, ExampleMatcher.matchingAll());
        boolean ReservaJaExiste = repo.exists(example);

        if(ReservaJaExiste)
            throw new RuntimeException("Reserva já cadastrada");
    }

    // public List<Reserva> obterReservas() {
    //     return repo.findAll();
    // }

    // public Reserva obterReserva(Reserva qdr) {
    //     Optional<Reserva> Reserva = repo.findById(qdr.getId());
    //     if (Reserva.isPresent())
    //         return Reserva.get();
    //     else
    //         return null;
    // }

    // public void deletar(Long id) {
    //     Optional<Reserva> qdr = repo.findById(id);
    //     if(!qdr.isPresent())
    //         throw new RuntimeException("Erro ao deletar. Reserva inexistente.");

    //     repo.delete(qdr.get());
    // }
}
