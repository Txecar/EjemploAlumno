package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ISexoDao;
import com.capgemini.entities.Sexo;
import com.capgemini.service.ISexoService;

@Service
public class SexoServiceImpl implements ISexoService{

	@Autowired
	private ISexoDao daoSexo;
	
	@Override
	public List<Sexo> getSexos() {
		// TODO Auto-generated method stub
		return daoSexo.findAll();
	}
	

}
