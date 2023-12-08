package com.dawes.servicio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;

@Service
public interface ServicioConcierto {

	Iterable<ConciertoVO> findAllByOrderByFechaAsc();

	Optional<List<ConciertoVO>> findByGrupoNombreIgnoreCase(String nombre);

	Optional<List<ConciertoVO>> findByFechaBetween(LocalDate inicio, LocalDate fin);

	Optional<ConciertoVO> findByFechaAndHora(LocalDate fecha, LocalTime hora);

	<S extends ConciertoVO> S save(S entity);

	<S extends ConciertoVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<ConciertoVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<ConciertoVO> findAll();

	Iterable<ConciertoVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(ConciertoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends ConciertoVO> entities);

	void deleteAll();

}