package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository <Consulta, Integer> {
}
