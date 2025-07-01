package com.perfulandia.soporte_api_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asunto;
    private String descripcion;
    private String estado; // Ej: Abierto, En proceso, Cerrado
    private String prioridad; // Ej: Alta, Media, Baja
    private String fechaCreacion;
}
