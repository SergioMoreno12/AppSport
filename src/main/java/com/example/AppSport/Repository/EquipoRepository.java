package com.example.AppSport.Repository;

import com.example.AppSport.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    // CONSULTA NATIVA: total de goles marcados por un equipo (local + visitante)
    @Query(value = "SELECT COALESCE(SUM(CASE " +
            "  WHEN p.equipo_local = :idEquipo THEN p.goles_local " +
            "  WHEN p.equipo_visita = :idEquipo THEN p.goles_visita " +
            "  ELSE 0 END), 0) " +
            "FROM partido p " +
            "WHERE p.equipo_local = :idEquipo OR p.equipo_visita = :idEquipo",
            nativeQuery = true)
    Integer totalGolesPorEquipo(Long idEquipo);
}