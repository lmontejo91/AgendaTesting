/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ed.agendatest.daotest;

import ed.agenda.dao.AgendaDao;
import ed.agenda.dao.AgendaDaoExcel;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import ed.agenda.excepciones.PosicionNoEncontradaException;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestAgendaDaoExcel {

//    Agenda agenda = new Agenda();
//    ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
//    ContactoPersona CP2 = new ContactoPersona("11221999", "Steve Jobs", "126588983");
//    ContactoPersona CP3 = new ContactoPersona("11221999", "Steve Wozniak", "126588983");
//    ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
//    ContactoEmpresa CE2 = new ContactoEmpresa("www.microsoft.com", "Microsoft", "789789987");
//    ContactoEmpresa CE3 = new ContactoEmpresa("www.apple.com", "Apple", "789789987");
//    CE1.setTrabajadores(new ArrayList<>(Arrays.asList(CP1)));
//    CE2.setTrabajadores(new ArrayList<>(Arrays.asList(CP2)));
//    CE3.setTrabajadores(new ArrayList<>(Arrays.asList(CP2, CP3)));
    @Test
    public void crearContactoEmpresaTest() {

        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");

        AgendaDao dao = new AgendaDaoExcel();

        dao.crearContactoEmpresa(CE1);

        assertTrue(true);

    }

    @Test
    public void crearContactoPersonaTest() {

        ContactoPersona CP1 = new ContactoPersona("11221999", "Bill Gates", "356562883");
        ContactoPersona CP2 = new ContactoPersona("11221999", "Steve Jobs", "126588983");
        ContactoEmpresa CE1 = new ContactoEmpresa("www.google.com", "Google", "123123123");
        CE1.setTrabajadores(new ArrayList<>(Arrays.asList(CP1)));
        AgendaDao dao = new AgendaDaoExcel();

        dao.crearContactoPersona(CP1);
        dao.crearContactoPersona(CP2);

        assertTrue(true);

    }

    @Test
    public void borrarContactoPorPosicionTest() throws PosicionNoEncontradaException {

        AgendaDao dao = new AgendaDaoExcel();
        dao.borrarContactoPorPosicion(0);
        assertTrue(true);

    }

}
