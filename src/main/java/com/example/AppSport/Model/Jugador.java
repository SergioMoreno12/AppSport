package com.example.AppSport.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "jugador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Long idJugador;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String posicion;

    private Integer dorsal;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    @Column(length = 100)
    private String nacionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    @JsonIgnoreProperties({"jugadores", "entrenadores", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Equipo equipo;
}