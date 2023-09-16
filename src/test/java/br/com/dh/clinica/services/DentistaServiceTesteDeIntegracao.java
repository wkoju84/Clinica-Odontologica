package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.repositories.DentistaRepository;
import br.com.dh.clinica.services.exceptions.BancoDeDadosException;
import br.com.dh.clinica.services.exceptions.EntidadeNaoEncontradaException;
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
    private Integer idDependente;
    private DentistaDto dto;

    @BeforeEach
    void setup() throws Exception{
        idExistente = 1;
        idInexistente = 999;
        idDependente = 3;
        dto = Factory.criarDentistaDto();
    }

    // Teste do método findAll
    @Test
    public void findAllDeveriaTrazerUmaLista(){
        List<DentistaDto> resultado = service.buscarTodos();
        Assertions.assertFalse(resultado.isEmpty());
    }

    // Teste do método findById
    @Test
    public void findByIdDeveriaTrazerUmRegistro(){
        DentistaDto resultado = service.buscarPorId(idExistente);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaLancarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(idInexistente);
        });
    }

    // Teste do método save
    @Test
    public void saveDeveriaPersistirNoBancoDeDados(){
      DentistaDto dto1 = dto;
      dto1.setId(null);
      dto1 = service.inserir(dto1);
      Assertions.assertNotNull(dto1.getId());
    }

    // Teste do método update
    @Test
    public void updateDeveriaAtualizarUmRegistro(){
        DentistaDto resultado = service.atualizar(idExistente, dto);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método update com exceção
    @Test
    public void updateDeveriaLancarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.atualizar(idInexistente, dto);
        });
    }

    // Teste do método delete
    @Test
    public void deleteDeveriaExcluirUmRegistro(){
        Assertions.assertDoesNotThrow(() -> {
            service.excluir(idExistente);
        });
    }

    // Teste do método delete com exceção de recurso não encontrado
    @Test
    public void deleteDeveriaLancarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.excluir(idInexistente);
        });
    }

    // Teste do método delete com exceção de integridade do BD
//    @Test
//    public void deleteDeveriaLancarUmaViolacaoDeIntegridade(){
//        Assertions.assertThrows(BancoDeDadosException.class, () -> {
//            service.excluir(idDependente);
//        });
//    }
}
