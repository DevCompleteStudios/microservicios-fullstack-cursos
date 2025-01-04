package com.devstudios.microservicios.cursos.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devstudios.microservicios.cursos.models.entities.Curso;



public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c JOIN FETCH c.alumnos a WHERE a.id=?1")
    public Optional<Curso> findCursoByUserId(Long id);

}
