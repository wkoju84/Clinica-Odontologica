package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Integer idExistente;
    private Integer idInexistente;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
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
