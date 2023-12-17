package com.dawes.WS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.DTO.GrupoDTO;
import com.dawes.modelo.GrupoVO;
import com.dawes.servicio.ServicioGrupo;

@RestController
@RequestMapping("/api")
public class GrupoWS {

	@Autowired
	ServicioGrupo sg;

	// "https://app-tickets-c6dfd5dd1620.herokuapp.com/api/grupo/"

	@GetMapping("/grupo/{nombre}")
	public ResponseEntity<?> muestraDescripcion(@PathVariable String nombre) {
		try {
			GrupoVO grupoVO = sg.findByNombreIgnoreCase(nombre).get();
			GrupoDTO grupoDTO = new GrupoDTO(grupoVO.getNombre(), grupoVO.getDescripcion());
			return new ResponseEntity<GrupoDTO>(grupoDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error en la búsqueda", HttpStatus.NOT_FOUND);
		}

	}

}
