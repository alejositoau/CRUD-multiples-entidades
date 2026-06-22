package com.sena.database_connection.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.database_connection.service.ReservaService;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReservaService reservaService;

    // GET /api/reportes/ocupacion?fecha=2026-06-15 -> Reporte de porcentaje de ocupación institucional
    @GetMapping("/ocupacion")
    public ResponseEntity<List<Map<String, Object>>> obtenerReporteOcupacion(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(reservaService.generarReporteOcupacion(fecha));
    }
}