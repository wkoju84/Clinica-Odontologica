package br.com.dh.clinica.tests;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Paciente;
import br.com.dh.clinica.entities.Usuario;

public class Factory {

    public static Dentista criarDentista(){

        return new Dentista(1, "Sofia Santos", "sofsantos@gmail.com", 56874, true);
    }

    public static DentistaDto criarDentistaDto(){
        Dentista dentista = criarDentista();
        return new DentistaDto(dentista);
    }

    public static Endereco criarEndereco(){
        return new Endereco(null, "Rua das Camélias", "110", "Bairro Novo", "SP", "07781-471", "SP");
    }

    public static EnderecoDto criarEnderecoDto(){
        Endereco endereco = criarEndereco();
        return new EnderecoDto(endereco);
    }

    public static Usuario criarUsuario(){
        return new Usuario(null, "Rogério Barbosa", "rogeriobarbosa@hotmail.com", "12345", 1);
    }

    public static UsuarioDto criarUsuarioDto(){
        Usuario usuario = criarUsuario();
        return new UsuarioDto(usuario);
    }

//    public static Paciente criarPaciente(){
//        Paciente paciente = new Paciente(null, "Eder Jofre", "ederjo@ig.com", "123456789-05", 2021-01-01);
//        return paciente;
//    }
}
