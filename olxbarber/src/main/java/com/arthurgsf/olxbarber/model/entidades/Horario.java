package com.arthurgsf.olxbarber.model.entidades;
import lombok.*;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="horario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Horario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "hora")
    private Time hora;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "dia")
    private Integer dia;

    @ManyToOne
    @JoinColumn (name = "id_quadra")
    private Quadra quadra;
}
