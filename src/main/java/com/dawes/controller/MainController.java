package com.dawes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioVenta;

@Controller

public class MainController {
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioGrupo sg;

	@Autowired
	ServicioVenta sv;
	
	
	@RequestMapping({"/", "/index"})
	public String index(Model modelo) {
		modelo.addAttribute("conciertos",sc.findAll()); 
		return "index";
	}
	
	@RequestMapping("/public/findgrupobyid")
	public String findGrupoById(@RequestParam int idgrupo, Model modelo) {
		modelo.addAttribute("grupo", sg.findById(idgrupo).get());
		return "public/vistaconcierto";
	}
	
	@RequestMapping("/login")
		public String login() {
			return "public/login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
	@RequestMapping("/admin")
	public String admin() {
		return "admin/admin";
	}
	@RequestMapping("/user")
	public String user() {
		return "user/user";
	}

	@RequestMapping("public/buscarporgrupo")
	public String findByGrupo(@RequestParam String nombre, Model modelo) {
	    Optional<List<ConciertoVO>> conciertos = sc.findByGrupoNombre(nombre);

	    if (conciertos.isPresent()) {
	        modelo.addAttribute("conciertos", conciertos.get());
	    } else {
	        // Handle the case when no concert is found, for example, redirect to an error page
	        modelo.addAttribute("error", "Concert not found");
	        return "error/errorPage"; // Adjust the view name accordingly
	    }

	    return "index";
	}

		 


}
