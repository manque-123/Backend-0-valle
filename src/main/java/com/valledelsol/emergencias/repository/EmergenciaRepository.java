package com.valledelsol.emergencias.repository;

import com.valledelsol.emergencias.model.Emergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenciaRepository extends JpaRepository<Emergencia, Long> {
   
}