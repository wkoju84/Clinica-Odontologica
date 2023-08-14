package br.com.dh.clinica.repositories;

import br.com.dh.clinica.entities.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository <Dentista, Integer> {
}
