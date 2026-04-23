package com.example.AppSport.Service;

import com.example.AppSport.DTO.EquipoDTO;
import com.example.AppSport.Model.Equipo;

import java.util.List;

public interface EquipoService {

    List<Equipo> listarTodos();

    Equipo buscarPorId(Long id);

    Equipo guardar(EquipoDTO dto);

    Equipo actualizar(Long id, EquipoDTO dto);

    void eliminar(Long id);

    Integer totalGolesEquipo(Long idEquipo);
}