package com.dawes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dawes.modelo.GrupoVO;
import com.dawes.servicio.ServicioConcierto;
import com.dawes.servicio.ServicioGrupo;
import com.dawes.upload.storage.StorageService;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	ServicioGrupo sg;

	@Autowired
	private StorageService storageService;

	@Autowired
	ServicioConcierto sc;

	@RequestMapping("/listadogrupos")
	public String listadogrupos(Model modelo) {
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/grupo/listadogrupos";

	}

	@RequestMapping("/creagrupo")
	public String creagrupo(Model modelo) {
		modelo.addAttribute("grupo", new GrupoVO());
		return "admin/grupo/insertagrupo";
	}

	@RequestMapping("/insertagrupo")
	public String insertagrupo(@ModelAttribute GrupoVO grupo, @RequestParam("file") MultipartFile file, Model modelo) {

		if (!file.isEmpty()) {
			String imagen = storageService.store(file, grupo.getIdgrupo());
			grupo.setImagen(MvcUriComponentsBuilder.fromMethodName(GrupoController.class, "serveFile", imagen).build()
					.toUriString());
		}
		try {
			sg.save(grupo);
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("msgError", "Ya existe un grupo con ese nombre");
		}
		modelo.addAttribute("grupos", sg.findAll());
		return "admin/grupo/listadogrupos";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		if (file == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(file);
	}

	@RequestMapping("/edit")
	public String edit(@RequestParam int idgrupo, Model modelo) {
		modelo.addAttribute("grupo", sg.findById(idgrupo).get());
		return "admin/grupo/editagrupo";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int idgrupo, Model modelo) {
		try {
			sg.deleteById(idgrupo);
			modelo.addAttribute("msgSuccess", "Grupo eliminado con éxito");
		} catch (DataIntegrityViolationException e) {
			modelo.addAttribute("msgError", "Este grupo tiene conciertos pendientes");
		}

		modelo.addAttribute("grupos", sg.findAll());
		return "admin/grupo/listadogrupos";
	}

	@RequestMapping("/buscarporgrupo")
	public String findByGrupo(@RequestParam String nombre, Model modelo) {
		modelo.addAttribute("conciertos", sc.findByGrupoNombreIgnoreCase(nombre).get());
		return "admin/concierto/listadoconciertos";
	}

}
