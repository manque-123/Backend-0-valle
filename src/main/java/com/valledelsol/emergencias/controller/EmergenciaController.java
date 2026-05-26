package com.valledelsol.emergencias.controller;

import com.valledelsol.emergencias.model.Emergencia;
import com.valledelsol.emergencias.repository.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/emergencias")
@CrossOrigin(origins = "*") // Permite que tu frontend se conecte sin errores de CORS
public class EmergenciaController {

    @Autowired
    private EmergenciaRepository repository;

    // Obtener todas las emergencias (Para mostrar los pines en el mapa)
    @GetMapping
    public List<Emergencia> listarTodo() {
        return repository.findAll();
    }

    // Obtener una emergencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Emergencia> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear reporte (Lo que usa el ciudadano/brigadista desde el móvil)
    @PostMapping
    public Emergencia guardar(@RequestBody Emergencia emergencia) {
        // REGLA DE NEGOCIO: El sistema asigna "PENDIENTE" por defecto
        emergencia.setEstado("PENDIENTE");
        // La latitud y longitud vienen en el cuerpo del JSON desde el frontend
        return repository.save(emergencia);
    }

    // Actualizar estado (Lo que usa el Administrador de la Municipalidad)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Emergencia> actualizarEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        return repository.findById(id)
                .map(emergencia -> {
                    emergencia.setEstado(nuevoEstado);
                    return ResponseEntity.ok(repository.save(emergencia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar reporte (Opcional, para limpieza de datos)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return repository.findById(id)
                .map(emergencia -> {
                    repository.delete(emergencia);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}