package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Sexo;

@Repository
public interface ISexoDao extends JpaRepository<Sexo, Long>{

}
