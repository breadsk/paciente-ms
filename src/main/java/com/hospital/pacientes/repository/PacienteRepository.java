package com.hospital.pacientes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hospital.pacientes.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDocumento(String documento);
}
