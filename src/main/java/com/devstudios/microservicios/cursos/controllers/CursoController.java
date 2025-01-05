package com.devstudios.microservicios.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devstudios.microservicios.app.commons.controllers.CommonController;
import com.devstudios.microservicios.commons.examenes.entities.Examen;
import com.devstudios.microservicios.cursos.models.entities.Curso;
import com.devstudios.microservicios.cursos.services.CursoService;
import com.microservicio.commons.alumnos.models.entities.Alumno;

import jakarta.validation.Valid;




@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar( @PathVariable Long id, @Valid @RequestBody Curso curso, BindingResult result ){
        if( result.hasErrors() ) return validar(result);

        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()) return ResponseEntity.notFound().build();

        Curso cursoDb = c.get();
        if(curso.getName() != null) cursoDb.setName(curso.getName());

        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Alumno> alumnos){
        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()) return ResponseEntity.notFound().build();

        Curso cursoDb = c.get();
        alumnos.forEach(cursoDb::addAlumno);
        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumno, BindingResult result){
        if( result.hasErrors() ) return validar(result);
        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()){
            System.out.println("No viene");
            return ResponseEntity.notFound().build();
        }

        System.out.println("Alumno id: " + alumno.getId());

        Curso cursoDb = c.get();
        cursoDb.remove(alumno);
        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamens(@PathVariable Long id, @RequestBody List<Examen> examenes){
        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()) return ResponseEntity.notFound().build();

        Curso cursoDb = c.get();
        examenes.forEach(cursoDb::addExamen);
        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @Valid @RequestBody Examen examen, BindingResult result){
        if( result.hasErrors() ) return validar(result);
        Optional<Curso> c = this.service.findById(id);
        if(c.isEmpty()){
            System.out.println("No viene");
            return ResponseEntity.notFound().build();
        }

        System.out.println("Alumno id: " + examen.getId());

        Curso cursoDb = c.get();
        cursoDb.removeExamen(examen);
        return ResponseEntity.status(201).body(this.service.save(cursoDb));
    }


    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> findCursoByUserId(@PathVariable Long id){
        Optional<Curso> curso = this.service.findCursoByUserId(id);
        if( curso.isEmpty() ) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(curso.get());
    }


    protected ResponseEntity<?> validar( BindingResult response ){
        Map<String, Object> res = new HashMap<>();

        response.getFieldErrors()
            .forEach(e -> res.put(e.getField(), e.getDefaultMessage()));

        return ResponseEntity.badRequest().body(res);
    }
}
