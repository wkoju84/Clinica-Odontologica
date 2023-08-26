package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.dtos.PacienteDto;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Paciente;
import br.com.dh.clinica.repositories.EnderecoRepository;
import br.com.dh.clinica.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(readOnly = true)
    public List<PacienteDto> buscarTodos(){
        List<Paciente> pacienteList = pacienteRepository.findAll();
        return pacienteList.stream().map(PacienteDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteDto buscarPorId(Integer id){
        Optional<Paciente> objeto = pacienteRepository.findById(id);
        Paciente entidade = objeto.get();
        return new PacienteDto(entidade);
    }

    public void excluir(Integer id){
        pacienteRepository.deleteById(id);
    }

    @Transactional
    public PacienteDto inserir(PacienteDto dto){
        Paciente entidade = new Paciente();
        copiarDtoParaEntidade(dto, entidade);
        entidade = pacienteRepository.save(entidade);
        return new PacienteDto(entidade);
    }

    @Transactional
    public PacienteDto atualizar(Integer id, PacienteDto dto) {
        Paciente entidade = pacienteRepository.getReferenceById(id);
        copiarDtoParaEntidade(dto, entidade);
        entidade = pacienteRepository.save(entidade);
        return new PacienteDto(entidade);
    }

    private void copiarDtoParaEntidade(PacienteDto dto, Paciente entidade){
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCpf(dto.getCpf());
        entidade.setDataCadastro(dto.getDataCadastro());
    }
}
