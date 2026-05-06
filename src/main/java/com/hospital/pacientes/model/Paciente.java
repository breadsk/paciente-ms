package com.hospital.pacientes.model;

import jakarta.validation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //1. Mejoras en el nombre
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min=2, max= 100, message = "Debe tener entre 2 a 100 caracteres")
    @Column(nullable = false,length = 100)
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Column(unique = true,nullable = false,length = 20)
    private String documento;

    @Past(message = "La fecha de nacimiento debe ser una fecha en el pasado")
    @Column(name="fecha_nacimiento",nullable = false)
    private LocalDate fechaNacimiento;
}
