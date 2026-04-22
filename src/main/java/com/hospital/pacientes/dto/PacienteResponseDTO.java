package com.hospital.pacientes.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteResponseDTO {

    private Long id;
    private String nombre;
    private String documento;
    private LocalDate fechaNacimiento;
}
