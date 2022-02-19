package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * Clase que define los atributos y métodos de los objetos Libreria generando la
 * tabla "librerias" en la base de datos
 * 
 * @since 07.02.2022
 *
 */
@Entity
@Table(name = "librerias")
public class Libreria {

	// Atributos de la clase Libreria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String propietario;

	// Relación bidireccional many to many con Libro
	@ManyToMany(cascade = CascadeType.PERSIST)
	// Creamos una tabla intermedia denominada librerias_libros a la que añadimos
	// como FK las PK de las tablas de origen
	@JoinTable(name = "librerias_libros", joinColumns = {
			@JoinColumn(name = "fk_id_libreria", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_id_libro", referencedColumnName = "id") })
	private List<Libro> librosEnLibreria;

	// Constructores vacíos y con todo
	public Libreria() {
		super();
	}

	public Libreria(Integer id, String nombre, String propietario, List<Libro> librosEnLibreria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.librosEnLibreria = librosEnLibreria;
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

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public List<Libro> getLibrosEnLibreria() {
		return librosEnLibreria;
	}

	public void setLibrosEnLibreria(List<Libro> librosEnLibreria) {
		this.librosEnLibreria = librosEnLibreria;
	}

	// Sobrescritura del método toString()	
	@Override
	public String toString() {
		return "Libreria [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", librosEnLibreria="
				+ librosEnLibreria + "]";
	}
}