/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.List;
import java.util.stream.Collectors;
import modelos.Curso;
import modelos.ModeloInscripcion;
import observer.Evento;
import observer.IObservador;
import vistas.IVistaInscripcion;
import vistas.VistaInscripcionn;

/**
 *
 * @author chris
 */
public class ControladorInscripcion implements IObservador<ModeloInscripcion> {

    private final ModeloInscripcion modelo;
    private final VistaInscripcionn vista;

    public ControladorInscripcion(ModeloInscripcion modelo, VistaInscripcionn vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        this.modelo.suscribir(this);
    }

    /**
     * Método que se ejecuta cuando el usuario selecciona un curso en la vista
     * para inscribirse. Se obtiene el id del curso desde la lista de
     * disponibles y se manda al modelo para procesarlo.
     *
     * @param index es la poscicion del curso en la lista
     */
    public void inscribirCurso(int index) {
        // obtener id real del curso
        int id = modelo.getDisponibles().get(index).getId();
        modelo.inscribirPorId(id);
    }

    /**
     * Llama al modelo para finalizar la inscripción y generar la ficha.
     */
    public void finalizar() {
        modelo.finalizarInscripcion();
    }

    /**
     * Carga la lista de cursos disponibles en el modelo, la lista de curos
     * estan harcodeados.
     * @param catalogo lista de cursos inicial
     */
    public void cargarCursos(java.util.List<modelos.Curso> catalogo) {
        modelo.cargarCursos(catalogo);
    }

    /**
     * Este es el metodo del patron Observer, este mismo se ejecuta automaticamente
     * cuando el modelo notifica un cambio, este actua dependiendo el tipo de evento.
     * @param evento es un tipo de evento enum que dispara el modelo
     * @param m una instancia del modelo actualizada
     */
    @Override
    public void actualizar(Evento evento, ModeloInscripcion m) {
        switch (evento) {
            case LISTAS_ACTUALIZADAS, TOTAL_ACTUALIZADO -> {
                String[] disponibles = m.getDisponibles().stream()
                        .map(c -> c.getId() + " - " + c.getNombre() + " $" + c.getCosto())
                        .toArray(String[]::new);

                String[] inscritos = m.getInscritos().stream()
                        .map(c -> c.getId() + " - " + c.getNombre() + " $" + c.getCosto())
                        .toArray(String[]::new);

                vista.setCursosDisponibles(disponibles);
                vista.setCursosInscritos(inscritos);
                vista.setTotal(String.valueOf(m.getTotal()));
            }
            case FICHA_GENERADA -> {
                var f = m.getUltimaFicha();
                String detalle = "== Ficha #" + f.getId() + " ==\n"
                        + f.getCursos().stream()
                                .map(c -> "- " + c.getNombre() + " $" + c.getCosto())
                                .collect(Collectors.joining("\n"))
                        + "\nTOTAL: $" + f.getTotal();
                vista.mostrarFicha(detalle);
            }
            case ERROR ->
                vista.mostrarError(m.getUltimoError());
        }
    }
}
