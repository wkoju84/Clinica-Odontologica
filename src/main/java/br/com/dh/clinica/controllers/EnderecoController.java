package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> buscarTodosOsEnderecos(){
        List<EnderecoDto> enderecoDtoList = enderecoService.buscarTodos();
        return ResponseEntity.ok().body(enderecoDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EnderecoDto> buscarEnderecoPorId(@PathVariable Integer id){
        EnderecoDto dto = enderecoService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Integer id){
        enderecoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> inserirEndereco(@RequestBody EnderecoDto dto){
        dto = enderecoService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@PathVariable Integer id,
                                                         @RequestBody EnderecoDto dto){
        dto = enderecoService.atualizar(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
