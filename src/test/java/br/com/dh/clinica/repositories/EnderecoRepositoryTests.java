package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EnderecoRepositoryTests {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Integer idExistente;
    private Integer idInexistente;

    private Integer countTotalEndereco;

    private List<Endereco> enderecoList;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        countTotalEndereco = 3;
        enderecoList = new ArrayList<>();

    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Endereco endereco = Factory.criarEndereco();//Simula um DTO
        endereco = enderecoRepository.save(endereco);

        Assertions.assertNotNull(endereco.getId());
        Assertions.assertEquals(countTotalEndereco + 1, endereco.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        enderecoList = enderecoRepository.findAll();
        Assertions.assertNotNull(enderecoList);
    }

    @Test
    public void buscarPorIdERetornarUmEndereco(){
        Optional<Endereco> resultado = enderecoRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Endereco> resultado = enderecoRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }
}
