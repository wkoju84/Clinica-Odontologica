package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Dentista;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class DentistaRepositoryTests {

    @Autowired
    private DentistaRepository dentistaRepository;

    private Integer idExistente;
    private Integer idInexistente;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
    }

    @Test
    public void buscarPorIdERetornarUmDentista(){
        Optional<Dentista> resultado = dentistaRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Dentista> resultado = dentistaRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }
}
