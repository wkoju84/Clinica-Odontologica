package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.RoleDto;
import br.com.dh.clinica.entities.Role;
import br.com.dh.clinica.repositories.RoleRepository;
import br.com.dh.clinica.services.exceptions.BancoDeDadosException;
import br.com.dh.clinica.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<Role> list = repository.findAll();
        return list.stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleDto findById(Integer id) {
        Optional<Role> object = repository.findById(id);
        Role entity = object.orElseThrow(() ->
                new EntidadeNaoEncontradaException("Este ID não existe em nosso sistema."));
        return new RoleDto(entity);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException("Id não encontrado: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new BancoDeDadosException("Violação de integridade no banco de dados.");
        }
    }

    @Transactional
    public RoleDto insert(RoleDto dto) {
        Role entity = new Role();
        entity.setAuthority(dto.getAuthority());
        entity = repository.save(entity);
        return new RoleDto(entity);
    }

    @Transactional
    public RoleDto update(Integer id, RoleDto dto) {
        try {
            Role entity = repository.getReferenceById(id);
            entity.setAuthority(dto.getAuthority());
            entity = repository.save(entity);
            return new RoleDto(entity);
        }
        catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException("Id não encontrado: " + id);
        }
    }
}
