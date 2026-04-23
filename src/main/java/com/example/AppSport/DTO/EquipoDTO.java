package com.example.AppSport.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipoDTO {

    private String nombre;
    private String ciudad;
    private LocalDate fundacion;
}