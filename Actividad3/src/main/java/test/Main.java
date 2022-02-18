package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Autor;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionLibrerias");
		EntityManager em = null;

		// Método estático que carga los datos en la base de datos
		InitBBDD.start();

		em = emf.createEntityManager();

		// Mostrar todos los libros dados de alta, con su editorial y su autor
		System.out.println("==============================================");
		Query query = em.createQuery("Select l from Libro l");
		List<Libro> listaLibros = query.getResultList();
		System.out.println("==== Listado de libros, editorial y autor ====");
		listarLibros(listaLibros);

		// Mostrar todos los autores dados de alta, con sus libros asociados
		System.out.println("==============================================");
		query = em.createQuery("Select a from Autor a");
		List<Autor> listaAutores = query.getResultList();
		System.out.println("==== Listado de autores y sus libros =========");
		listarAutores(listaAutores);

		// Mostrar todas las librerías, con solamente sus libros asociados
		System.out.println("==============================================");
		query = em.createQuery("Select l from Libreria l");
		List<Libreria> listaLibrerias = query.getResultList();
		System.out.println("==== Librerías y libros asociados ============");
		listarLibrerias(listaLibrerias);

		// Mostrar todos los libros dados de alta, y en la librería en la que están
		System.out.println("==============================================");
		query = em.createQuery("Select l from Libro l");
		List<Libro> listaLibrosLibrerias = query.getResultList();
		System.out.println("==== Libros en librerías =====================");
		listarLibrosLibrerias(listaLibrosLibrerias);

		em.close();
		emf.close();

	}

	// Mostrar todos los libros dados de alta, con su editorial y su autor
	// Utilizamos un bucle for-each para presentar la información
	private static void listarLibros(List<Libro> listaLibros) {
		for (Libro l : listaLibros) {
			System.out.println("Libro: " + l.getTitulo() + " / Editorial: " + l.getEditorial().getNombre() + " / Autor: " + l.getAutor().getNombre() + " "
					+ l.getAutor().getApellidos());
		}
	}

	// Mostrar todos los autores dados de alta, con sus libros asociados
	// Utilizamos dos bucles for-each para presentar la información
	private static void listarAutores(List<Autor> listaAutores) {
		for (Autor a : listaAutores) {
			System.out.println("Autor: " + a.getNombre() + " " + a.getApellidos());
			for (Libro l : a.getLibrosDelAutor()) {
				System.out.println("	" + l);
			}
		}
	}

	// Mostrar todas las librerías, con solamente sus libros asociados
	// Utilizamos dos bucles for-each para presentar la información
	private static void listarLibrerias(List<Libreria> listaLibrerias) {
		for (Libreria l : listaLibrerias) {
			System.out.println(l.getNombre());
			for (Libro libro : l.getLibrosEnLibreria()) {
				System.out.println("	" + libro);
			}
		}
	}

	// Mostrar todos los libros dados de alta, y en la librería en la que están
	// Utilizamos dos bucles for-each para presentar la información
	private static void listarLibrosLibrerias(List<Libro> listaLibrosLibrerias) {
		for (Libro l : listaLibrosLibrerias) {
			System.out.println(l);
			for (Libreria libreria : l.getLibreriasConLibro()) {
				System.out.println("	" + libreria.getNombre());
			}
		}
	}
}