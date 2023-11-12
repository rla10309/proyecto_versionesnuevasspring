package com.dawes.servicioImpl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.GrupoVO;
import com.dawes.repositorios.GrupoRepositorio;
import com.dawes.servicio.ServicioGrupo;

import jakarta.transaction.Transactional;

@Service
public class ServicioGrupoImpl implements ServicioGrupo {
	@Autowired
	GrupoRepositorio gr;

	@Override
	public Optional<GrupoVO> findByNombre(String nombre) {
		return gr.findByNombre(nombre);
	}

	@Transactional
	@Override
	public <S extends GrupoVO> S save(S entity)throws DataIntegrityViolationException {
		try {
			gr.save(entity);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
		return entity;
		
	}

	@Override
	public <S extends GrupoVO> Iterable<S> saveAll(Iterable<S> entities) {
		return gr.saveAll(entities);
	}

	@Override
	public Optional<GrupoVO> findById(Integer id) {
		return gr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return gr.existsById(id);
	}

	@Override
	public Iterable<GrupoVO> findAll() {
		return gr.findAll();
	}

	@Override
	public Iterable<GrupoVO> findAllById(Iterable<Integer> ids) {
		return gr.findAllById(ids);
	}

	@Override
	public long count() {
		return gr.count();
	}

	@Transactional
	@Override
	public void deleteById(Integer id){
	
			gr.deleteById(id);
	}

	@Override
	public void delete(GrupoVO entity) {
		gr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		gr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends GrupoVO> entities) {
		gr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		gr.deleteAll();
	}
	
	

}
