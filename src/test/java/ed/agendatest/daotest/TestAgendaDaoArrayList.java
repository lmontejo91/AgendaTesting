/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.agendatest.daotest;

import ed.agenda.dao.AgendaDaoArrayList;
import ed.agenda.entidades.Contacto;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import ed.agenda.excepciones.ContactoNoEncontradoException;
import ed.agenda.excepciones.PosicionNoEncontradaException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class TestAgendaDaoArrayList {
    
    AgendaDaoArrayList dao = new AgendaDaoArrayList();
    
    @After
    public void vaciarArray(){
        dao.setContactos(new ArrayList<Contacto>());
    }
    
    @Test
    public void TestCrearContactoPersona (){
    
    ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "+34 981 666666");
    ContactoPersona CP2 = new ContactoPersona("11221999", "Bill6 Gates", "+34 981 666666");
    ContactoPersona CP3 = new ContactoPersona("11221999", "Bill Gates", "34 981 666666");
    ContactoPersona CP4 = new ContactoPersona("11221999", "Bill Gates", "+34 981 666666");
    ContactoPersona CP5 = new ContactoPersona("11221999", "Bil", "34 981 666666");
    ContactoPersona CP6 = new ContactoPersona("11221999", "Bi", "+34 981 666666");

        //Verifico que la agenda esta vacía
        assertTrue(dao.getContactos().isEmpty());
        //Añado el contacto persona que es CORRECTO
        dao.crearContactoPersona(CP1);
        //Verifico que la agenda contiene el contacto que yo he añadido
        assertTrue(dao.getContactos().contains(CP1));
        //Verifico que la agenda ha aumentado su cantidad de contactos en 1
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto persona con el nombre INCORRECTO (Contiene un carácter numérico)
        dao.crearContactoPersona(CP2);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CP2));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto persona con el telefono INCORRECTO (No contiene el caracter '+' y no cumple el patrón)
        dao.crearContactoPersona(CP3);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CP3));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto persona con un nombre REPETIDO (ya existe en la agenda)
        assertFalse(dao.crearContactoPersona(CP4));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto persona con un NOMBRE DE 3 CARACTERES (límite inferior)
        dao.crearContactoPersona(CP5);
        //Verifico que la agenda contiene el contacto que yo he añadido
        assertTrue(dao.getContactos().contains(CP5));
        //Verifico que la agenda ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==2);
        
        //Añado el contacto persona con el nombre NOMBRE DE 2 CARACTERES  (Nombre con nº caracteres inferior al límite inferior del patrón)
        dao.crearContactoPersona(CP6);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CP6));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==2);

    }
    
    @Test
    public void TestCrearContactoEmpresa (){
    
    ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
    ContactoEmpresa CE2 = new ContactoEmpresa("www.google.com", "Go7gle", "123123123");
    ContactoEmpresa CE3 = new ContactoEmpresa("www.google.comin", "Google", "123123123");
    ContactoEmpresa CE4 = new ContactoEmpresa("www.prueba.com", "Google", "125882323");
    ContactoEmpresa CE5 = new ContactoEmpresa("www.google.comin", "Go7gle", "123123123");
    ContactoEmpresa CE6 = new ContactoEmpresa("www.google.com", "Goo", "123123123");
    ContactoEmpresa CE7 = new ContactoEmpresa("www.google.com", "Go", "123123123");

        //Verifico que la agenda esta vacía
        assertTrue(dao.getContactos().isEmpty());
        //Añado el contacto empresa que es CORRECTO
        dao.crearContactoEmpresa(CE1);
        //Verifico que la agenda contiene el contacto que yo he añadido
        assertTrue(dao.getContactos().contains(CE1));
        //Verifico que la agenda ha aumentado su cantidad de contactos en 1
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto empresa con el nombre INCORRECTO (Contiene un carácter numérico)
        dao.crearContactoEmpresa(CE2);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CE2));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto empresa con la web INCORRECTA (No cumple el patrón ya que el dominio supera los 3 caracteres)
        dao.crearContactoEmpresa(CE3);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CE3));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto persona con un nombre REPETIDO (ya existe en la agenda)
        assertFalse(dao.crearContactoEmpresa(CE4));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
  
        //Añado el contacto empresa con el nombre y la web INCORRECTOS
        dao.crearContactoEmpresa(CE5);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CE5));
        //Verifico que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==1);
        
        //Añado el contacto empresa con un NOMBRE DE 3 CARACTERES (límite inferior)
        dao.crearContactoEmpresa(CE6);
        //Verifico que la agenda contiene el contacto que yo he añadido
        assertTrue(dao.getContactos().contains(CE6));
        //Verificao que la agenda ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==2);
        
        //Añado el contacto empresa con el nombre NOMBRE DE 2 CARACTERES (Nombre con nº caracteres inferior al límite inferior del patrón)
        dao.crearContactoEmpresa(CE7);
        //Verifico que la agenda NO contiene el contacto que yo he añadido
        assertFalse(dao.getContactos().contains(CE7));
        //Verificao que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().size()==2);

    }
    
    //Test para comprobar que el método borra correctamente un contacto.
    @Test
    public void TestBorrarContactoPorPosicion_CORRECTO() { 
        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "+34 981 666666");
        dao.crearContactoPersona(CP1);
        try{
            dao.borrarContactoPorPosicion(0);
        }catch(PosicionNoEncontradaException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        //Verifico que la agenda NO contiene el contacto que he borrado
        assertFalse(dao.getContactos().contains(CP1));
        //Verificao que la agenda NO ha aumentado su tamaño
        assertTrue(dao.getContactos().isEmpty());
    }
    
    //Test para comprobar que cuando la posición introducida es mayor que el tamaño de la agenda se lanza una Excepción.
    @Test(expected = PosicionNoEncontradaException.class)
    public void TestBorrarContactoPorPosicion_MayorQueAgenda() throws PosicionNoEncontradaException{ 
        dao.borrarContactoPorPosicion(10);
    }
    
    //Test para comprobar que cuando la posición introducida es negativa se lanza una Excepción.
    @Test(expected = PosicionNoEncontradaException.class)
    public void TestBorrarContactoPorPosicion_ValorNegativo() throws PosicionNoEncontradaException{ 
        dao.borrarContactoPorPosicion(-1);
        //Para hacer esta prueba se necesitó cambiar algo de código en el archivo AgendaDaoArrayList.java
        //AgendaDaoArrayList.java --> En línea 76 se añadió: " || index < 0"
    }
    
    @Test
    public void TestObtenerContactoPorPosicion_CORRECTO(){
        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "+34 981 666666");
        dao.crearContactoPersona(CP1);
        try{
            assertFalse(dao.borrarContactoPorPosicion(0));
        }catch(PosicionNoEncontradaException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }     
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestObtenerContactoPorPosicion_MayorQueAgenda(){
        dao.obtenerContactoPorPosicion(100);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void TestObtenerContactoPorPosicion_ValorNegativo(){
        dao.obtenerContactoPorPosicion(-1);
    }
    
    @Test
    public void TestMostrarTrabajadoresEmpresa_CORRECTO_SinTrabajadores(){
        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
        dao.crearContactoEmpresa(CE1);
        dao.mostrarTrabajadoresEmpresa("Google");
        
        //COMO COMPROBAR QUE SE ENTRA EN EL isEmpty();
        
    }
    
    /*Test para comprobar que se muestran los trabajadores de una empresa correctamente.
    Para poder realizar este test se debió añadir código:
    AgendaDaoArrayList.java --> método mostrarTrabajadoresEmpresa()
                                líneas 120 a 136.
    */
    @Test
    public void TestMostrarTrabajadoresEmpresa_CORRECTO_ConTrabajadores(){
        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
        ContactoPersona CP2 = new ContactoPersona("11221999", "Steve Jobs", "126588983");
        dao.crearContactoPersona(CP1);
        dao.crearContactoPersona(CP2);
        
        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
        dao.crearContactoEmpresa(CE1);
        CE1.setTrabajadores(new ArrayList<>(Arrays.asList(CP1, CP2)));
        
        dao.mostrarTrabajadoresEmpresa("Google");
        
        //COMO COMPROBAR QUE IMPRIME lista de trabajadores;
        
    }
    
    /*Test para comprobar que cuando el nombre de la empresa introducido no existe 
    en la Agenda se lanza una Excepción.
    Para poder realizar este test se debió añadir código:
    AgendaDaoArrayList.java --> método mostrarTrabajadoresEmpresa()
                                líneas 120 a 136.
    */
//    @Test(expected = ContactoNoEncontradoException.class)
//    public void TestMostrarTrabajadoresEmpresa_INCORRECTO(){
//        dao.mostrarTrabajadoresEmpresa("google");        
//    }
//    
//    @Test
//    public void TestAnadirTrabajadorAEmpresa_CORRECTO(){
//        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
//        ContactoPersona CP2 = new ContactoPersona("11221999", "Steve Jobs", "126588983");
//        dao.crearContactoPersona(CP1);
//        dao.crearContactoPersona(CP2);
//        
//        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
//        dao.crearContactoEmpresa(CE1);
//        
//        /*Esta parte comprueba que se introducen trabajadores correctamente en la empresa cuando ésta 
//        no tiene trabajadores --> El programa debería entrar el en IF de la línea 145 de AgendaDaoArrayList.java */
//        dao.anadirTrabajadorAEmpresa(CE1,CP1);
//        assertTrue(CE1.getTrabajadores().size()==1);
//        assertTrue(CE1.getTrabajadores().contains(CP1));
//        
//        /*Esta parte comprueba que se introducen trabajadores correctamente en la empresa cuando ya contiene 
//        trabajadores --> El programa debería saltar directamente a la línea 148 de AgendaDaoArrayList.java */
//        dao.anadirTrabajadorAEmpresa(CE1,CP2);
//        assertTrue(CE1.getTrabajadores().size()==2);
//        assertTrue(CE1.getTrabajadores().contains(CP2));
//    }   
//    
//    /*Comprueba que se lanza una Excepcion cuando el ContactoPersona que se intenta
//    añadir a la empresa no está en la Agenda.*/
//    @Test(expected = AssertionError.class)
//    public void TestAnadirTrabajadorAEmpresa_INCORRECTO01(){  
//        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
//        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
//        dao.crearContactoEmpresa(CE1);
//        
//        dao.anadirTrabajadorAEmpresa(CE1,CP1);
//    }   
//    
//    /*Comprueba que se lanza una Excepcion cuando el ContactoEmpresa en el que se intenta 
//    introducir trabajadores no existe en la Agenda.*/
//    @Test(expected = NullPointerException.class)
//    public void TestAnadirTrabajadorAEmpresa_INCORRECTO02(){  
//        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
//        dao.crearContactoPersona(CP1);
//        
//        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
//        
//        dao.anadirTrabajadorAEmpresa(CE1,CP1);
//    }   
//    
//    /*Comprueba que se lanza una Excepcion cuando ambos, ContactoEmpresa y ContactoPersona,
//    no existen en la Agenda.*/
//    @Test(expected = NullPointerException.class)
//    public void TestAnadirTrabajadorAEmpresa_INCORRECTO03(){  
//        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");     
//        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
//        
//        dao.anadirTrabajadorAEmpresa(CE1,CP1);
//    } 
//    
}
