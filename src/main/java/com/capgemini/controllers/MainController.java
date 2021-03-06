package com.capgemini.controllers;

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
	
	@PostMapping("/postrequest")
	public String getFormulario(@ModelAttribute (name = "alumno") Alumno alumno, @RequestParam (name = "horario") String horario) {
		//System.out.println("horario" + horario);
		//alumno.getCurso().getHorario().setId(Long.parseLong(horario));
		//System.out.println(alumno);
		serviceAlumno.guardarAlumno(alumno);
		return "redirect:/examen";
	}
	
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
	public String getFormulario(@ModelAttribute (name = "alumno") Alumno alumno) {
		serviceAlumno.guardarAlumno(alumno);
		
		return "redirect:/examen";
	}

}
