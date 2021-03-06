package modelo.entidad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * Clase que define los atributos y métodos de los objetos Autor generando la
 * tabla "autores" en la base de datos
 * 
 * @since 07.02.2022
 *
 */
@Entity
@Table(name = "autores")
public class Autor {

	// Atributos de la clase Autor
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String apellidos;
	
	private String fnacimiento;

	// Relación bidireccional one to many con Libro
	// Especificamos CascadeType.ALL
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	private List<Libro> librosDelAutor;

	// Relación bidireccional one to one con SGAE (a efectos del requeriento 3)
	@OneToOne(mappedBy = "autor", cascade = CascadeType.ALL)
	private Sgae sgae;

	// Constructores vacíos y con todo
	public Autor() {
		super();
	}

	public Autor(Integer id, String nombre, String apellidos, String fnacimiento, List<Libro> librosDelAutor, Sgae sgae) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fnacimiento = fnacimiento;
		this.librosDelAutor = librosDelAutor;
		this.sgae = sgae;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(String fnacimiento) {
		this.fnacimiento = fnacimiento;
	}

	public List<Libro> getLibrosDelAutor() {
		return librosDelAutor;
	}

	public void setLibrosDelAutor(List<Libro> librosDelAutor) {
		this.librosDelAutor = librosDelAutor;
	}

	public Sgae getSgae() {
		return sgae;
	}

	public void setSgae(Sgae sgae) {
		this.sgae = sgae;
	}

	/**
	 * Método que asigna un libro al autor en el caso de que no se encuentre ya
	 * asignado y asigna el autor al libro
	 * 
	 * @param libro Libro a asignar al autor
	 */
	public void asignarLibroAAutor(Libro libro) {
		librosDelAutor = new ArrayList<>();
		if (!librosDelAutor.contains(libro)) {
			librosDelAutor.add(libro);
			libro.setAutor(this);
		}
	}

	/**
	 * Método que asigna un sgae al autor y asigna el autor a la sgae
	 * 
	 * @param sgae Sgae a asignar al autor
	 */
	public void asignarSgaeAAutor(Sgae sgae) {
		this.setSgae(sgae);
		sgae.setAutor(this);
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fnacimiento=" + fnacimiento
				+ ", librosDelAutor=" + librosDelAutor + ", sgae=" + sgae + "]";
	}
}