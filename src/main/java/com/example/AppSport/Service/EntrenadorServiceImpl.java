package com.example.AppSport.Service;

import com.example.AppSport.DTO.EntrenadorDTO;
import com.example.AppSport.Model.Entrenador;
import com.example.AppSport.Model.Equipo;
import com.example.AppSport.Repository.EntrenadorRepository;
import com.example.AppSport.Repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final EquipoRepository equipoRepository;

    public EntrenadorServiceImpl(EntrenadorRepository entrenadorRepository,
                                 EquipoRepository equipoRepository) {
        this.entrenadorRepository = entrenadorRepository;
        this.equipoRepository = equipoRepository;
    }

    // Convierte DTO → Entidad
    private Entrenador toEntity(EntrenadorDTO dto) {
        Entrenador e = new Entrenador();
        e.setNombre(dto.getNombre());
        e.setEspecialidad(dto.getEspecialidad());

        if (dto.getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
            e.setEquipo(equipo);
        }
        return e;
    }

    @Override
    public List<Entrenador> listarTodos() {
        return entrenadorRepository.findAll();
    }

    @Override
    public Entrenador buscarPorId(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
    }

    @Override
    public Entrenador guardar(EntrenadorDTO dto) {
        return entrenadorRepository.save(toEntity(dto));
    }

    @Override
    public Entrenador actualizar(Long id, EntrenadorDTO dto) {
        Entrenador entrenador = buscarPorId(id);
        entrenador.setNombre(dto.getNombre());
        entrenador.setEspecialidad(dto.getEspecialidad());

        if (dto.getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
            entrenador.setEquipo(equipo);
        }
        return entrenadorRepository.save(entrenador);
    }

    @Override
    public void eliminar(Long id) {
        if (!entrenadorRepository.existsById(id)) {
            throw new RuntimeException("Entrenador no encontrado con id: " + id);
        }
        entrenadorRepository.deleteById(id);
    }
}