package com.dawes.servicioImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;
import com.dawes.repositorios.ConciertoRepositorio;
import com.dawes.repositorios.VentaRepositorio;
import com.dawes.servicio.ServicioConcierto;

import jakarta.transaction.Transactional;

@Service
public class ServicioConciertoImpl implements ServicioConcierto {

	public Optional<ConciertoVO> findByFechaAndHora(LocalDate fecha, LocalTime hora) {
		return cr.findByFechaAndHora(fecha, hora);
	}

	@Autowired
	private ConciertoRepositorio cr;

	public Optional<List<ConciertoVO>> findByGrupoNombreIgnoreCase(String nombre) {
		return cr.findByGrupoNombreIgnoreCase(nombre);
	}

	public Optional<List<ConciertoVO>> findByFechaBetween(LocalDate inicio, LocalDate fin) {
		return cr.findByFechaBetween(inicio, fin);
	}

	@Override
	public <S extends ConciertoVO> S save(S entity) throws DataIntegrityViolationException {
		try {
			cr.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("La fecha y la hora seleccionadas están ocupadas");
		}
		return entity;
	}

	@Override
	public <S extends ConciertoVO> Iterable<S> saveAll(Iterable<S> entities) {
		return cr.saveAll(entities);
	}

	@Override
	public Optional<ConciertoVO> findById(Integer id) {
		return cr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return cr.existsById(id);
	}

	@Override
	public Iterable<ConciertoVO> findAll() {
		return cr.findAll();
	}

	@Override
	public Iterable<ConciertoVO> findAllById(Iterable<Integer> ids) {
		return cr.findAllById(ids);
	}

	@Override
	public long count() {
		return cr.count();
	}

	public Iterable<ConciertoVO> findAllByOrderByFechaAsc() {
		return cr.findAllByOrderByFechaAsc();
	}

	@Override
	public void deleteById(Integer id) {
		cr.deleteById(id);
	}

	@Override
	public void delete(ConciertoVO entity) {
		cr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		cr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ConciertoVO> entities) {
		cr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		cr.deleteAll();
	}

}
