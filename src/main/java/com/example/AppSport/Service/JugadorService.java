package com.example.AppSport.Service;

import com.example.AppSport.DTO.JugadorDTO;
import com.example.AppSport.Model.Jugador;

import java.util.List;

public interface JugadorService {

    List<Jugador> listarTodos();

    Jugador buscarPorId(Long id);

    Jugador guardar(JugadorDTO dto);

    Jugador actualizar(Long id, JugadorDTO dto);

    void eliminar(Long id);

    List<Jugador> jugadoresPorEquipo(Long idEquipo);

    List<Jugador> jugadoresConMasDeXGoles(Integer minGoles);
}