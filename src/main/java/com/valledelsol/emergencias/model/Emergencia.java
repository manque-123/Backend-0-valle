package com.valledelsol.emergencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "emergencias") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Emergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    private String descripcion;
    
    private String ubicacion;

    private String estado; 
}