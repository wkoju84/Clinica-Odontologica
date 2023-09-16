package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.RoleDto;
import br.com.dh.clinica.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDto>> buscarTodasAsRoles(){
        List<RoleDto> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDto> buscarRolePorId(@PathVariable Integer id){
        RoleDto dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirRole(@PathVariable Integer id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<RoleDto> inserirRole(@RequestBody RoleDto dto){
        dto = service.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
