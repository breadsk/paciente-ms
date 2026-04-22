package com.hospital.pacientes.controller;

import com.hospital.pacientes.dto.PacienteResponseDTO;
import com.hospital.pacientes.model.Paciente;
import com.hospital.pacientes.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // Este es el metodo que usa Feign Client desde citas
    @GetMapping("/{id}")
    public PacienteResponseDTO obtenerPaciente(@PathVariable Long id) {
        return pacienteService.obtenerPorId(id);
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

}
