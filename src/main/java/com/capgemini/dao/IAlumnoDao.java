package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Alumno;

@Repository
public interface IAlumnoDao extends JpaRepository<Alumno, Long>{

}
