package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.ConciertoVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;

@Controller
@RequestMapping("/concierto")
public class ConciertoController {
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioGrupo sg;
	
	@RequestMapping("/listadoconciertos")
	public String listadoconciertos(Model modelo) {
		modelo.addAttribute("conciertos", sc.findAll());
		return "admin/concierto/listadoconciertos";
	}
	
	@RequestMapping("/creaconcierto")
	public String creaconcierto(Model modelo) {
		modelo.addAttribute("concierto", new ConciertoVO());
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/concierto/nuevoconcierto-form";
	}
	
	@RequestMapping("/insertaconcierto")
		public String insertaConcierto(@ModelAttribute ConciertoVO concierto) {
			sc.save(concierto);
			return "redirect:/concierto/listadoconciertos";
	}
	@RequestMapping("/edit")
	public String edit(@RequestParam int idconcierto, Model modelo) {
		modelo.addAttribute("concierto", sc.findById(idconcierto).get());
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/concierto/nuevoconcierto-form";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int idconcierto) {
		try {
		sc.deleteById(idconcierto);
		}catch (DataIntegrityViolationException e) {
			return "redirect:/concierto/listadoconciertos?error=true";
		}
		return "redirect:/concierto/listadoconciertos?success=true";
	}
	

	

}
