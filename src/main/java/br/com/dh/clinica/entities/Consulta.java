package br.com.dh.clinica.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Consulta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dataCadastro;
    private LocalDate dataAtendimento;

    private Paciente paciente;

    private Dentista dentista;

    private Usuario usuario;



}
