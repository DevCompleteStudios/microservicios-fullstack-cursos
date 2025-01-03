package com.devstudios.microservicios.cursos.services;

import org.springframework.stereotype.Service;

import com.devstudios.microservicios.app.commons.services.CommonServiceImpl;
import com.devstudios.microservicios.cursos.models.entities.Curso;
import com.devstudios.microservicios.cursos.models.repositories.CursoRepository;



@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

}
