package com.dawes.servicio;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;

@Service
public interface ServicioUsuario {

	UserDetails findByUsername(String username);

	UserDetails findByEmail(String email);

	Optional<UsuarioVO> findByDni(String dni);

	Optional<UsuarioVO> findByUsernameAndApellidos(String username, String apellidos);

	<S extends UsuarioVO> S save(S entity);

	<S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<UsuarioVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<UsuarioVO> findAll();

	Iterable<UsuarioVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(UsuarioVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends UsuarioVO> entities);

	void deleteAll();

}