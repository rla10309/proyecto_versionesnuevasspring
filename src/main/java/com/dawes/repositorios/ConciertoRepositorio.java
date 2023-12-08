package com.dawes.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.ConciertoVO;

public interface ConciertoRepositorio extends CrudRepository<ConciertoVO, Integer> {

	Optional<ConciertoVO> findByFechaAndHora(LocalDate fecha, LocalTime hora);

	Optional<List<ConciertoVO>> findByFechaBetween(LocalDate inicio, LocalDate fin);

	Optional<List<ConciertoVO>> findByGrupoNombreIgnoreCase(String nombre);

	Iterable<ConciertoVO> findAllByOrderByFechaAsc();

}
