package ed.agenda.entidades;

import java.util.List;
import java.util.Objects;

/**
 * Esta clase representa a objetos que son contactos de Empresas.
 * 
 * @author Marcos
 */
public class ContactoEmpresa extends Contacto {

	private int id;
	private String pagweb;

	private List<ContactoPersona> trabajadores;

	public ContactoEmpresa(String pagweb, String nombre, String telefono) {
		super(nombre, telefono);
		this.pagweb = pagweb;
		this.id= hashCode();
	}

	public String getPagweb() {
		return pagweb;
	}

	public void setPagweb(String pagweb) {
		this.pagweb = pagweb;
	}

	public List<ContactoPersona> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<ContactoPersona> trabajadores) {
		this.trabajadores = trabajadores;
		for(ContactoPersona c: trabajadores) {
			
			c.setIdEmpresa(this.getId());
		}	
	}

	@Override
	public String toString() {
		return "ContactoEmpresa{" + " pagweb: " + pagweb + " Nombre: " + super.getNombre() + " Telefono: "
				+ super.getTelefono() + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.getNombre());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactoEmpresa other = (ContactoEmpresa) obj;
		return Objects.equals(pagweb, other.pagweb);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
