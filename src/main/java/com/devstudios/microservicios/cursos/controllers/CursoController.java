package com.devstudios.microservicios.cursos.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devstudios.microservicios.app.commons.controllers.CommonController;
import com.devstudios.microservicios.cursos.models.entities.Curso;
import com.devstudios.microservicios.cursos.services.CursoService;




@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar( @PathVariable Long id, @RequestBody Curso curso ){
        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()) return ResponseEntity.notFound().build();

        Curso cursoDb = c.get();
        if(curso.getName() != null) cursoDb.setName(curso.getName());

        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }

}
