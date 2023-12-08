package com.dawes.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.VentaVO;

public interface VentaRepositorio extends CrudRepository<VentaVO, Integer> {

	Optional<List<VentaVO>> findByUsuarioDni(String dni);

	Optional<List<VentaVO>> findByConciertoGrupoNombre(String nombre);

}
