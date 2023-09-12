package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.repositories.DentistaRepository;
import br.com.dh.clinica.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class DentistaServiceTesteDeIntegracao {

    @Autowired
    private DentistaService service;

    @Autowired
    private DentistaRepository repository;

    private Integer idExistente;
    private Integer idInexistente;

    private DentistaDto dto;

    @BeforeEach
    void setup() throws Exception{
        idExistente = 1;
        idInexistente = 999;
        dto = Factory.criarDentistaDto();
    }

    // Teste do método findAll
    @Test
    public void findAllDeveriaTrazerUmaLista(){
        List<DentistaDto> resultado = service.buscarTodos();
        Assertions.assertFalse(resultado.isEmpty());
    }
}
