package com.sena.database_connection.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.database_connection.model.Ambiente;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

    // Busca ambientes activos que NO tengan reservas cruzadas en ese rango de tiempo
    @Query("SELECT a FROM Ambiente a WHERE a.activo = true AND a.id NOT IN " +
           "(SELECT r.ambiente.id FROM Reserva r WHERE r.estado = 'ACTIVA' AND r.fechaInicio < :fin AND r.fechaFin > :inicio)")
    List<Ambiente> findAmbientesDisponibles(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}