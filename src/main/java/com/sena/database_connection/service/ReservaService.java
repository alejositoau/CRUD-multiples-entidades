package com.sena.database_connection.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.database_connection.exception.ReglaNegocioException;
import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.model.EstadoReserva;
import com.sena.database_connection.model.Reserva;
import com.sena.database_connection.repository.AmbienteRepository;
import com.sena.database_connection.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Transactional
    public Reserva crearReserva(Reserva reserva) {
        Ambiente ambiente = ambienteRepository.findById(reserva.getAmbiente().getId())
                .orElseThrow(() -> new ReglaNegocioException("El ambiente especificado no existe."));

        reserva.setAmbiente(ambiente);

        if (reserva.getFechaInicio().isBefore(LocalDateTime.now())) {
            throw new ReglaNegocioException("La fecha de inicio no puede ser en el pasado.");
        }

        if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio())) {
            throw new ReglaNegocioException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }

        if (!ambiente.getActivo()) {
            throw new ReglaNegocioException("No se puede reservar porque el ambiente se encuentra INACTIVO.");
        }

        if (reserva.getNumeroAprendices() > ambiente.getCapacidad()) {
            throw new ReglaNegocioException("El número de aprendices (" + reserva.getNumeroAprendices() 
                    + ") supera la capacidad máxima del ambiente (" + ambiente.getCapacidad() + ").");
        }

        LocalTime horaInicio = reserva.getFechaInicio().toLocalTime();
        LocalTime horaFin = reserva.getFechaFin().toLocalTime();
        LocalTime limiteInicio = LocalTime.of(6, 0);
        LocalTime limiteFin = LocalTime.of(22, 0);

        if (horaInicio.isBefore(limiteInicio) || horaFin.isAfter(limiteFin)) {
            throw new ReglaNegocioException("Las reservas solo están permitidas dentro del horario institucional (06:00 a 22:00).");
        }

        long horasDuracion = Duration.between(reserva.getFechaInicio(), reserva.getFechaFin()).toHours();
        if (horasDuracion < 1 || horasDuracion > 4) {
            throw new ReglaNegocioException("La duración de la reserva debe ser de mínimo 1 hora y máximo 4 horas.");
        }

        LocalDateTime inicioDia = reserva.getFechaInicio().toLocalDate().atStartOfDay();
        LocalDateTime finDia = reserva.getFechaInicio().toLocalDate().atTime(23, 59, 59);
        long reservasDelDia = reservaRepository.contarReservasInstructorEnDia(reserva.getNombreInstructor(), inicioDia, finDia);
        if (reservasDelDia >= 3) {
            throw new ReglaNegocioException("El instructor " + reserva.getNombreInstructor() 
                    + " ya alcanzó el límite máximo de 3 reservas para este día.");
        }

        long cruces = reservaRepository.contarCruces(ambiente.getId(), reserva.getFechaInicio(), reserva.getFechaFin());
        if (cruces > 0) {
            throw new ReglaNegocioException("El ambiente ya se encuentra reservado en el rango de tiempo solicitado.", HttpStatus.CONFLICT);
        }

        reserva.setEstado(EstadoReserva.ACTIVA);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservasActivasPorAmbienteYFecha(Long ambienteId, LocalDateTime fecha) {
        LocalDateTime inicioDia = fecha.toLocalDate().atStartOfDay();
        LocalDateTime finDia = fecha.toLocalDate().atTime(23, 59, 59);
        return reservaRepository.findReservasActivasPorAmbienteYFecha(ambienteId, inicioDia, finDia);
    }

    @Transactional
    public Reserva cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ReglaNegocioException("La reserva especificada no existe."));

        if (LocalDateTime.now().isAfter(reserva.getFechaInicio().minusHours(2))) {
            throw new ReglaNegocioException("Una reserva solo puede cancelarse si faltan al menos 2 horas para su inicio.");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }

    public List<Ambiente> listarAmbientesDisponibles(LocalDateTime inicio, LocalDateTime fin) {
        if (!fin.isAfter(inicio)) {
            throw new ReglaNegocioException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }
        return ambienteRepository.findAmbientesDisponibles(inicio, fin);
    }

    public java.util.List<java.util.Map<String, Object>> generarReporteOcupacion(java.time.LocalDate fecha) {
        LocalDateTime inicioDia = fecha.atStartOfDay();
        LocalDateTime finDia = fecha.atTime(23, 59, 59);

        List<Ambiente> todosLosAmbientes = ambienteRepository.findAll();
        java.util.List<java.util.Map<String, Object>> reporte = new java.util.ArrayList<>();

        for (Ambiente amb : todosLosAmbientes) {
            List<Reserva> reservasDelDia = reservaRepository.findReservasActivasPorAmbienteYFecha(amb.getId(), inicioDia, finDia);
            
            long minutosReservados = 0;
            for (Reserva res : reservasDelDia) {
                minutosReservados += Duration.between(res.getFechaInicio(), res.getFechaFin()).toMinutes();
            }

            double horasReservadas = minutosReservados / 60.0;
            double porcentajeOcupacion = (horasReservadas / 16.0) * 100;

            java.util.Map<String, Object> fila = new java.util.HashMap<>();
            fila.put("ambienteId", amb.getId());
            fila.put("nombre", amb.getNombre());
            fila.put("horasReservadas", horasReservadas);
            fila.put("porcentajeOcupacion", Math.min(porcentajeOcupacion, 100.0)); // Toque de seguridad

            reporte.add(fila);
        }

        return reporte;
    }

}