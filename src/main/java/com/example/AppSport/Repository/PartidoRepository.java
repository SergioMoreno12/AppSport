package com.example.AppSport.Repository;

import com.example.AppSport.DTO.ResultadoPartidoDTO;
import com.example.AppSport.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    // CONSULTA NATIVA: resultados de partidos con nombres de equipos
    @Query(value = "SELECT p.id_partido AS idPartido, p.fecha AS fecha, " +
            "p.estadio AS estadio, " +
            "el.nombre AS equipoLocal, ev.nombre AS equipoVisita, " +
            "p.goles_local AS golesLocal, p.goles_visita AS golesVisita " +
            "FROM partido p " +
            "INNER JOIN equipo el ON p.equipo_local = el.id_equipo " +
            "INNER JOIN equipo ev ON p.equipo_visita = ev.id_equipo " +
            "ORDER BY p.fecha DESC",
            nativeQuery = true)
    List<ResultadoPartidoDTO> findResultadosPartidos();
}