package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Horario;

@Repository
public interface IHorarioDao extends JpaRepository<Horario, Long>{

}
