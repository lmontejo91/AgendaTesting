/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.agenda.excepciones;

/**
 *
 * @author marco
 */
public class ContactoNoEncontradoException extends Exception{

    public ContactoNoEncontradoException() {
    }

    public ContactoNoEncontradoException(String message) {
        super(message);
    }

    public ContactoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactoNoEncontradoException(Throwable cause) {
        super(cause);
    }

    public ContactoNoEncontradoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
}
