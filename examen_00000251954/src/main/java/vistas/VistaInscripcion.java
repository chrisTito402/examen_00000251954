//package vistas;
//
//import controladores.ControladorInscripcion;
//import java.util.List;
//import modelos.Curso;
//import modelos.FichaPago;
//import modelos.ModeloInscripcion;
//import observer.Evento;
//import observer.IObservador;
//
//public class VistaInscripcion implements IObservador<ModeloInscripcion> {
//
//    private ControladorInscripcion ctrl;
//
//    public void setControlador(ControladorInscripcion ctrl) {
//        this.ctrl = ctrl;
//    }
//
//    // Se suscribe al modelo
//    public void suscribirAModelo(ModeloInscripcion modelo) {
//        modelo.suscribir(this);
//    }
//
//    // ---- Métodos de "UI" (solo muestran datos) ----
//    private void renderListas(ModeloInscripcion m) {
//        System.out.println("\n== DISPONIBLES ==");
//        m.getDisponibles().forEach(c -> System.out.println("  " + c));
//        System.out.println("== INSCRITOS ==");
//        m.getInscritos().forEach(c -> System.out.println("  " + c));
//        System.out.println("TOTAL: $" + m.getTotal());
//    }
//
//    private void renderFicha(FichaPago f) {
//        System.out.println("\n== FICHA DE PAGO ==");
//        System.out.println(f.detalle());
//    }
//
//    private void renderError(String msg) {
//        System.out.println("[ERROR] " + msg);
//    }
//
//    // ---- IObservador: reacciona SOLO a notificaciones del Modelo ----
//    @Override
//    public void actualizar(Evento evento, ModeloInscripcion m) {
//        switch (evento) {
//            case LISTAS_ACTUALIZADAS, TOTAL_ACTUALIZADO ->
//                renderListas(m);
//            case FICHA_GENERADA ->
//                renderFicha(m.getUltimaFicha());
//            case ERROR ->
//                renderError(m.getUltimoError());
//        }
//    }
//
//    // ---- Simulación de acciones del usuario ----
//    public void abrirPantalla(List<Curso> catalogo) {
//        ctrl.cargarCursos(catalogo);
//    }
//
//    public void seleccionarCurso(int id) {
//        ctrl.inscribirCurso(id);
//    }
//
//    public void finalizar() {
//        ctrl.finalizar();
//    }
//}
