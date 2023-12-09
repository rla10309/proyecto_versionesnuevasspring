package com.dawes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.VentaVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.servicio.ServicioUsuario;
import com.dawes.servicio.ServicioVenta;

@Controller
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	ServicioVenta sv;
	@Autowired
	ServicioConcierto sc;
	@Autowired
	ServicioGrupo sg;
	@Autowired
	ServicioUsuario su;

	@RequestMapping("/prueba")
	public String prueba(Model modelo) {
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/venta/prueba";

	}

	@RequestMapping("/listadoventas")
	public String listadoventas(Model modelo) {
		modelo.addAttribute("ventas", sv.findAll());
		return "admin/venta/listadoventas";
	}

	@RequestMapping("/nuevaventa")
	public String nuevaventa(Model modelo) {
		modelo.addAttribute("venta", new VentaVO());
		modelo.addAttribute("usuarios", su.findAll());
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/venta/forminsertaventa";
	}

	@RequestMapping("/insertar")
	public String insertar(@ModelAttribute VentaVO venta) {
		sv.save(venta);
		return "redirect:/venta/listadoventas";
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int idventa, @RequestParam String nombre, Model modelo) {
		try {
			modelo.addAttribute("venta", sv.findById(idventa).get());
			modelo.addAttribute("usuarios", su.findAll());
			modelo.addAttribute("grupos", sg.findAll());
			modelo.addAttribute("conciertos", sc.findByGrupoNombreIgnoreCase(nombre).get());
		} catch (Exception e) {
			System.out.println("Error ventas: " + e.getStackTrace());
		}
		return "admin/venta/forminsertaventa";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int idventa) {
		sv.deleteById(idventa);
		return "redirect:/venta/listadoventas";
	}

	@RequestMapping("/informe")
	public String findByGrupoNombre(@RequestParam String nombre, Model modelo) {
		List<VentaVO> ventas = sv.findByConciertoGrupoNombre(nombre).get();
		if (!ventas.isEmpty()) {
			modelo.addAttribute("nombregrupo", nombre);
			modelo.addAttribute("ventas", ventas);
		} else {
			modelo.addAttribute("msgError", "No hay datos que mostrar");
		}

		return "admin/venta/informeventas";
	}
}
