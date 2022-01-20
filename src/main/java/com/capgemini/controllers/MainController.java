package com.capgemini.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.entities.Alumno;
import com.capgemini.entities.Curso;
import com.capgemini.entities.Horario;
import com.capgemini.service.IAlumnoService;
import com.capgemini.service.ICursoService;
import com.capgemini.service.IHorarioService;
import com.capgemini.service.ISexoService;

@Controller
@RequestMapping("/")
public class MainController {
	
private static final Log LOG = LogFactory.getLog(MainController.class);
	
	@Autowired
	private IAlumnoService serviceAlumno;
	@Autowired
	private ICursoService serviceCurso;
	@Autowired
	private IHorarioService serviceHorario;
	@Autowired
	private ISexoService serviceSexo;
	
	
//	@GetMapping("/examen")
//	public ModelAndView metodo() {
//		
//		ModelAndView mav = new ModelAndView("examen");
//		mav.addObject("alumnos", serviceAlumno.getAlumnos());
//		
//		LOG.info("Estoy entrando en el ej1");
//		
//		return mav;
//	}

	@GetMapping()
	public String home() {
		return "index";
	}
	
	@GetMapping("/examen")
	public String listar (Model model) {
		
		model.addAttribute("alumnos", serviceAlumno.getAlumnos());	
		model.addAttribute("alumno", new Alumno());
		model.addAttribute("cursos", serviceCurso.getCursos());
		model.addAttribute("horarios", serviceHorario.getHorarios());
		model.addAttribute("sexos", serviceSexo.getSexos());
		model.addAttribute("alumnosManana", serviceAlumno.getAlumnosManana());
		
		var listadoPasajeros = serviceAlumno.getAlumnos().stream()
				.collect(Collectors.groupingBy(Alumno::getCurso));
		System.out.println(listadoPasajeros);
		model.addAttribute("alumnosCurso", listadoPasajeros);
		
		
		return "examen";
	}
	
//	@PostMapping("/postrequest")
//	public String getFormulario(@ModelAttribute (name = "alumno") Alumno alumno, @RequestParam (name = "horario") String horario) {
//		//System.out.println("horario" + horario);
//		//alumno.getCurso().getHorario().setId(Long.parseLong(horario));
//		//System.out.println(alumno);
//		serviceAlumno.guardarAlumno(alumno);
//		return "redirect:/examen";
//	}
	
	@GetMapping("/borrar/{id}")
	public String borrar (@PathVariable (name = "id") String id) {
		
		serviceAlumno.eliminar(serviceAlumno.getAlumno(id));
		
		return "redirect:/examen";
	}
	
	@GetMapping("/botonactualiza/{id}")
	public String botonactualiza (@PathVariable (name = "id") String id, Model model) {
		
		model.addAttribute("alumnos", serviceAlumno.getAlumnos());	
		model.addAttribute("alumno", serviceAlumno.getAlumno(id));
		model.addAttribute("cursos", serviceCurso.getCursos());
		model.addAttribute("horarios", serviceHorario.getHorarios());
		model.addAttribute("sexos", serviceSexo.getSexos());
		
		return "formularioactualiza";

	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute (name = "alumno") Alumno alumno, @RequestParam(name = "file") MultipartFile imagen) {
		
		
		if (!imagen.isEmpty()) {

			// Obtenemos la ruta relativa
			// Path imagesFolder = Paths.get("src//main//resources//static/images");

			// Ruta absoluta
			// String rutaAbsoluta = imagesFolder.toFile().getAbsolutePath();

			String rutaAbsoluta = "//home//curso//Persona//recursos";

			try {
				byte[] bytesImages = imagen.getBytes();

				// Ruta completa, que incluye el nombre original de la imagen
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());

				LOG.info("ruta completa a la imagen: " + rutaCompleta);

				Files.write(rutaCompleta, bytesImages);

				// persona.setFoto(imagen.getOriginalFilename());

				alumno.setFoto(imagen.getOriginalFilename());

				serviceAlumno.guardarAlumno(alumno);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:/examen";
	}
	
	
	@PostMapping("/postrequest")
	public String getFormulario(@ModelAttribute(name = "alumno") Alumno alumno, @RequestParam(name = "file") MultipartFile imagen) {
		
		if(! imagen.isEmpty()) {
			
			// Obtenemos la ruta relativa
			// Path imagesFolder = Paths.get("src//main//resources//static/images");
			
			// Ruta absoluta
			// String rutaAbsoluta = imagesFolder.toFile().getAbsolutePath();	
			
			String rutaAbsoluta = "//home//curso//Persona//recursos";
			
			try {
				byte[] bytesImages = imagen.getBytes();
				
				// Ruta completa, que incluye el nombre original de la imagen
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				
				LOG.info("ruta completa a la imagen: " + rutaCompleta);
				
				Files.write(rutaCompleta, bytesImages);
				
				//persona.setFoto(imagen.getOriginalFilename());
				
				alumno.setFoto(imagen.getOriginalFilename());
				
				serviceAlumno.guardarAlumno(alumno);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return "redirect:/examen";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalles(@PathVariable(name ="id") String id, Model model ) {
		
		Alumno alumno = null;
		
		alumno = serviceAlumno.getAlumno(id);
		
		model.addAttribute("alumno", alumno);
		
		return "detalles";
	}

}
