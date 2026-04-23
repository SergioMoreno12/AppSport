package com.example.AppSport.Service;

import com.example.AppSport.DTO.EquipoDTO;
import com.example.AppSport.Model.Equipo;
import com.example.AppSport.Repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // Convierte DTO → Entidad
    private Equipo toEntity(EquipoDTO dto) {
        Equipo e = new Equipo();
        e.setNombre(dto.getNombre());
        e.setCiudad(dto.getCiudad());
        e.setFundacion(dto.getFundacion());
        return e;
    }

    @Override
    public List<Equipo> listarTodos() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo buscarPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + id));
    }

    @Override
    public Equipo guardar(EquipoDTO dto) {
        return equipoRepository.save(toEntity(dto));
    }

    @Override
    public Equipo actualizar(Long id, EquipoDTO dto) {
        Equipo equipo = buscarPorId(id);
        equipo.setNombre(dto.getNombre());
        equipo.setCiudad(dto.getCiudad());
        equipo.setFundacion(dto.getFundacion());
        return equipoRepository.save(equipo);
    }

    @Override
    public void eliminar(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Equipo no encontrado con id: " + id);
        }
        equipoRepository.deleteById(id);
    }

    @Override
    public Integer totalGolesEquipo(Long idEquipo) {
        return equipoRepository.totalGolesPorEquipo(idEquipo);
    }
}