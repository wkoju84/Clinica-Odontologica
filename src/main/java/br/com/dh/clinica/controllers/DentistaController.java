package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dentistas")
public class DentistaController {
    @Autowired
    DentistaService dentistaService;

    @GetMapping
    public ResponseEntity<List<DentistaDto>> buscarTodosOsDentistas(){
        List<DentistaDto> dentistaDtoList = dentistaService.buscarTodos();
        return ResponseEntity.ok().body(dentistaDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DentistaDto> buscarDentistaPorId(@PathVariable Integer id){
        DentistaDto dto = dentistaService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

}
