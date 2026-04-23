package com.example.AppSport.Service;

import com.example.AppSport.DTO.PartidoDTO;
import com.example.AppSport.DTO.ResultadoPartidoDTO;
import com.example.AppSport.Model.Equipo;
import com.example.AppSport.Model.Partido;
import com.example.AppSport.Repository.EquipoRepository;
import com.example.AppSport.Repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;

    public PartidoServiceImpl(PartidoRepository partidoRepository,
                              EquipoRepository equipoRepository) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
    }

    // Convierte DTO → Entidad
    private Partido toEntity(PartidoDTO dto) {
        Partido p = new Partido();
        p.setFecha(dto.getFecha());
        p.setEstadio(dto.getEstadio());
        p.setGolesLocal(dto.getGolesLocal());
        p.setGolesVisita(dto.getGolesVisita());

        if (dto.getIdEquipoLocal() != null) {
            Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                    .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + dto.getIdEquipoLocal()));
            p.setEquipoLocal(local);
        }
        if (dto.getIdEquipoVisita() != null) {
            Equipo visita = equipoRepository.findById(dto.getIdEquipoVisita())
                    .orElseThrow(() -> new RuntimeException("Equipo visita no encontrado con id: " + dto.getIdEquipoVisita()));
            p.setEquipoVisita(visita);
        }
        return p;
    }

    @Override
    public List<Partido> listarTodos() {
        return partidoRepository.findAll();
    }

    @Override
    public Partido buscarPorId(Long id) {
        return partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado con id: " + id));
    }

    @Override
    public Partido guardar(PartidoDTO dto) {
        return partidoRepository.save(toEntity(dto));
    }

    @Override
    public Partido actualizar(Long id, PartidoDTO dto) {
        Partido partido = buscarPorId(id);
        partido.setFecha(dto.getFecha());
        partido.setEstadio(dto.getEstadio());
        partido.setGolesLocal(dto.getGolesLocal());
        partido.setGolesVisita(dto.getGolesVisita());

        if (dto.getIdEquipoLocal() != null) {
            Equipo local = equipoRepository.findById(dto.getIdEquipoLocal())
                    .orElseThrow(() -> new RuntimeException("Equipo local no encontrado con id: " + dto.getIdEquipoLocal()));
            partido.setEquipoLocal(local);
        }
        if (dto.getIdEquipoVisita() != null) {
            Equipo visita = equipoRepository.findById(dto.getIdEquipoVisita())
                    .orElseThrow(() -> new RuntimeException("Equipo visita no encontrado con id: " + dto.getIdEquipoVisita()));
            partido.setEquipoVisita(visita);
        }
        return partidoRepository.save(partido);
    }

    @Override
    public void eliminar(Long id) {
        if (!partidoRepository.existsById(id)) {
            throw new RuntimeException("Partido no encontrado con id: " + id);
        }
        partidoRepository.deleteById(id);
    }

    @Override
    public List<ResultadoPartidoDTO> resultadosConNombres() {
        return partidoRepository.findResultadosPartidos();
    }
}