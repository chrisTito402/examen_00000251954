/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 *
 * @author chris
 */
public interface IObservable<M> {
    void suscribir(IObservador<M> o);
    void desuscribir(IObservador<M> o);
    void notificar(Evento e);
}