package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Usuario;
import br.com.dh.clinica.repositories.DentistaRepository;
import br.com.dh.clinica.repositories.UsuarioRepository;
import br.com.dh.clinica.services.exceptions.BancoDeDadosException;
import br.com.dh.clinica.services.exceptions.EntidadeNaoEncontradaException;
import br.com.dh.clinica.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    private Integer idExistente;
    private Integer idInexistente;
    private Integer idDependente;
    private Usuario usuario;
    private UsuarioDto dto;

    private List<Usuario> list;

    @BeforeEach
    void setup() throws Exception {
        idExistente = 1;
        idInexistente = 999;
        idDependente = 2;
        usuario = Factory.criarUsuario();
        dto = Factory.criarUsuarioDto();
        list = new ArrayList<>();

        //Mock do findAll
        Mockito.when(repository.findAll((Sort) ArgumentMatchers.any())).thenReturn(list);

        //Mock do findById
        Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(usuario));
        Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        //Mock do save
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(usuario);

        //Mock do getReferenceById
        Mockito.when(repository.getReferenceById(idExistente)).thenReturn(usuario);
        Mockito.when(repository.getReferenceById(idInexistente)).thenThrow(EntidadeNaoEncontradaException.class);

        //Mock do delete
        Mockito.doNothing().when(repository).deleteById(idExistente);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idInexistente);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idDependente);
    }

    //Teste do método findAll
    @Test
    public void findAllDeveriaRetornarUmaLista(){
        List<UsuarioDto> resultado = service.buscarTodos();
        Assertions.assertNotNull(resultado);
    }

    //Teste do método findById
    @Test
    public void findByIdDeveriaRetornarUmRegistroDoBD(){
        UsuarioDto resultado = service.buscarPorId(idExistente);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método findById retornando uma Exceção
    @Test
    public void findByIdDeveriaRetornarUmaExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(idInexistente);
        });
    }

    // Teste do método save
    @Test
    public void saveDeveriaPersistirNoBD(){
        UsuarioDto resultado = service.inserir(dto);
        Assertions.assertNotNull(resultado);
    }

    // Teste do método update
    @Test
    public void updateDeveriaAtualizarUmRegistro(){
        UsuarioDto resultado = service.atualizar(idExistente, dto);
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
        Assertions.assertDoesNotThrow(() -> service.excluir(idExistente));
        Mockito.verify(repository, Mockito.times(1)).deleteById(idExistente);
    }

    // Teste do método delete com exceção de recurso não encontrado
    @Test
    public void deleteDeveriaLancarExcecao(){
        Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> service.excluir(idInexistente));
        Mockito.verify(repository, Mockito.times(1)).deleteById(idInexistente);
    }

    // Teste do método delete com exceção de integridade do BD
    @Test
    public void deleteDeveriaLancarExcecaoDeIntegridadeDeBD(){
        Assertions.assertThrows(BancoDeDadosException.class, () -> service.excluir(idDependente));
        Mockito.verify(repository, Mockito.times(1)).deleteById(idDependente);
    }


}
