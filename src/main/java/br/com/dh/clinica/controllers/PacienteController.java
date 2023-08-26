package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.dtos.PacienteDto;
import br.com.dh.clinica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Integer id){
        PacienteDto dto = pacienteService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Integer id){
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PacienteDto> inserirPaciente(@RequestBody PacienteDto dto){
        dto = pacienteService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PacienteDto> atualizarEndereco(@PathVariable Integer id,
                                                         @RequestBody PacienteDto dto){
        dto = pacienteService.atualizar(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
