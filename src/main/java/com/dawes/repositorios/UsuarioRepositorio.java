package com.dawes.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.dawes.modelo.UsuarioVO;

public interface UsuarioRepositorio extends CrudRepository<UsuarioVO, Integer> {

	Optional<UsuarioVO> findByDni(String dni);

	UserDetails findByEmail(String email);

	UserDetails findByUsername(String username);

	Optional<UsuarioVO> findByUsernameAndApellidos(String username, String apellidos);

}
