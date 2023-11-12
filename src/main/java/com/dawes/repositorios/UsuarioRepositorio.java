package com.dawes.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.dawes.modelo.UsuarioVO;

public interface UsuarioRepositorio extends CrudRepository<UsuarioVO, Integer> {
	
	
	Optional<UsuarioVO> findByDni(String dni);
	Optional<UsuarioVO> findByEmail(String email);
	Optional<UsuarioVO> findByUsername(String username);
	Optional<UsuarioVO> findByUsernameAndApellidos(String username, String apellidos);
	

}
