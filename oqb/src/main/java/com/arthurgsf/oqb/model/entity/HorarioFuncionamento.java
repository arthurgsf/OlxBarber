package com.arthurgsf.oqb.model.entity;

import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horario_funcionamento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioFuncionamento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dia_semana")
    private Integer diaSemana;

    @Column(name = "hora")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)    
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "id_quadra")
    private Quadra quadra;
}
