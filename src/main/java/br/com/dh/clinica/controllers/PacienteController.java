package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.PacienteDto;
import br.com.dh.clinica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarTodosOsPacientes(){
        List<PacienteDto> pacienteDtoList = pacienteService.buscarTodos();
        return ResponseEntity.ok().body(pacienteDtoList);
    }
}
