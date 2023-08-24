package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class EnderecoRepositoryTests {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Integer idExistente;
    private Integer idInexistente;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
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
