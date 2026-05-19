package com.valledelsol.emergencias.controller;

import com.valledelsol.emergencias.model.Emergencia;
import com.valledelsol.emergencias.repository.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergencias")
public class EmergenciaController {

    @Autowired
    private EmergenciaRepository repository;

    @GetMapping
    public List<Emergencia> listarTodo() {
        return repository.findAll();
    }

    @PostMapping
    public Emergencia guardar(@RequestBody Emergencia emergencia) {
        return repository.save(emergencia);
    }
}