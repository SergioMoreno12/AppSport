package com.example.AppSport.DTO;

import java.time.LocalDate;

public interface ResultadoPartidoDTO {

    Long getIdPartido();
    LocalDate getFecha();
    String getEstadio();
    String getEquipoLocal();
    String getEquipoVisita();
    Integer getGolesLocal();
    Integer getGolesVisita();
}