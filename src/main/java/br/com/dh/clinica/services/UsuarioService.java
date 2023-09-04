package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.entities.Usuario;
import br.com.dh.clinica.repositories.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public UsuarioDto inserir(UsuarioDto dto){
        Usuario entidade = new Usuario();
        copiarDtoParaEntidade(dto, entidade);
        entidade = usuarioRepository.save(entidade);
        return new UsuarioDto(entidade);
    }

    @Transactional
    public UsuarioDto atualizar(Integer id, UsuarioDto dto){
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
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setSenha(dto.getSenha());
        entidade.setNivelAcesso(dto.getNivelAcesso());
    }
}
