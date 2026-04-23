package com.example.AppSport.Controller;

import com.example.AppSport.DTO.PartidoDTO;
import com.example.AppSport.DTO.ResultadoPartidoDTO;
import com.example.AppSport.Model.Partido;
import com.example.AppSport.Service.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping
    public ResponseEntity<List<Partido>> listar() {
        return ResponseEntity.ok(partidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(partidoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Partido> crear(@RequestBody PartidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidoService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> actualizar(@PathVariable Long id, @RequestBody PartidoDTO dto) {
        try {
            return ResponseEntity.ok(partidoService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            partidoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CONSULTA NATIVA: GET /api/partidos/resultados
    @GetMapping("/resultados")
    public ResponseEntity<List<ResultadoPartidoDTO>> resultados() {
        return ResponseEntity.ok(partidoService.resultadosConNombres());
    }
}