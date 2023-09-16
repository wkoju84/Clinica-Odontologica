package br.com.dh.clinica.controllers;

import br.com.dh.clinica.dtos.RoleDto;
import br.com.dh.clinica.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
