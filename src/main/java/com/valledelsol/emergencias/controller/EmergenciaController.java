package com.valledelsol.emergencias.controller;

import com.valledelsol.emergencias.model.Emergencia;
import com.valledelsol.emergencias.repository.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergencias")
@CrossOrigin(origins = "*")
public class EmergenciaController {

    @Autowired
    private EmergenciaRepository repository;

    @GetMapping
    public List<Emergencia> listarTodo() {
        return repository.findAll();
    }

    @PostMapping
    public Emergencia guardar(@RequestBody Emergencia emergencia) {
        if (emergencia.getEstado() == null || emergencia.getEstado().isBlank()) {
            emergencia.setEstado("PENDIENTE");
        }

        return repository.save(emergencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emergencia> actualizar(@PathVariable Long id, @RequestBody Emergencia datos) {
        return repository.findById(id)
                .map(emergencia -> {
                    if (datos.getTipo() != null) {
                        emergencia.setTipo(datos.getTipo());
                    }

                    if (datos.getDescripcion() != null) {
                        emergencia.setDescripcion(datos.getDescripcion());
                    }

                    if (datos.getUbicacion() != null) {
                        emergencia.setUbicacion(datos.getUbicacion());
                    }

                    if (datos.getEstado() != null) {
                        emergencia.setEstado(datos.getEstado());
                    }

                    Emergencia actualizada = repository.save(emergencia);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}