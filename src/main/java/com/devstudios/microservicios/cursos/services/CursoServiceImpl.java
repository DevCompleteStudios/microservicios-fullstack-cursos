package com.devstudios.microservicios.cursos.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devstudios.microservicios.app.commons.services.CommonServiceImpl;
import com.devstudios.microservicios.cursos.models.entities.Curso;
import com.devstudios.microservicios.cursos.models.repositories.CursoRepository;



@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

    @Override
    @Transactional(readOnly=true)
    public Optional<Curso> findCursoByUserId(Long id) {
        return this.repository.findCursoByUserId(id);
    }

}
