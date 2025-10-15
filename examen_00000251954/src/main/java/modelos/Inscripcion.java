/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class Inscripcion {

    private final int id;
    private final Alumno alumno;
    private final List<Curso> cursos = new ArrayList<>();

    public Inscripcion(int id, Alumno alumno) {
        if (id <= 0) {
            throw new IllegalArgumentException("id debe ser positivo");
        }
        if (alumno == null) {
            throw new IllegalArgumentException("alumno requerido");
        }
        this.id = id;
        this.alumno = alumno;
    }

    public int getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public List<Curso> getCursos() {
        return List.copyOf(cursos);
    }

    /**
     * /total derivado
     */
    public BigDecimal calcularTotal() {
        return cursos.stream()
                .map(Curso::getCosto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Regla: no duplicar cursos.
     */
    public void agregarCurso(Curso curso) {
        boolean dup = cursos.stream().anyMatch(c -> c.getId() == curso.getId());
        if (dup) {
            throw new IllegalArgumentException("Curso ya inscrito");
        }
        cursos.add(curso);
    }

    public FichaPago finalizar() {
        if (cursos.isEmpty()) {
            throw new IllegalStateException("Seleccione al menos un curso");
        }
        return new FichaPago(id, LocalDateTime.now(), cursos, calcularTotal());
    }
}
