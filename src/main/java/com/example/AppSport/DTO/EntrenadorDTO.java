package com.example.AppSport.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntrenadorDTO {

    private String nombre;
    private String especialidad;
    private Long idEquipo;
}