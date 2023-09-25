package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.UserDto;
import br.com.dh.clinica.dtos.UserInsertDto;
import br.com.dh.clinica.dtos.UserUpdateDto;
import br.com.dh.clinica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> buscarTodos() {
        List<UserDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> buscarPorId(@PathVariable Integer id) {
        UserDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> inserir(@RequestBody UserInsertDto dto) {
        UserDto newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> atualizar(@PathVariable Integer id, @RequestBody UserUpdateDto dto) {
        UserDto newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

}
