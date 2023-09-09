package br.com.dh.clinica.tests;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Usuario;

public class Factory {

    public static Dentista criarDentista(){

        Dentista dentista = new Dentista(null, "Sofia Santos", "sofsantos@gmail.com", 56874, true);
        return dentista;
    }

    public static DentistaDto criarDentistaDto(){
        Dentista dentista = criarDentista();
        return new DentistaDto(dentista);
    }

    public static Endereco criarEndereco(){
        Endereco endereco = new Endereco(null, "Rua das Camélias", "110", "Bairro Novo", "SP", "07781-471", "SP");
        return endereco;
    }

    public static EnderecoDto criarEnderecoDto(){
        Endereco endereco = criarEndereco();
        return new EnderecoDto(endereco);
    }

    public static Usuario criarUsuario(){
        Usuario usuario = new Usuario(null, "Rogério Barbosa", "rogeriobarbosa@hotmail.com", "12345", 1);
        return usuario;
    }

    public static UsuarioDto criarUsuarioDto(){
        Usuario usuario = criarUsuario();
        return new UsuarioDto(usuario);
    }
}
