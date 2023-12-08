package com.dawes.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.VentaVO;

@Service
public interface ServicioVenta {

	Optional<List<VentaVO>> findByConciertoGrupoNombre(String nombre);

	Optional<List<VentaVO>> findByUsuarioDni(String dni);

	<S extends VentaVO> S save(S entity);

	<S extends VentaVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<VentaVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<VentaVO> findAll();

	Iterable<VentaVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(VentaVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends VentaVO> entities);

	void deleteAll();

}