package com.example.AppSport.Service;

import com.example.AppSport.DTO.EstadisticaJugadorDTO;
import com.example.AppSport.Model.EstadisticaJugador;
import com.example.AppSport.Model.Jugador;
import com.example.AppSport.Model.Partido;
import com.example.AppSport.Repository.EstadisticaJugadorRepository;
import com.example.AppSport.Repository.JugadorRepository;
import com.example.AppSport.Repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadisticaJugadorServiceImpl implements EstadisticaJugadorService {

    private final EstadisticaJugadorRepository estadisticaRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidoRepository partidoRepository;

    public EstadisticaJugadorServiceImpl(EstadisticaJugadorRepository estadisticaRepository,
                                         JugadorRepository jugadorRepository,
                                         PartidoRepository partidoRepository) {
        this.estadisticaRepository = estadisticaRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidoRepository = partidoRepository;
    }

    // Convierte DTO → Entidad
    private EstadisticaJugador toEntity(EstadisticaJugadorDTO dto) {
        EstadisticaJugador e = new EstadisticaJugador();
        e.setMinutosJugados(dto.getMinutosJugados());
        e.setGoles(dto.getGoles());
        e.setAsistencias(dto.getAsistencias());
        e.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        e.setTarjetasRojas(dto.getTarjetasRojas());

        if (dto.getIdJugador() != null) {
            Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                    .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + dto.getIdJugador()));
            e.setJugador(jugador);
        }
        if (dto.getIdPartido() != null) {
            Partido partido = partidoRepository.findById(dto.getIdPartido())
                    .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + dto.getIdPartido()));
            e.setPartido(partido);
        }
        return e;
    }

    @Override
    public List<EstadisticaJugador> listarTodas() {
        return estadisticaRepository.findAll();
    }

    @Override
    public EstadisticaJugador buscarPorId(Long id) {
        return estadisticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estadística no encontrada con id: " + id));
    }

    @Override
    public EstadisticaJugador guardar(EstadisticaJugadorDTO dto) {
        return estadisticaRepository.save(toEntity(dto));
    }

    @Override
    public EstadisticaJugador actualizar(Long id, EstadisticaJugadorDTO dto) {
        EstadisticaJugador estadistica = buscarPorId(id);
        estadistica.setMinutosJugados(dto.getMinutosJugados());
        estadistica.setGoles(dto.getGoles());
        estadistica.setAsistencias(dto.getAsistencias());
        estadistica.setTarjetasAmarillas(dto.getTarjetasAmarillas());
        estadistica.setTarjetasRojas(dto.getTarjetasRojas());

        if (dto.getIdJugador() != null) {
            Jugador jugador = jugadorRepository.findById(dto.getIdJugador())
                    .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + dto.getIdJugador()));
            estadistica.setJugador(jugador);
        }
        if (dto.getIdPartido() != null) {
            Partido partido = partidoRepository.findById(dto.getIdPartido())
                    .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + dto.getIdPartido()));
            estadistica.setPartido(partido);
        }
        return estadisticaRepository.save(estadistica);
    }

    @Override
    public void eliminar(Long id) {
        if (!estadisticaRepository.existsById(id)) {
            throw new RuntimeException("Estadística no encontrada con id: " + id);
        }
        estadisticaRepository.deleteById(id);
    }
}