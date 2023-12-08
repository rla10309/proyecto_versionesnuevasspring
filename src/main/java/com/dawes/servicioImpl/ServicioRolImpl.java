package com.dawes.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.repositorios.RolRepositorio;
import com.dawes.servicio.ServicioRol;

@Service
public class ServicioRolImpl implements ServicioRol {
	@Autowired
	RolRepositorio rr;

	public Optional<List<UsuarioVO>> findUsuarioByNombre(String nombre) {
		return rr.findUsuarioByNombre(nombre);
	}

	@Override
	public Optional<RolVO> findByNombre(String nombre) {
		return rr.findByNombre(nombre);
	}

	@Override
	public <S extends RolVO> S save(S entity) {
		return rr.save(entity);
	}

	@Override
	public <S extends RolVO> Iterable<S> saveAll(Iterable<S> entities) {
		return rr.saveAll(entities);
	}

	@Override
	public Optional<RolVO> findById(Integer id) {
		return rr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return rr.existsById(id);
	}

	@Override
	public Iterable<RolVO> findAll() {
		return rr.findAll();
	}

	@Override
	public Iterable<RolVO> findAllById(Iterable<Integer> ids) {
		return rr.findAllById(ids);
	}

	@Override
	public long count() {
		return rr.count();
	}

	@Override
	public void deleteById(Integer id) {
		rr.deleteById(id);
	}

	@Override
	public void delete(RolVO entity) {
		rr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		rr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends RolVO> entities) {
		rr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		rr.deleteAll();
	}

}
