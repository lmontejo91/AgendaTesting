/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.agenda;

import ed.agenda.dao.AgendaDao;
import ed.agenda.dao.AgendaDaoArrayList;
import ed.agenda.dao.AgendaDaoExcel;
import ed.agenda.entidades.Contacto;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import ed.agenda.excepciones.ContactoNoEncontradoException;
import ed.agenda.excepciones.PosicionNoEncontradaException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class agendatest {

    public static void main(String[] args) {

        AgendaDao agenda = new AgendaDaoArrayList();

        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
        ContactoPersona CP2 = new ContactoPersona("11221999", "Steve Jobs", "126588983");
        ContactoPersona CP3 = new ContactoPersona("11221999", "Steve Wozniak", "126588983");
        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
        ContactoEmpresa CE2 = new ContactoEmpresa("www.microsoft.com", "Microsoft", "789789987");
        ContactoEmpresa CE3 = new ContactoEmpresa("www.apple.com", "Apple", "789789987");
        CE1.setTrabajadores(new ArrayList<>(Arrays.asList(CP1)));
        CE2.setTrabajadores(new ArrayList<>(Arrays.asList(CP2)));
        CE3.setTrabajadores(new ArrayList<>(Arrays.asList(CP2, CP3)));

        agenda.crearContactoPersona(CP1);
        agenda.crearContactoPersona(CP2);
        agenda.crearContactoPersona(CP3);
        agenda.crearContactoEmpresa(CE1);
        agenda.crearContactoEmpresa(CE2);
        agenda.crearContactoEmpresa(CE3);

        Boolean salir = true;
        Scanner teclado = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("_________________Mi agenda________________");
            System.out.println("      Escriba el numero de la opción seleccionada");
            System.out.println("a) Imprimir contactos");
            System.out.println("b) Imprimir contacto por posicion");
            System.out.println("c) Añadir Contacto empresa");
            System.out.println("d) Añadir Contacto persona");
            System.out.println("e) Eliminar contacto");
            System.out.println("f) Buscar contacto");
            System.out.println("g) Obtener posición de contacto");
            System.out.println("h) Mostrar trabajadores de una empresa");
            System.out.println("i) Añadir trabajador a empresa");

            System.out.println("j) Salir");

            opcion = teclado.nextLine();
            String nombre, telefono, pagweb, cumpleanos;
            Contacto c;
            
            try {
            switch (opcion) {

                case "a":
                    agenda.listarContactos();
                    break;
                case "b":
                    System.out.println("Escribe posicion del contacto");
                    int posicion = teclado.nextInt();

                    try {
                        Contacto contacto = agenda.obtenerContactoPorPosicion(posicion);
                        System.out.println(contacto.toString());
                    } catch (PosicionNoEncontradaException e) {
                        System.out.println("No existe la posicion indicada");
                    }
                    break;

                case "c":
                    System.out.print("Escribe nombre empresa: ");
                    nombre = teclado.nextLine();
                    System.out.print("Escribe telefono empresa: ");
                    telefono = teclado.nextLine();
                    System.out.print("Escribe pag web empresa: ");
                    pagweb = teclado.nextLine();

                    ContactoEmpresa cEmpresa = new ContactoEmpresa(pagweb, nombre, opcion);
                    agenda.crearContactoEmpresa(cEmpresa);
                    break;

                case "d":
                    System.out.print("Escribe nombre persona: ");
                    nombre = teclado.nextLine();
                    System.out.print("Escribe telefono persona: ");
                    telefono = teclado.nextLine();
                    System.out.print("Escribe pag web cumpleaños: ");
                    cumpleanos = teclado.nextLine();

                    ContactoPersona cPersona = new ContactoPersona(cumpleanos, nombre, telefono);
                    agenda.crearContactoPersona(cPersona);
                    break;

                case "e":
                    System.out.println("Escribe el nombre del contacto a eliminar");
                    nombre = teclado.nextLine();
                    agenda.eliminarContactoPorNombre(nombre);
                    break;

                case "f":
                    System.out.println("Escribe el nombre del contacto a buscar");
                    nombre = teclado.nextLine();
                    System.err.println(agenda.obtenerContactoPorNombre(nombre).toString());

                    break;

                case "g":
                    System.out.println("Escribe el nombre del contacto a buscar");
                    nombre = teclado.nextLine();
                    System.err.println("El contacto se encuntra en la poscion " + agenda.obtenerContactoPorNombre(nombre));

                    break;

                case "h":
                    System.out.println("Escribe el nombre de la empresa");
                    nombre = teclado.nextLine();
                    agenda.mostrarTrabajadoresEmpresa(nombre);
                    break;

                case "i":
                    System.out.println("Escribe el nombre del trabajador");
                    nombre = teclado.nextLine();
                    ContactoPersona trabajador = (ContactoPersona) agenda.obtenerContactoPorNombre(nombre);

                    System.out.println("Escribe el nombre de la empresa");
                    nombre = teclado.nextLine();
                    ContactoEmpresa empresa = (ContactoEmpresa) agenda.obtenerContactoPorNombre(nombre);

                    if (trabajador == null && empresa == null) {
                        System.out.println("No se ha encontrado el trabajdor o la empresa");
                    } else {
                        empresa.getTrabajadores().add(trabajador);
                    }
                    salir = false;
                    break;

                case "j":
                    salir = false;
                    break;
            }

            
            }catch(ContactoNoEncontradoException e){
                System.out.println("Contacto no encontrado prueba otra opcion");
            }
        } while (salir);
    }

}
