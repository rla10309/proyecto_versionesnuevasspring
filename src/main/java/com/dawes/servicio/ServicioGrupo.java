package com.dawes.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dawes.modelo.GrupoVO;

@Service
public interface ServicioGrupo {

	Optional<GrupoVO> findByNombreIgnoreCase(String nombre);

	<S extends GrupoVO> S save(S entity);

	<S extends GrupoVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<GrupoVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<GrupoVO> findAll();

	Iterable<GrupoVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(GrupoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends GrupoVO> entities);

	void deleteAll();

}