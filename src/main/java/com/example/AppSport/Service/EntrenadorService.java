package com.example.AppSport.Service;

import com.example.AppSport.DTO.EntrenadorDTO;
import com.example.AppSport.Model.Entrenador;

import java.util.List;

public interface EntrenadorService {

    List<Entrenador> listarTodos();

    Entrenador buscarPorId(Long id);

    Entrenador guardar(EntrenadorDTO dto);

    Entrenador actualizar(Long id, EntrenadorDTO dto);

    void eliminar(Long id);
}