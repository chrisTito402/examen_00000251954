///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package vistas;
//
///**
// *
// * @author chris
// */
//
//
//
//import controladores.ControladorInscripcion;
//import java.math.BigDecimal;
//import java.util.List;
//import modelos.*;
//import vistas.VistaInscripcion;
//
//public class Prueba {
//    public static void main(String[] args) {
//        // === Crear los objetos base ===
//        Alumno alumno = new Alumno("A001", "Christopher Álvarez");
//        Inscripcion inscripcion = new Inscripcion(1, alumno);
//        ModeloInscripcion modelo = new ModeloInscripcion(inscripcion);
//
//        // === Crear la vista y controlador ===
//        VistaInscripcion vista = new VistaInscripcion();
//        ControladorInscripcion ctrl = new ControladorInscripcion(modelo);
//
//        vista.setControlador(ctrl);
//        vista.suscribirAModelo(modelo);  // La vista observa al modelo
//
//        // === Crear un catálogo de cursos ===
//        List<Curso> catalogo = List.of(
//            new Curso(1, "Matemáticas", new BigDecimal("1000")),
//            new Curso(2, "Programación", new BigDecimal("1200")),
//            new Curso(3, "Historia", new BigDecimal("800"))
//        );
//
//        // === Simular acciones del usuario ===
//        vista.abrirPantalla(catalogo);  // Cargar cursos
//        vista.seleccionarCurso(2);      // Inscribir Programación
//        vista.seleccionarCurso(1);      // Inscribir Matemáticas
//        vista.finalizar();              // Generar ficha de pago
//    }
//}
//
