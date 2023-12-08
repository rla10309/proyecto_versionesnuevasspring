package com.dawes.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.UsuarioVO;
import com.dawes.repositorios.UsuarioRepositorio;
import com.dawes.servicio.ServicioUsuario;

import jakarta.transaction.Transactional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario, UserDetailsService {
	@Autowired
	UsuarioRepositorio ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = ur.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("No se encuentra al usuario " + username);
		}

		return user;
	}

	public UserDetails findByEmail(String email) {
		return ur.findByEmail(email);
	}

	public UserDetails findByUsername(String username) {
		return ur.findByUsername(username);
	}

	@Override
	public Optional<UsuarioVO> findByDni(String dni) {
		return ur.findByDni(dni);
	}

	@Override
	public Optional<UsuarioVO> findByUsernameAndApellidos(String username, String apellidos) {
		return ur.findByUsernameAndApellidos(username, apellidos);
	}

	@Transactional
	@Override
	public <S extends UsuarioVO> S save(S entity) throws DataIntegrityViolationException {
		try {
			ur.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error en la inserción de usuario");

		}
		return entity;
	}

	@Override
	public <S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities) {
		return ur.saveAll(entities);
	}

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return ur.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return ur.existsById(id);
	}

	@Override
	public Iterable<UsuarioVO> findAll() {
		return ur.findAll();
	}

	@Override
	public Iterable<UsuarioVO> findAllById(Iterable<Integer> ids) {
		return ur.findAllById(ids);
	}

	@Override
	public long count() {
		return ur.count();
	}

	@Override
	public void deleteById(Integer id) {
		ur.deleteById(id);
	}

	@Override
	public void delete(UsuarioVO entity) {
		ur.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ur.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioVO> entities) {
		ur.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ur.deleteAll();
	}

}
