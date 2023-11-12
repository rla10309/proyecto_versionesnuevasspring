package com.dawes.WS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.DTO.ConciertoDTO;
import com.dawes.modelo.ConciertoVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;

@RestController
@RequestMapping("/api")
public class ConciertoWS {

	@Autowired
	ServicioConcierto sc;
	
	@Autowired
	ServicioGrupo sg;
	
	/* 
	 * Devuelve todos los conciertos de un grupo
	 * desde http://localhost:808/api/conciertos/ABBA
	 */
	@GetMapping("/conciertos/{nombre}")
	public ResponseEntity<?> findByGrupoNombre(@PathVariable String nombre){
		List<ConciertoDTO> conciertosDTO = new ArrayList<ConciertoDTO>();
		List<ConciertoVO> conciertosVO = sc.findByGrupoNombre(nombre).get();
		conciertosVO.forEach(c->conciertosDTO.add(new ConciertoDTO(c.getIdconcierto(), c.getFecha(), c.getHora(), c.getPrecioanticipado(), c.getPreciotaquilla(), c.getPlazas(),
				c.getGrupo().getIdgrupo())));
		return new ResponseEntity<List<ConciertoDTO>>(conciertosDTO, HttpStatus.OK);
		
	}
	
}