package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.UsuarioDto;
import br.com.dh.clinica.entities.Usuario;
import br.com.dh.clinica.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Usuario entidade = objeto.get();
        return new UsuarioDto(entidade);
    }

    public void excluir(Integer id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDto inserir(UsuarioDto dto){
        Usuario entidade = new Usuario();
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setSenha(dto.getSenha());
        entidade.setNivelAcesso(dto.getNivelAcesso());
        entidade = usuarioRepository.save(entidade);
        return new UsuarioDto(entidade);
    }

    @Transactional
    public UsuarioDto atualizar(Integer id, UsuarioDto dto){
        Usuario entidade = usuarioRepository.getReferenceById(id);
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setSenha(dto.getSenha());
        entidade.setNivelAcesso(dto.getNivelAcesso());
        entidade = usuarioRepository.save(entidade);
        return new UsuarioDto(entidade);
    }
}
