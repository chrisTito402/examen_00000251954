/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vistas;

/**
 *
 * @author chris
 */
public interface IVistaInscripcion {
    // la vista SOLO pinta primitivos/strings

    void setCursosDisponibles(String[] items);

    void setCursosInscritos(String[] items);

    void setTotal(String total);

    void mostrarError(String msg);

    void mostrarFicha(String detalle);

    // el controlador se inyecta
    void setControlador(controladores.ControladorInscripcion c);
}
