package modelo.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Clase que define los atributos y métodos de los objetos Editorial generando
 * la tabla "editoriales" en la base de datos
 * 
 * @since 07.02.2022
 *
 */
@Entity
@Table(name = "editoriales")
public class Editorial {

	// Atributos de la clase Editorial
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String direccion;

	// Relación bidireccional one to many con Libro
	@OneToMany(mappedBy = "editorial")	
	private List<Libro> librosEnEditorial;

	// Constructores vacíos y con todo
	public Editorial() {
		super();
	}	

	public Editorial(Integer id, String nombre, String direccion, List<Libro> librosEnEditorial) {
		super();		
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.librosEnEditorial = librosEnEditorial;
	}
	
	// Getter y setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Libro> getLibrosEnEditorial() {
		return librosEnEditorial;
	}

	public void setLibrosEnEditorial(List<Libro> librosEnEditorial) {
		this.librosEnEditorial = librosEnEditorial;
	}
	
	/**
	 * Método que asigna un libro a una editorial en el caso de que no se encuentre ya
	 * asignado y asigna la editorial al libro
	 * 
	 * @param libro Libro a asignar a la editorial
	 */
	public void asignarLibroAEditorial(Libro libro) {
		librosEnEditorial = new ArrayList<Libro>();
		if (!librosEnEditorial.contains(libro)) {
			librosEnEditorial.add(libro);
			libro.setEditorial(this);
		}
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Editorial [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", librosEnEditorial="
				+ librosEnEditorial + "]";
	}
}