package com.devstudios.microservicios.cursos.models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.devstudios.microservicios.commons.examenes.entities.Examen;
import com.microservicio.commons.alumnos.models.entities.Alumno;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;




@Entity
@Table(name="cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt = LocalDateTime.now();

    @OneToMany(fetch=FetchType.LAZY)
    private List<Alumno> alumnos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes = new ArrayList<>();


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }
    public void remove(Alumno alumno) {
        this.alumnos.remove(alumno);
    }
    public List<Examen> getExamenes() {
        return examenes;
    }
    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }
    public void removeExamen(Examen examen) {
        this.examenes.remove(examen);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
