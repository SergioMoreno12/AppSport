package com.example.AppSport.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estadistica_jugador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EstadisticaJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Long idEstadistica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador")
    @JsonIgnoreProperties({"equipo", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Jugador jugador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido")
    @JsonIgnoreProperties({"equipoLocal", "equipoVisita", "hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Partido partido;

    @Column(name = "minutos_jugados")
    private Integer minutosJugados;

    private Integer goles;
    private Integer asistencias;

    @Column(name = "tarjetas_amarillas")
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas")
    private Integer tarjetasRojas;
}