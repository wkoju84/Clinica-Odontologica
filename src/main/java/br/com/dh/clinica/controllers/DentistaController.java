package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.services.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirDentista(@PathVariable Integer id){
        dentistaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DentistaDto> inserirDentista(@RequestBody DentistaDto dto){
        dto = dentistaService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DentistaDto> atualizaDentista(@PathVariable Integer id,
                                                        @RequestBody DentistaDto dto){
        dto = dentistaService.atualizar(id,dto);
        return ResponseEntity.ok().body(dto);
    }

}
