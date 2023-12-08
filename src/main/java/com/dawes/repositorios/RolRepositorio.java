package com.dawes.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;

public interface RolRepositorio extends CrudRepository<RolVO, Integer> {
	Optional<RolVO> findByNombre(String nombre);

	Optional<List<UsuarioVO>> findUsuarioByNombre(String nombre);

}
