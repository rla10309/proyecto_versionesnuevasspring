package com.dawes.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.GrupoVO;

public interface GrupoRepositorio extends CrudRepository<GrupoVO, Integer> {
	Optional<GrupoVO> findByNombreIgnoreCase(String nombre);

}
