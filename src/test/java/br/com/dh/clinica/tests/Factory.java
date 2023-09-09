package br.com.dh.clinica.tests;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;

public class Factory {

    public static Dentista criarDentista(){

        Dentista dentista = new Dentista(null, "Sofia Santos", "sofsantos@gmail.com", 56874, true);
        return dentista;
    }

    public static DentistaDto criarDentistaDto(){
        Dentista dentista = criarDentista();
        return new DentistaDto(dentista);
    }
}
