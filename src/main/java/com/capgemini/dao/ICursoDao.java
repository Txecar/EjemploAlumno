package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.Curso;

@Repository
public interface ICursoDao extends JpaRepository<Curso, Long>{

}
