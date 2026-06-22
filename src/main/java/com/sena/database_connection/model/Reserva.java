package com.sena.database_connection.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ambiente es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY) // LAZY mejora el rendimiento de la consulta
    @JoinColumn(name = "ambiente_id", nullable = false)
    private Ambiente ambiente;

    @NotBlank(message = "El nombre del instructor es obligatorio")
    @Column(name = "nombre_instructor", nullable = false)
    private String nombreInstructor;

    @NotNull(message = "La fecha y hora de inicio son obligatorias")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha y hora de fin son obligatorias")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;

    @NotNull(message = "El número de aprendices es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 aprendiz en la reserva")
    @Column(name = "numero_aprendices", nullable = false)
    private Integer numeroAprendices;

    @NotNull(message = "El estado de la reserva es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;
}