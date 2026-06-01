package com.valledelsol.emergencias.controller;

import com.valledelsol.emergencias.model.Emergencia;
import com.valledelsol.emergencias.repository.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emergencias")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite la conexión limpia desde Ionic
public class EmergenciaController {

    @Autowired
    private EmergenciaRepository repository;

    // 1. Obtener todas las emergencias (Para mostrar los pines en el mapa)
    // URL: GET https://backend-0-valle.onrender.com/api/emergencias
    @GetMapping
    public List<Emergencia> listarTodo() {
        return repository.findAll();
    }

    // 2. Obtener una emergencia por ID
    // URL: GET https://backend-0-valle.onrender.com/api/emergencias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Emergencia> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear reporte (Ruta explícita para evitar errores de barra '/' en Ionic)
    // URL: POST https://backend-0-valle.onrender.com/api/emergencias/crear
    @PostMapping("/crear")
    public Emergencia guardar(@RequestBody Emergencia emergencia) {
        // REGLA DE NEGOCIO: El sistema asigna "PENDIENTE" por defecto
        emergencia.setEstado("PENDIENTE");
        // La latitud, longitud y tipo vienen en el cuerpo del JSON desde el frontend
        return repository.save(emergencia);
    }

    // 4. Actualizar estado (Optimizado con Map para evitar fallos de String plano)
    // URL: PUT https://backend-0-valle.onrender.com/api/emergencias/{id}/estado
    @PutMapping("/{id}/estado")
    public ResponseEntity<Emergencia> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return repository.findById(id)
                .map(emergencia -> {
                    String nuevoEstado = body.get("estado");
                    if (nuevoEstado != null) {
                        emergencia.setEstado(nuevoEstado);
                    }
                    return ResponseEntity.ok(repository.save(emergencia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar reporte (Opcional, para limpieza de datos)
    // URL: DELETE https://backend-0-valle.onrender.com/api/emergencias/{id}
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