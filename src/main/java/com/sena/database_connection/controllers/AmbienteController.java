package com.sena.database_connection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sena.database_connection.model.Ambiente;
import com.sena.database_connection.service.AmbienteService;
import java.util.List;

@RestController
@RequestMapping("/api/ambientes")
@CrossOrigin(origins = "*")
public class AmbienteController {

    @Autowired
    private AmbienteService ambienteService;

    // GET: Listar todos
    @GetMapping
    public List<Ambiente> listarTodos() {
        return ambienteService.findAll();
    }

    // GET: Buscar por ID (Punto C)
    @GetMapping("/{id}")
    public ResponseEntity<Ambiente> obtenerPorId(@PathVariable Long id) {
        return ambienteService.findById(id)
                .map(ambiente -> ResponseEntity.ok().body(ambiente))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear
    @PostMapping
    public ResponseEntity<Ambiente> crearAmbiente(@RequestBody Ambiente ambiente) {
        Ambiente nuevo = ambienteService.save(ambiente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // PUT: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Ambiente> actualizarAmbiente(@PathVariable Long id, @RequestBody Ambiente datosActualizados) {
        return ambienteService.findById(id)
                .map(ambienteExistente -> {
                    ambienteExistente.setNombre(datosActualizados.getNombre());
                    ambienteExistente.setCapacidad(datosActualizados.getCapacidad());
                    ambienteExistente.setTipo(datosActualizados.getTipo());
                    ambienteExistente.setActivo(datosActualizados.getActivo());
                    Ambiente guardado = ambienteService.save(ambienteExistente);
                    return ResponseEntity.ok().body(guardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAmbiente(@PathVariable Long id) {
        return ambienteService.findById(id)
                .map(ambiente -> {
                    ambienteService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}