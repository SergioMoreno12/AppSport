package com.example.AppSport.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartidoDTO {

    private LocalDate fecha;
    private String estadio;
    private Long idEquipoLocal;
    private Long idEquipoVisita;
    private Integer golesLocal;
    private Integer golesVisita;
}