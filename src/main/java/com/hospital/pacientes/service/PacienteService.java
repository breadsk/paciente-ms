package com.hospital.pacientes.service;

import com.hospital.pacientes.dto.PacienteResponseDTO;
import com.hospital.pacientes.model.Paciente;
import com.hospital.pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        return mapearADto(paciente);
    }

    public List<PacienteResponseDTO> obtenerTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    // Método para guardar pacientes
    public PacienteResponseDTO guardar(Paciente paciente) {
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return mapearADto(pacienteGuardado);
    }

    // Metodo para actualizar pacientes
    public PacienteResponseDTO actualizar(Long id, Paciente detallesPaciente) {
        // 1.Verificamos que el paciente existe
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ese id: " + id));

        // Actualizamos los datos permitidos
        pacienteExistente.setNombre(detallesPaciente.getNombre());
        pacienteExistente.setDocumento(detallesPaciente.getDocumento());
        pacienteExistente.setFechaNacimiento(detallesPaciente.getFechaNacimiento());

        // Guardamos y devolcemos como DTO
        Paciente pacienteActualizado = pacienteRepository.save(pacienteExistente);
        return mapearADto(pacienteActualizado);
    }

    // Metodo para eliminar
    public void eliminar(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No hay paciente con ese id: " + id));

        pacienteRepository.delete(paciente);
    }

    private PacienteResponseDTO mapearADto(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setDocumento(paciente.getDocumento());
        dto.setFechaNacimiento(paciente.getFechaNacimiento());

        return dto;
    }

}
