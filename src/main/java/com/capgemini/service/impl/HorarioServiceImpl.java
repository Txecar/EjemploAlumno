package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.IHorarioDao;
import com.capgemini.entities.Horario;
import com.capgemini.service.IHorarioService;

@Service
public class HorarioServiceImpl implements IHorarioService{

	@Autowired
	private IHorarioDao daoHorario;
	
	@Override
	public List<Horario> getHorarios() {
		return daoHorario.findAll();
	}

}
