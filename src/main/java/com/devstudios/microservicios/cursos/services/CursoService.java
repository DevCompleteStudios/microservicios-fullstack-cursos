package com.devstudios.microservicios.cursos.services;

import java.util.Optional;

import com.devstudios.microservicios.app.commons.services.CommonService;
import com.devstudios.microservicios.cursos.models.entities.Curso;



public interface CursoService extends CommonService<Curso> {
    public Optional<Curso> findCursoByUserId(Long id);
}
