package com.example.AppSport.Service;

import com.example.AppSport.DTO.JugadorDTO;
import com.example.AppSport.Model.Equipo;
import com.example.AppSport.Model.Jugador;
import com.example.AppSport.Repository.EquipoRepository;
import com.example.AppSport.Repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;
    private final EquipoRepository equipoRepository;

    public JugadorServiceImpl(JugadorRepository jugadorRepository,
                              EquipoRepository equipoRepository) {
        this.jugadorRepository = jugadorRepository;
        this.equipoRepository = equipoRepository;
    }

    // Convierte DTO → Entidad
    private Jugador toEntity(JugadorDTO dto) {
        Jugador j = new Jugador();
        j.setNombre(dto.getNombre());
        j.setPosicion(dto.getPosicion());
        j.setDorsal(dto.getDorsal());
        j.setFechaNac(dto.getFechaNac());
        j.setNacionalidad(dto.getNacionalidad());

        if (dto.getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
            j.setEquipo(equipo);
        }
        return j;
    }

    @Override
    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }

    @Override
    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id: " + id));
    }

    @Override
    public Jugador guardar(JugadorDTO dto) {
        return jugadorRepository.save(toEntity(dto));
    }

    @Override
    public Jugador actualizar(Long id, JugadorDTO dto) {
        Jugador jugador = buscarPorId(id);
        jugador.setNombre(dto.getNombre());
        jugador.setPosicion(dto.getPosicion());
        jugador.setDorsal(dto.getDorsal());
        jugador.setFechaNac(dto.getFechaNac());
        jugador.setNacionalidad(dto.getNacionalidad());

        if (dto.getIdEquipo() != null) {
            Equipo equipo = equipoRepository.findById(dto.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id: " + dto.getIdEquipo()));
            jugador.setEquipo(equipo);
        }
        return jugadorRepository.save(jugador);
    }

    @Override
    public void eliminar(Long id) {
        if (!jugadorRepository.existsById(id)) {
            throw new RuntimeException("Jugador no encontrado con id: " + id);
        }
        jugadorRepository.deleteById(id);
    }

    @Override
    public List<Jugador> jugadoresPorEquipo(Long idEquipo) {
        return jugadorRepository.findJugadoresPorEquipo(idEquipo);
    }

    @Override
    public List<Jugador> jugadoresConMasDeXGoles(Integer minGoles) {
        return jugadorRepository.findJugadoresConMasDeXGoles(minGoles);
    }
}