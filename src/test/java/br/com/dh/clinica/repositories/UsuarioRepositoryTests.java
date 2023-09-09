package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Usuario;
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
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Integer idExistente;
    private Integer idInexistente;

    private Integer countTotalUsuario;
    private List<Usuario> usuarioList;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        countTotalUsuario = 3;
        usuarioList = new ArrayList<>();
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Usuario usuario = Factory.criarUsuario();//Simula um DTO
        usuario = usuarioRepository.save(usuario);

        Assertions.assertNotNull(usuario.getId());
        Assertions.assertEquals(countTotalUsuario + 1, usuario.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        usuarioList = usuarioRepository.findAll();
        Assertions.assertNotNull(usuarioList);
    }

    @Test
    public void buscarPorIdERetornarUmUsuario(){
        Optional<Usuario> resultado = usuarioRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Usuario> resultado = usuarioRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }
}
