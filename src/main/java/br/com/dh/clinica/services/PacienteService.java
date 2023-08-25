package br.com.dh.clinica.services;

import br.com.dh.clinica.dtos.PacienteDto;
import br.com.dh.clinica.entities.Paciente;
import br.com.dh.clinica.repositories.EnderecoRepository;
import br.com.dh.clinica.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
