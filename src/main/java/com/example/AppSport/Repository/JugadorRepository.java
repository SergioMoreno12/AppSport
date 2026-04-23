package com.example.AppSport.Repository;

import com.example.AppSport.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // CONSULTA NATIVA: jugadores de un equipo específico
    @Query(value = "SELECT * FROM jugador WHERE id_equipo = :idEquipo",
            nativeQuery = true)
    List<Jugador> findJugadoresPorEquipo(Long idEquipo);

    // CONSULTA NATIVA: jugadores que han marcado más de X goles
    @Query(value = "SELECT j.* FROM jugador j " +
            "INNER JOIN estadistica_jugador e ON j.id_jugador = e.id_jugador " +
            "GROUP BY j.id_jugador " +
            "HAVING SUM(e.goles) > :minGoles",
            nativeQuery = true)
    List<Jugador> findJugadoresConMasDeXGoles(Integer minGoles);
}