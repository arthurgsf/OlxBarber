package com.olxbarber.olxbarber;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nome")
    private String Nome;

    @Column(name = "login")
    private String Login;

    @Column(name = "senha")
    private String Senha;
  
    @Column(name = "email")
    private String Email;

    @Column(name = "telefone")
    private String Telefone;
}
