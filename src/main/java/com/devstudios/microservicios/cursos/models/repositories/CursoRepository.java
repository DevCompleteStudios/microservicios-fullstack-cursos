package com.devstudios.microservicios.cursos.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devstudios.microservicios.cursos.models.entities.Curso;



public interface CursoRepository extends CrudRepository<Curso, Long> {}
