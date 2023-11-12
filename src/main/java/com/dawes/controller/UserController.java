package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.modelo.GrupoVO;
import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioVenta;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioGrupo sg;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioUsuario su;
	
	@RequestMapping("/user")
	public String userByDni(@RequestParam String dni, Model modelo) {
		modelo.addAttribute("usuario", su.findByDni(dni).get() );
		modelo.addAttribute("ventas", sv.findByUsuarioDni(dni).get());
		return "user/user";
	}
	
	
	

	@RequestMapping("/compra")
	public String compra(@RequestParam int idconcierto, Model modelo) {
	    VentaVO venta = new VentaVO();
	    ConciertoVO concierto = sc.findById(idconcierto).get();
	    venta.setConcierto(concierto);
		modelo.addAttribute("venta", venta);
		modelo.addAttribute("usuarios", su.findAll());
		modelo.addAttribute("grupo", concierto.getGrupo());
		return "user/formcompra";
		
	}
	
	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute VentaVO venta, Model modelo)  {
		try {
			sv.save(venta);
			modelo.addAttribute("venta", venta);
			modelo.addAttribute("grupo", sg.findById(venta.getConcierto().getGrupo().getIdgrupo()).get());
		}
		catch(Exception e) {
			System.out.println("pasa por aquí");
			modelo.addAttribute("msgError", "No se ha podido realizar la venta " + e.getStackTrace());
			
		}

		return "user/ticket";
	}
}
