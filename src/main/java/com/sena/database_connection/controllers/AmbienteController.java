package com.sena.database_connection.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.service.AmbienteService;
import com.sena.database_connection.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteService ambienteService;

    @Autowired
    private ReservaService reservaService;

    // POST /api/ambientes -> Registrar un ambiente
    @PostMapping
    public ResponseEntity<Ambiente> registrarAmbiente(@Valid @RequestBody Ambiente ambiente) {
        Ambiente nuevoAmbiente = ambienteService.guardar(ambiente);
        return new ResponseEntity<>(nuevoAmbiente, HttpStatus.CREATED);
    }

    // GET /api/ambientes -> Listar ambientes
    @GetMapping
    public ResponseEntity<List<Ambiente>> listarAmbientes() {
        return ResponseEntity.ok(ambienteService.listarTodos());
    }

    // GET /api/ambientes/{id}/reservas?fecha=2026-06-15 -> Ver las reservas activas de un ambiente en una fecha
    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<?>> listarReservasPorAmbienteYFecha(
            @PathVariable("id") Long id,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return ResponseEntity.ok(reservaService.listarReservasActivasPorAmbienteYFecha(id, fecha));
    }

    // GET /api/ambientes/disponibles?inicio=...&fin=... -> Listar ambientes libres en un rango de tiempo
    @GetMapping("/disponibles")
    public ResponseEntity<List<Ambiente>> listarDisponibles(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(reservaService.listarAmbientesDisponibles(inicio, fin));
    }
}