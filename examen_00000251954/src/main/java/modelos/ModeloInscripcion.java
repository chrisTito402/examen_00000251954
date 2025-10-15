/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import observer.Evento;
import observer.IObservable;
import observer.IObservador;

/**
 *
 * @author chris
 */
public class ModeloInscripcion implements IObservable<ModeloInscripcion> {

    private final Inscripcion inscripcion;      // entidad raíz
    private final List<Curso> disponibles = new ArrayList<>();
    private FichaPago ultimaFicha;
    private String ultimoError;

    private final List<IObservador<ModeloInscripcion>> observadores = new ArrayList<>();

    public ModeloInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    // ===== Estado que la Vista lee cuando recibe notificaciones =====
    public List<Curso> getDisponibles() {
        return List.copyOf(disponibles);
    }

    public List<Curso> getInscritos() {
        return inscripcion.getCursos();
    }

    public java.math.BigDecimal getTotal() {
        return inscripcion.calcularTotal();
    }

    public FichaPago getUltimaFicha() {
        return ultimaFicha;
    }

    public String getUltimoError() {
        return ultimoError;
    }

    //--------------LOGICA DEL CASO DEL USO---------------------
    /**
     * Metodo que carga los cursos iniciales
     * y notifica a los observadores para que la vista se pueda actualizar
     * @param catalogo lista de cursos iniciales
     */
    public void cargarCursos(List<Curso> catalogo) {
        disponibles.clear();
        disponibles.addAll(catalogo);
        notificar(Evento.LISTAS_ACTUALIZADAS);
    }

    /**
     * true si se inscribió; false si id inválido o duplicado.
     */
    public boolean inscribirPorId(int idCurso) {
        ultimoError = null;
        Optional<Curso> match = disponibles.stream().filter(c -> c.getId() == idCurso).findFirst();
        if (match.isEmpty()) {
            ultimoError = "Curso no encontrado";
            notificar(Evento.ERROR);
            return false;
        }
        try {
            inscripcion.agregarCurso(match.get());
            disponibles.remove(match.get());
            notificar(Evento.LISTAS_ACTUALIZADAS);
            notificar(Evento.TOTAL_ACTUALIZADO);
            return true;
        } catch (IllegalArgumentException dup) {
            ultimoError = dup.getMessage(); // "Curso ya inscrito"
            notificar(Evento.ERROR);
            return false;
        }
    }

    /**
     * true si finaliza; false si no hay cursos.
     */
    public boolean finalizarInscripcion() {
        ultimoError = null;
        try {
            ultimaFicha = inscripcion.finalizar();
            notificar(Evento.FICHA_GENERADA);
            return true;
        } catch (IllegalStateException e) {
            ultimoError = e.getMessage(); // "Seleccione al menos un curso"
            notificar(Evento.ERROR);
            return false;
        }
    }

    // -------------IMPLEMENTACION DEL PATRON OBSERVER----------------
    @Override
    public void suscribir(IObservador<ModeloInscripcion> o) {
        if (!observadores.contains(o)) {
            observadores.add(o);
        }
    }

    @Override
    public void desuscribir(IObservador<ModeloInscripcion> o) {
        observadores.remove(o);
    }

    @Override
    public void notificar(Evento e) {
        for (var o : observadores) {
            o.actualizar(e, this);
        }
    }
}
