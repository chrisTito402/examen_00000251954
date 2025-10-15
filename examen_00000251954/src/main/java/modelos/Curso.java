/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author chris
 */
public class Curso {
//
    private final int id;
    private final String nombre;
    private final BigDecimal costo;

    public Curso(int id, String nombre, BigDecimal costo) {
        if (id <= 0) {
            throw new IllegalArgumentException("id debe ser positivo");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre requerido");
        }
        if (costo == null || costo.signum() < 0) {
            throw new IllegalArgumentException("costo no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curso)) {
            return false;
        }
        Curso curso = (Curso) o;
        return id == curso.id; // igualdad por id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%d - %s ($%s)", id, nombre, costo);
    }
}
