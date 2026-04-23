package com.example.AppSport.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "partido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Long idPartido;

    private LocalDate fecha;

    @Column(length = 100)
    private String estadio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local")
    @JsonIgnoreProperties({"jugadores", "entrenadores", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visita")
    @JsonIgnoreProperties({"jugadores", "entrenadores", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Equipo equipoVisita;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visita")
    private Integer golesVisita;
}