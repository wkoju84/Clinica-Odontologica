package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.UsuarioAtualizarDto;
import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.dtos.UsuarioInserirDto;
import br.com.dh.clinica.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodosOsUsuarios(){
        List<UsuarioDto> usuarioDtoList = usuarioService.buscarTodos();
        return ResponseEntity.ok().body(usuarioDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Integer id){
        UsuarioDto dto = usuarioService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id){
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> inserirUsuario(@RequestBody UsuarioInserirDto dto){
        UsuarioDto novoDto = usuarioService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@PathVariable Integer id,
                                                       @RequestBody UsuarioAtualizarDto dto){
        UsuarioDto novoDto = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok().body(novoDto);
    }
}
