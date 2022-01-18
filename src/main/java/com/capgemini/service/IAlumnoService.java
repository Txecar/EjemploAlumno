package com.capgemini.service;

import java.util.List;

import com.capgemini.entities.Alumno;

public interface IAlumnoService {

	public List<Alumno> getAlumnos();
	public void guardarAlumno(Alumno alumno);
	public Alumno getAlumno (String id);
	public void eliminar(Alumno alumno);
	public List<Alumno> getAlumnosManana();
}
