package com.sena.database_connection.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.sena.database_connection.model.Reserva;
import com.sena.database_connection.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
public List<Reserva> listarTodas() {
    return reservaService.findAll();
}
@GetMapping("/{id}")
public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {
    return reservaService.findById(id)
            .map(reserva -> ResponseEntity.ok().body(reserva))
            .orElse(ResponseEntity.notFound().build());
}

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@Valid @RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable("id") Long id) {
        Reserva reservaCancelada = reservaService.cancelarReserva(id);
        return ResponseEntity.ok(reservaCancelada);
    }

    @PutMapping("/{id}")
public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva datosActualizados) {
    return reservaService.findById(id)
            .map(reservaExistente -> {
                reservaExistente.setNombreInstructor(datosActualizados.getNombreInstructor());
                reservaExistente.setFechaInicio(datosActualizados.getFechaInicio());
                reservaExistente.setFechaFin(datosActualizados.getFechaFin());
                reservaExistente.setNumeroAprendices(datosActualizados.getNumeroAprendices());
                reservaExistente.setEstado(datosActualizados.getEstado());
                reservaExistente.setAmbiente(datosActualizados.getAmbiente());
                
                Reserva guardada = reservaService.save(reservaExistente);
                return ResponseEntity.ok().body(guardada);
            })
            .orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
    return reservaService.findById(id)
            .map(reserva -> {
                reservaService.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }


}