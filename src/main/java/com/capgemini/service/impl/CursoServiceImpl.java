package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ICursoDao;
import com.capgemini.entities.Curso;
import com.capgemini.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	private ICursoDao daoCurso;
	
	@Override
	public List<Curso> getCursos() {
		return daoCurso.findAll();
	}

}
