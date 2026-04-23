package com.example.AppSport.Controller;

import com.example.AppSport.DTO.JugadorDTO;
import com.example.AppSport.Model.Jugador;
import com.example.AppSport.Service.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // GET /api/jugadores
    @GetMapping
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(jugadorService.listarTodos());
    }

    // GET /api/jugadores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jugadorService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/jugadores
    @PostMapping
    public ResponseEntity<Jugador> crear(@RequestBody JugadorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jugadorService.guardar(dto));
    }

    // PUT /api/jugadores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizar(@PathVariable Long id, @RequestBody JugadorDTO dto) {
        try {
            return ResponseEntity.ok(jugadorService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/jugadores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            jugadorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CONSULTA NATIVA: GET /api/jugadores/equipo/{idEquipo}
    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<Jugador>> jugadoresPorEquipo(@PathVariable Long idEquipo) {
        return ResponseEntity.ok(jugadorService.jugadoresPorEquipo(idEquipo));
    }

    // CONSULTA NATIVA: GET /api/jugadores/goles-mayor-a/{goles}
    @GetMapping("/goles-mayor-a/{goles}")
    public ResponseEntity<List<Jugador>> jugadoresConMasGoles(@PathVariable Integer goles) {
        return ResponseEntity.ok(jugadorService.jugadoresConMasDeXGoles(goles));
    }
}