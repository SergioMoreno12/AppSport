package com.example.AppSport.Service;

import com.example.AppSport.DTO.EstadisticaJugadorDTO;
import com.example.AppSport.Model.EstadisticaJugador;

import java.util.List;

public interface EstadisticaJugadorService {

    List<EstadisticaJugador> listarTodas();

    EstadisticaJugador buscarPorId(Long id);

    EstadisticaJugador guardar(EstadisticaJugadorDTO dto);

    EstadisticaJugador actualizar(Long id, EstadisticaJugadorDTO dto);

    void eliminar(Long id);
}