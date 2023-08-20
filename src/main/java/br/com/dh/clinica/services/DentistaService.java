package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.DentistaDto;
import br.com.dh.clinica.entities.Dentista;
import br.com.dh.clinica.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Dentista entidade = objeto.get();
        return new DentistaDto(entidade);
    }

    public void excluir(Integer id){
        dentistaRepository.deleteById(id);
    }

    @Transactional
    public DentistaDto inserir(DentistaDto dto){
        Dentista entidade = new Dentista();
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCro(dto.getCro());
        entidade.setAtendeConvenio(dto.isAtendeConvenio());
        entidade = dentistaRepository.save(entidade);
        return new DentistaDto(entidade);
    }

    @Transactional
    public DentistaDto atualizar(Integer id, DentistaDto dto){
        Dentista entidade = dentistaRepository.getReferenceById(id);
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCro(dto.getCro());
        entidade.setAtendeConvenio(dto.isAtendeConvenio());
        entidade = dentistaRepository.save(entidade);
        return new DentistaDto(entidade);
    }
}
