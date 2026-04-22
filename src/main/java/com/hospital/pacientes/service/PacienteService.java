package com.hospital.pacientes.service;

import com.hospital.pacientes.dto.PacienteResponseDTO;
import com.hospital.pacientes.model.Paciente;
import com.hospital.pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteResponseDTO obtenerPorId(Long id) {
        // Buscamos la entidad de la base de datos

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Mapeamos la Entidad al DTO (puedes usar ModelMapper o MapStruct)
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setDocumento(paciente.getDocumento());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());

        return dto;
    }

    // Método para guardar pacientes
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

}
