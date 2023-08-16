package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente, Integer> {
}
