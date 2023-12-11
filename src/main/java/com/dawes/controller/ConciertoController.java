package com.dawes.controller;

import java.time.LocalDate;

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
		//LocalDate now = LocalDate.now();
		modelo.addAttribute("concierto", new ConciertoVO());
		modelo.addAttribute("grupos", sg.findAll());
		modelo.addAttribute("now", LocalDate.now());
		
		return "admin/concierto/nuevoconcierto-form";
	}

	@RequestMapping("/insertaconcierto")
	public String insertaConcierto(@ModelAttribute ConciertoVO concierto, Model modelo) {
		boolean existe = false;
		if(concierto.getIdconcierto() !=0) {
			existe = true;
		}
		try {
			sc.save(concierto);
		} catch (DataIntegrityViolationException e) {

			modelo.addAttribute("concierto", new ConciertoVO());
			modelo.addAttribute("grupos", sg.findAll());
			modelo.addAttribute("msgError", "La fecha y la hora están ocupadas");
			return "admin/concierto/nuevoconcierto-form";
		}
		modelo.addAttribute("conciertos", sc.findAll());
		if(!existe) {
			modelo.addAttribute("msgSuccess", "Se ha creado un nuevo concierto");
		} else {
			modelo.addAttribute("msgSuccess", "Se ha modificado el concierto");
		}
		return "admin/concierto/listadoconciertos";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int idconcierto, Model modelo) {
		modelo.addAttribute("concierto", sc.findById(idconcierto).get());
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/concierto/nuevoconcierto-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int idconcierto, Model modelo) {
		try {
			sc.deleteById(idconcierto);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("conciertos", sc.findAll());
			modelo.addAttribute("msgError", "El concierto no se puede eliminar porque tiene ventas asociadas");
			return "admin/concierto/listadoconciertos";
		}

		modelo.addAttribute("conciertos", sc.findAll());
		modelo.addAttribute("msgSuccess", "El concierto se ha eliminado");

		return "admin/concierto/listadoconciertos";
	}

}
