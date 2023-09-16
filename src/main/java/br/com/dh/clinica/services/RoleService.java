package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.RoleDto;
import br.com.dh.clinica.entities.Role;
import br.com.dh.clinica.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public List<RoleDto> buscarTodos(){
        List<Role> list = repository.findAll();
        return list.stream().map(RoleDto::new).collect(Collectors.toList());
    }
}
