package ed.agenda.entidades;

import java.util.Objects;

/**
 * Esta clase representa a objetos que son contactos de personas
 *
 * @author Marcos
 */
public class ContactoPersona extends Contacto {

    private String cumpleanos;
    private int idEmpresa;


    public ContactoPersona(String cumpleanos,
             String nombre, String telefono) {
        super(nombre, telefono);
        this.cumpleanos = cumpleanos;
        ;
    }

    public void setCumpleAnos(String cumpleaAnos) {
        this.cumpleanos = cumpleaAnos;
    }

    public String getCumpleanos() {
        return cumpleanos;
    }

    @Override
    public String toString() {
        return "Persona{ cumpleaños: " + cumpleanos
                + " Nombre: " + super.getNombre()
                + " Telefono: " + super.getTelefono() + '}';
    }

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public void setCumpleanos(String cumpleanos) {
		this.cumpleanos = cumpleanos;
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
		ContactoPersona other = (ContactoPersona) obj;
		return Objects.equals(cumpleanos, other.cumpleanos) && Objects.equals(idEmpresa, other.idEmpresa);
	}

}
