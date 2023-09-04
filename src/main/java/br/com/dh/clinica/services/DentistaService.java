package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.repositories.DentistaRepository;
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
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Transactional(readOnly = true)
    public List<DentistaDto> buscarTodos(){
        List<Dentista> dentistaList = dentistaRepository.findAll();
        return dentistaList.stream().map(DentistaDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DentistaDto buscarPorId(Integer id){
        Optional<Dentista> objeto = dentistaRepository.findById(id);
        //Dentista entidade = objeto.get(); Sem utilizar exceptions
        Dentista entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new DentistaDto(entidade);
    }

    public void excluir(Integer id){
        try {
            dentistaRepository.deleteById(id);
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
    public DentistaDto inserir(DentistaDto dto){
        Dentista entidade = new Dentista();
        copiarDtoParaEntidade(dto, entidade);
        entidade = dentistaRepository.save(entidade);
        return new DentistaDto(entidade);
    }

    @Transactional
    public DentistaDto atualizar(Integer id, DentistaDto dto){
        try {
            Dentista entidade = dentistaRepository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entidade);
            entidade = dentistaRepository.save(entidade);
            return new DentistaDto(entidade);
        }
        catch (EntityNotFoundException e){
            throw new EntidadeNaoEncontradaException(
                    "Registro " + id + " não encontrado em sua base de dados!"
            );
        }
    }

    private void copiarDtoParaEntidade(DentistaDto dto, Dentista entidade){
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCro(dto.getCro());
        entidade.setAtendeConvenio(dto.isAtendeConvenio());
    }
}
