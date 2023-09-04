package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.EnderecoDto;
import br.com.dh.clinica.dtos.PacienteDto;
import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Paciente;
import br.com.dh.clinica.repositories.EnderecoRepository;
import br.com.dh.clinica.repositories.PacienteRepository;
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
        Paciente entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new PacienteDto(entidade);
    }

    public void excluir(Integer id){
        try {
            pacienteRepository.deleteById(id);
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
    public PacienteDto inserir(PacienteDto dto){
        Paciente entidade = new Paciente();
        copiarDtoParaEntidade(dto, entidade);
        entidade = pacienteRepository.save(entidade);
        return new PacienteDto(entidade);
    }

    @Transactional
    public PacienteDto atualizar(Integer id, PacienteDto dto) {
        try {
            Paciente entidade = pacienteRepository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entidade);
            entidade = pacienteRepository.save(entidade);
            return new PacienteDto(entidade);
        }
        catch (EntityNotFoundException e){
            throw new EntidadeNaoEncontradaException(
                    "Registro " + id + " não encontrado em sua base de dados!"
            );
        }
    }

    private void copiarDtoParaEntidade(PacienteDto dto, Paciente entidade){
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setCpf(dto.getCpf());
        entidade.setDataCadastro(dto.getDataCadastro());
    }
}
