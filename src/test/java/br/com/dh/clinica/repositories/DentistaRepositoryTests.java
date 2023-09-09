package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class DentistaRepositoryTests {

    @Autowired
    private DentistaRepository dentistaRepository;

    private Integer idExistente;
    private Integer idInexistente;

    private Integer countTotalDentistas;

    private List<Dentista> dentistaList;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        countTotalDentistas = 3;
        dentistaList = new ArrayList<>();
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Dentista dentista = Factory.criarDentista();//Simula um DTO
        dentista = dentistaRepository.save(dentista);

        Assertions.assertNotNull(dentista.getId());
        Assertions.assertEquals(countTotalDentistas + 1, dentista.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        dentistaList = dentistaRepository.findAll();
        Assertions.assertNotNull(dentistaList);
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

    @Test
    public void deleteDeveriaExcluirUmRegistroDoBD(){
        dentistaRepository.deleteById(idExistente);
        Optional<Dentista> resultado = dentistaRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaNaoEncontrarORegistroNoBD(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            dentistaRepository.deleteById(idInexistente);
        });
    }
}
