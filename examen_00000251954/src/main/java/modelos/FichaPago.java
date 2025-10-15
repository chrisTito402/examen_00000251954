/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chris
 */
public class FichaPago {

    private final int id;
    private final LocalDateTime fecha;
    private final List<Curso> cursos;
    private final BigDecimal total;

    public FichaPago(int id, LocalDateTime fecha, List<Curso> cursos, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.cursos = List.copyOf(cursos);
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String detalle() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ficha #").append(id).append("  ").append(fecha).append("\n");
        cursos.forEach(c -> sb.append("- ").append(c.getNombre())
                .append("  $").append(c.getCosto()).append("\n"));
        sb.append("TOTAL: $").append(total);
        return sb.toString();
    }
}
