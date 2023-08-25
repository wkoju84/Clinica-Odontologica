package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<EnderecoDto> buscarTodos(){
        List<Endereco> enderecoList = enderecoRepository.findAll();
        return enderecoList.stream().map(EnderecoDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EnderecoDto buscarPorId(Integer id){
        Optional<Endereco> objeto = enderecoRepository.findById(id);
        Endereco entidade = objeto.get();
        return new EnderecoDto(entidade);
    }

    public void excluir(Integer id){
        enderecoRepository.deleteById(id);
    }

    @Transactional
    public EnderecoDto inserir(EnderecoDto dto){
        Endereco entidade = new Endereco();
        copiarDtoParaEntidade(dto, entidade);
        entidade = enderecoRepository.save(entidade);
        return new EnderecoDto(entidade);
    }

    @Transactional
    public EnderecoDto atualizar(Integer id, EnderecoDto dto) {
        Endereco entidade = enderecoRepository.getReferenceById(id);
        copiarDtoParaEntidade(dto, entidade);
        entidade = enderecoRepository.save(entidade);
        return new EnderecoDto(entidade);
    }

    private void copiarDtoParaEntidade(EnderecoDto dto, Endereco entidade){
        entidade.setRua(dto.getRua());
        entidade.setNumero(dto.getNumero());
        entidade.setBairro(dto.getBairro());
        entidade.setCidade(dto.getCidade());
        entidade.setCep(dto.getCep());
        entidade.setEstado(dto.getEstado());
    }
}
