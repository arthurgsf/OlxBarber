package com.arthurgsf.oqb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name="usuario"
    )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "senha")
    private String senha;
  
    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;
}
