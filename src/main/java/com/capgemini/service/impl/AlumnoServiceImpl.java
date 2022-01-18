package com.capgemini.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.IAlumnoDao;
import com.capgemini.entities.Alumno;
import com.capgemini.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDao daoAlumno;
	
	@Override
	public List<Alumno> getAlumnos() {
		return daoAlumno.findAll();
	}

	@Override
	public void guardarAlumno(Alumno alumno) {
		daoAlumno.save(alumno);
	}

	@Override
	public Alumno getAlumno(String id) {
		return daoAlumno.getById(Long.parseLong(id));
	}

	@Override
	public void eliminar(Alumno alumno) {
		daoAlumno.delete(alumno);
		
	}
	
	@Override
	public List<Alumno> getAlumnosManana() {

		return daoAlumno.findAll().stream().filter(p -> p.getCurso().getHorario().getNombre().equals("DIURNO")).collect(Collectors.toList());
	}
}
