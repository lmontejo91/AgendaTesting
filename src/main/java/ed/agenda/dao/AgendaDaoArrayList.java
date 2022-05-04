/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.agenda.dao;

import ed.agenda.entidades.Contacto;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import ed.agenda.excepciones.ContactoNoEncontradoException;
import ed.agenda.excepciones.PosicionNoEncontradaException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marcos
 */
public class AgendaDaoArrayList implements AgendaDao {

	String nomre;
	static ArrayList<Contacto> contactos = new ArrayList<Contacto>();

	public static ArrayList<Contacto> getContactos() {
		return contactos;
	}

	public static void setContactos(ArrayList<Contacto> contactos) {
		AgendaDaoArrayList.contactos = contactos;
	}

	@Override
	public boolean crearContactoPersona(ContactoPersona c) {
		Pattern pat = Pattern.compile("[a-zA-Z ]{3,15}");
		Matcher mat = pat.matcher(c.getNombre());
		
		Pattern pat2 = Pattern.compile("/\\+34 9[0-9]{1,2} [0-9]{7}/");
		Matcher mat2 = pat2.matcher(c.getNombre());

		if (!mat.matches() && !mat2.matches()) {
			return false;
		}

		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equalsIgnoreCase(c.getNombre())) {
				return false;
			}
		}
		contactos.add(c);
		return true;
	}

	@Override
	public boolean crearContactoEmpresa(ContactoEmpresa c) {
		Pattern pat = Pattern.compile("[a-zA-Z ]{3,20}");
		Matcher mat = pat.matcher(c.getNombre());
		
		Pattern pat2 = Pattern.compile("^[w]{3}[\\.][\\w]+([\\.]+[\\w]{2,3})$");
                //"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
		Matcher mat2 = pat.matcher(c.getPagweb());
		if (!mat.matches() && !mat2.matches()) {
			return false;
		}
		
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equalsIgnoreCase(c.getNombre())) {
				return false;
			}
		}
		contactos.add(c);
		return true;
	}

	@Override
	public boolean borrarContactoPorPosicion(int index) throws PosicionNoEncontradaException {
		if (index > contactos.size() || index < 0)
			throw new PosicionNoEncontradaException();

		System.out.println("Borrado " + contactos.get(index).toString());
		contactos.remove(index);
		return false;
	}

	@Override
	public Contacto obtenerContactoPorPosicion(int index) {
		return contactos.get(index);
	}

	@Override
	public Contacto obtenerContactoPorNombre(String nombre) {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre() ==(nombre)) {
				System.out.println("Se encuentra en la posición " + i);
				return contactos.get(i);
			}
		}
		return null;
	}

	@Override
	public void listarContactos() {
		for (int i = 0; i < contactos.size(); i++) {
			System.out.println(contactos.get(i).toString());
		}
	}

	@Override
	public boolean eliminarContactoPorNombre(String nombre) throws ContactoNoEncontradoException {
		for (int i = 0; i < contactos.size(); i++) {
			if (contactos.get(i).getNombre().equalsIgnoreCase(nomre)) {
				contactos.remove(contactos.get(i));
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ContactoPersona> mostrarTrabajadoresEmpresa(String nombre)  {
            try{
                Contacto empresa = obtenerContactoPorNombre(nombre);
                if (empresa instanceof ContactoEmpresa){
                    if(((ContactoEmpresa)empresa).getTrabajadores().isEmpty()){
                        System.out.println("No hay trabajadores en la empresa");
                        return null;
                    }else{
                        List<ContactoPersona> trabajadores = new ArrayList<ContactoPersona>();
                        for (ContactoPersona trabajador : ((ContactoEmpresa) empresa).getTrabajadores()) {
                                trabajadores.add(trabajador);
                        } 
                        return trabajadores;
                    } 
                }else{
                    throw new ContactoNoEncontradoException("El contacto de la empresa inroducido no existe");
                }
            }catch(NullPointerException | ContactoNoEncontradoException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
                return null;
            }
	}
        
        @Override
        public void anadirTrabajadorAEmpresa(ContactoEmpresa ce, ContactoPersona cp) {

            if (ce.getTrabajadores()==null)
                ce.setTrabajadores(new ArrayList<ContactoPersona>());
        
            ce.getTrabajadores().add(cp);

        }

}
