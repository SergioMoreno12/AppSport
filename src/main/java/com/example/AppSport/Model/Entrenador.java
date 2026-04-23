package com.example.AppSport.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrenador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador")
    private Long idEntrenador;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    @JsonIgnoreProperties({"jugadores", "entrenadores", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Equipo equipo;
}