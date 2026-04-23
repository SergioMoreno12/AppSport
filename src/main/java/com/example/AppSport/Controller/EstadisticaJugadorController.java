package com.example.AppSport.Controller;

import com.example.AppSport.DTO.EstadisticaJugadorDTO;
import com.example.AppSport.Model.EstadisticaJugador;
import com.example.AppSport.Service.EstadisticaJugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaJugadorController {

    private final EstadisticaJugadorService estadisticaService;

    public EstadisticaJugadorController(EstadisticaJugadorService estadisticaService) {
        this.estadisticaService = estadisticaService;
    }

    @GetMapping
    public ResponseEntity<List<EstadisticaJugador>> listar() {
        return ResponseEntity.ok(estadisticaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadisticaJugador> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadisticaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadisticaJugador> crear(@RequestBody EstadisticaJugadorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estadisticaService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadisticaJugador> actualizar(@PathVariable Long id, @RequestBody EstadisticaJugadorDTO dto) {
        try {
            return ResponseEntity.ok(estadisticaService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            estadisticaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}