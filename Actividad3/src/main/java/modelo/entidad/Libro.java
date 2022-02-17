package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * Clase que define los atributos y métodos de los objetos Libro generando la
 * tabla "libros" en la base de datos
 * 
 * @since 07.02.2022
 *
 */
@Entity
@Table(name = "libros")
public class Libro {

	// Atributos de la clase Libro
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;

	private double precio;

	// Relación bidireccional many to one con Editorial
	@ManyToOne
	@JoinColumn(name = "fk_id_editorial", referencedColumnName = "id")
	private Editorial editorial;

	// Relación bidireccional many to one con Autor
	@ManyToOne
	@JoinColumn(name = "fk_id_autor", referencedColumnName = "id")
	private Autor autor;

	// Relación bidireccional many to many con Libreria
	@ManyToMany(mappedBy = "librosEnLibreria", cascade = CascadeType.PERSIST)
	private List<Libreria> libreriasConLibro;

	// Constructores vacíos y con todo
	public Libro() {
		super();
	}

	public Libro(Integer id, String titulo, double precio, Editorial editorial, Autor autor,
			List<Libreria> libreriasConLibro) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.editorial = editorial;
		this.autor = autor;
		this.libreriasConLibro = libreriasConLibro;
	}

	// Getter y setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Libreria> getLibreriasConLibro() {
		return libreriasConLibro;
	}

	public void setLibreriasConLibro(List<Libreria> libreriasConLibro) {
		this.libreriasConLibro = libreriasConLibro;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", precio=" + precio + "]";
	}
}