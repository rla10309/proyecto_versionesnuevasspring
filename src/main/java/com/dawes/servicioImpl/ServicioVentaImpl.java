package com.dawes.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.repositorios.ConciertoRepositorio;
import com.dawes.repositorios.VentaRepositorio;
import com.dawes.servicio.ServicioVenta;

import jakarta.transaction.Transactional;

@Service
public class ServicioVentaImpl implements ServicioVenta {
	@Autowired
	VentaRepositorio vr;
	@Autowired
	ConciertoRepositorio cr;

	public Optional<List<VentaVO>> findByUsuarioDni(String dni) {
		return vr.findByUsuarioDni(dni);
	}

	public Optional<List<VentaVO>> findByConciertoGrupoNombre(String nombre) {
		return vr.findByConciertoGrupoNombre(nombre);
	}

	@Transactional
	@Override
	public <S extends VentaVO> S save(S entity) throws DataIntegrityViolationException {
		ConciertoVO c = cr.findById(entity.getConcierto().getIdconcierto()).get();
		try {
			if (entity.getIdventa() == 0) {
				c.setPlazas(c.getPlazas() - entity.getNumeroentradas());
				cr.save(c);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error en la venta");
		}

		return vr.save(entity);
	}

	@Override
	public <S extends VentaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return vr.saveAll(entities);
	}

	@Override
	public Optional<VentaVO> findById(Integer id) {
		return vr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return vr.existsById(id);
	}

	@Override
	public Iterable<VentaVO> findAll() {
		return vr.findAll();
	}

	@Override
	public Iterable<VentaVO> findAllById(Iterable<Integer> ids) {
		return vr.findAllById(ids);
	}

	@Override
	public long count() {
		return vr.count();
	}

	@Override
	public void deleteById(Integer id) {
		VentaVO venta = vr.findById(id).get();

		int plazasfinales = venta.getConcierto().getPlazas() + venta.getNumeroentradas();
		venta.getConcierto().setPlazas(plazasfinales);
		vr.deleteById(id);
	}

	@Override
	public void delete(VentaVO entity) {
		vr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		vr.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends VentaVO> entities) {
		vr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		vr.deleteAll();
	}

}
