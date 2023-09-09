package br.com.dh.clinica.tests;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.entities.Endereco;

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
}
