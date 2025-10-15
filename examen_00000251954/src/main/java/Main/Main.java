import controladores.ControladorInscripcion;
import modelos.*;
import vistas.VistaInscripcionn;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- Modelo base ---
        Alumno alumno = new Alumno("A001", "Chris");
        Inscripcion inscripcion = new Inscripcion(1, alumno);
        ModeloInscripcion modelo = new ModeloInscripcion(inscripcion);

        // --- Vista (Swing) ---
        VistaInscripcionn vista = new VistaInscripcionn();

        // --- Controlador ---
        ControladorInscripcion ctrl = new ControladorInscripcion(modelo, vista);

        // --- Catálogo inicial ---
        List<Curso> catalogo = List.of(
                new Curso(1, "Matematicas", new BigDecimal("1500.00")),
                new Curso(2, "Estructuras de Datos", new BigDecimal("1800.00")),
                new Curso(3, "Bases de Datos", new BigDecimal("1600.00"))
        );

        // --- Cargar y mostrar ---
        ctrl.cargarCursos(catalogo);
        vista.setVisible(true);
    }
}
