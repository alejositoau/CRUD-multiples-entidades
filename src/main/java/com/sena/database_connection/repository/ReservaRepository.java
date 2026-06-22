package com.sena.database_connection.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.database_connection.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Regla 1: Sin cruces de horario. Retorna cuántas reservas se cruzan con el rango dado[cite: 17, 35].
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.ambiente.id = :ambienteId AND r.estado = 'ACTIVA' AND r.fechaInicio < :fin AND r.fechaFin > :inicio")
    long contarCruces(
            @Param("ambienteId") Long ambienteId, 
            @Param("inicio") LocalDateTime inicio, 
            @Param("fin") LocalDateTime fin
    );

    // Regla 5: Límite por instructor (máximo 3 al día).
    // Pasamos el inicio y fin del día para evitar problemas de compatibilidad con funciones nativas de base de datos.
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.nombreInstructor = :instructor AND r.estado = 'ACTIVA' AND r.fechaInicio >= :inicioDia AND r.fechaInicio <= :finDia")
    long contarReservasInstructorEnDia(
            @Param("instructor") String instructor, 
            @Param("inicioDia") LocalDateTime inicioDia, 
            @Param("finDia") LocalDateTime finDia
    );
    
    // Para el endpoint: Ver las reservas activas de un ambiente en una fecha específica.
    @Query("SELECT r FROM Reserva r WHERE r.ambiente.id = :ambienteId AND r.estado = 'ACTIVA' AND r.fechaInicio >= :inicioDia AND r.fechaInicio <= :finDia")
    List<Reserva> findReservasActivasPorAmbienteYFecha(
            @Param("ambienteId") Long ambienteId, 
            @Param("inicioDia") LocalDateTime inicioDia, 
            @Param("finDia") LocalDateTime finDia
    );
}
