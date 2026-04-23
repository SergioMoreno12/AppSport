package com.example.AppSport.Service;

import com.example.AppSport.DTO.PartidoDTO;
import com.example.AppSport.DTO.ResultadoPartidoDTO;
import com.example.AppSport.Model.Partido;

import java.util.List;

public interface PartidoService {

    List<Partido> listarTodos();

    Partido buscarPorId(Long id);

    Partido guardar(PartidoDTO dto);

    Partido actualizar(Long id, PartidoDTO dto);

    void eliminar(Long id);

    List<ResultadoPartidoDTO> resultadosConNombres();
}