package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.RoleDto;
import br.com.dh.clinica.dtos.UsuarioAtualizarDto;
import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.dtos.UsuarioInserirDto;
import br.com.dh.clinica.entities.Role;
import br.com.dh.clinica.entities.Usuario;
import br.com.dh.clinica.repositories.RoleRepository;
import br.com.dh.clinica.repositories.UsuarioRepository;
import br.com.dh.clinica.services.exceptions.BancoDeDadosException;
import br.com.dh.clinica.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UsuarioDto> buscarTodos(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return usuarioList.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDto buscarPorId(Integer id){
        Optional<Usuario> objeto = usuarioRepository.findById(id);
        Usuario entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new UsuarioDto(entidade);
    }

    public void excluir(Integer id){
        try {
            usuarioRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    "Exclusão impossível: Registro " + id + " não encontrado em sua base de dados!"
            );
        }
        catch (DataIntegrityViolationException e){
            throw new BancoDeDadosException(
                    "Violação de integridade: Registro " + id + " está inserido em outro registro!"
            );
        }
    }

    @Transactional
    public UsuarioDto inserir(UsuarioInserirDto dto){
        Usuario entidade = new Usuario();
        copiarDtoParaEntidade(dto, entidade);
        entidade.setSenha(passwordEncoder.encode(dto.getSenha()));//dh2022
        entidade = usuarioRepository.save(entidade);
        return new UsuarioDto(entidade);
    }

    @Transactional
    public UsuarioDto atualizar(Integer id, UsuarioAtualizarDto dto){
       try {
           Usuario entidade = usuarioRepository.getReferenceById(id);
           copiarDtoParaEntidade(dto, entidade);
           entidade = usuarioRepository.save(entidade);
           return new UsuarioDto(entidade);
       }
       catch (EntityNotFoundException e){
           throw new EntidadeNaoEncontradaException(
                   "Registro " + id + " não encontrado em sua base de dados!"
           );
       }
    }

    private void copiarDtoParaEntidade(UsuarioDto dto, Usuario entidade){
        entidade.setPrimeroNome(dto.getPrimeroNome());
        entidade.setUltimoNome(dto.getUltimoNome());
        entidade.setEmail(dto.getEmail());

        entidade.getRoles().clear();
        for (RoleDto roleDto : dto.getRoles()){
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entidade.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.buscarPorEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Você digitou um e-mail inválido!");
        }
        return usuario;
    }
}
