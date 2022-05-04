package ed.agenda.entidades;

import java.util.Objects;

/**
 * Clase que representa los tipos los datos básicos de un contacto en la agenda
 * @author Marcos
 */
public class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Contacto{" + "nombre=" + nombre + ", telefono=" + telefono + '}';
    }



	@Override
	public int hashCode() {
		return Objects.hash(nombre, telefono);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contacto other = (Contacto) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	}
    
    
}
