package com.example.AppSport.Controller;

import com.example.AppSport.DTO.EquipoDTO;
import com.example.AppSport.Model.Equipo;
import com.example.AppSport.Service.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // GET /api/equipos
    @GetMapping
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(equipoService.listarTodos());
    }

    // GET /api/equipos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/equipos
    @PostMapping
    public ResponseEntity<Equipo> crear(@RequestBody EquipoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.guardar(dto));
    }

    // PUT /api/equipos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizar(@PathVariable Long id, @RequestBody EquipoDTO dto) {
        try {
            return ResponseEntity.ok(equipoService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/equipos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            equipoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // CONSULTA NATIVA: GET /api/equipos/{id}/total-goles
    @GetMapping("/{id}/total-goles")
    public ResponseEntity<Integer> totalGoles(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.totalGolesEquipo(id));
    }
}