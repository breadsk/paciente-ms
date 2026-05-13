package com.hospital.pacientes.controller;

import com.hospital.pacientes.dto.PacienteResponseDTO;
import com.hospital.pacientes.model.Paciente;
import com.hospital.pacientes.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.obtenerTodos();
        return ResponseEntity.ok(pacientes);
    }

    // Obtener paciente por ID (usado por Feign Client)
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obtenerPaciente(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.obtenerPorId(id);
        return ResponseEntity.ok(paciente);
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> guardarPaciente(@RequestBody Paciente paciente) {
        PacienteResponseDTO nuevoPaciente = pacienteService.guardar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    // Actualizar un paciente existente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody Paciente paciente) {
        PacienteResponseDTO pacienteActualizado = pacienteService.actualizar(id, paciente);
        return ResponseEntity.ok(pacienteActualizado);
    }

    // Eliminar un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}