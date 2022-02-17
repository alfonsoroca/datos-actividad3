package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Autor;
import modelo.entidad.Editorial;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;
import modelo.entidad.Sgae;

/**
 * 
 * Clase que va a crear los objetos y realizar las asignaciones pertinentes
 * entre ellos para el requrimiento
 * 
 * @since 16.02.2022
 *
 */
public class InitBBDD {

	// Para cada conjunto de objetos que se instancian creamos una lista que los
	// almacene para su mejor tratamiento
	private static Autor autor1, autor2, autor3;
	private static List<Autor> listaAutores = new ArrayList<>();

	private static Editorial editorial1, editorial2;
	private static List<Editorial> listaEditoriales = new ArrayList<>();

	private static Libro libro1, libro2, libro3, libro4, libro5, libro6, libro7, libro8;
	private static List<Libro> listaLibros = new ArrayList<>();

	private static Libreria libreria1, libreria2;
	private static List<Libreria> listaLibrerias = new ArrayList<>();

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionLibrerias");
	private static EntityManager em = null;

	public static void start() {

		altasRequerimiento();
		asignacionesManyToOne();
		asignacionesOneToMany();
		asignacionesManyToMany();

		em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(autor1);
		em.persist(autor2);
		em.persist(autor3);

		em.persist(editorial1);
		em.persist(editorial2);

		em.persist(libro1);
		em.persist(libro2);
		em.persist(libro3);
		em.persist(libro4);
		em.persist(libro5);
		em.persist(libro6);
		em.persist(libro7);
		em.persist(libro8);

		em.persist(libreria1);
		em.persist(libreria2);

		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	// Método que crea los objetos solicitados en el requerimiento
	private static void altasRequerimiento() {

		// Creación de 3 autores
		autor1 = new Autor(null, "Miguel", "De Cervantes", null, null, null);
		autor2 = new Autor(null, "Francisco", "Ibañez", null, null, null);
		autor3 = new Autor(null, "Andrzej", "Sapkowski", null, null, null);
		// Sgae a efectos del requerimiento 3
		autor1.setSgae(new Sgae(null, "a-03215", autor1, "0182-1119"));
		autor2.setSgae(new Sgae(null, "b-66465", autor2, "2100-0046"));
		autor3.setSgae(new Sgae(null, "c-98741", autor3, "128-0055"));
		listaAutores.add(autor1);
		listaAutores.add(autor2);
		listaAutores.add(autor3);

		// Creación de 2 editoriales
		editorial1 = new Editorial(null, "Espasa Calpe", "Calle de la Letra A", null);
		editorial2 = new Editorial(null, "Austral", "Calle del lapicero", null);
		listaEditoriales.add(editorial1);
		listaEditoriales.add(editorial2);

		// Creación de 8 libros
		libro1 = new Libro(null, "Don Quijote de La Mancha", 50.00, null, null, null);
		libro2 = new Libro(null, "La Galatea", 45.50, null, null, null);
		libro3 = new Libro(null, "Mortadelo y Filemón", 35.00, null, null, null);
		libro4 = new Libro(null, "Pepe Gotera y Otilio", 35.00, null, null, null);
		libro5 = new Libro(null, "Rompetechos", 35.00, null, null, null);
		libro6 = new Libro(null, "El último deseo", 15.99, null, null, null);
		libro7 = new Libro(null, "Bautismo de fuego", 15.99, null, null, null);
		libro8 = new Libro(null, "Camino sin retorno", 15.99, null, null, null);
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);

		// Creación de 2 librerías
		libreria1 = new Libreria(null, "Librería Studio", "Paco Libros", null);
		libreria2 = new Libreria(null, "Librería La Moderna", "Frida Khalo", null);
		listaLibrerias.add(libreria1);
		listaLibrerias.add(libreria2);

	}

	// Método que asigna a los objetos del extremo Many de la relación OneToMany
	private static void asignacionesManyToOne() {

		// Autores a libros
		libro1.setAutor(autor1);
		libro2.setAutor(autor1);

		libro3.setAutor(autor2);
		libro4.setAutor(autor2);
		libro5.setAutor(autor2);

		libro6.setAutor(autor3);
		libro7.setAutor(autor3);
		libro8.setAutor(autor3);

		// Editoriales a libros
		libro1.setEditorial(editorial1);
		libro2.setEditorial(editorial1);
		libro3.setEditorial(editorial1);
		libro4.setEditorial(editorial1);

		libro5.setEditorial(editorial2);
		libro6.setEditorial(editorial2);
		libro7.setEditorial(editorial2);
		libro8.setEditorial(editorial2);

	}

	// Método que asigna a los objetos del extremo One de la relación OneToMany
	private static void asignacionesOneToMany() {

		Autor autor = new Autor();
		Editorial editorial = new Editorial();
		List<Libro> librosAutores = new ArrayList<>();
		List<Libro> librosEditoriales = new ArrayList<>();

		// Libros a autores
		// Utilizamos 2 bucles para asignar el listado de libros a cada autor
		for (int i = 0; i < listaAutores.size(); i++) {
			autor = listaAutores.get(i);
			for (int j = 0; j < listaLibros.size(); j++) {
				if (listaLibros.get(j).getAutor() == autor) {
					librosAutores.add(listaLibros.get(j));
				}
			}
			autor.setLibrosDelAutor(librosAutores);

			// Vaciamos la lista para la próxima iteración
			librosAutores.clear();
		}

		// Libros a editoriales
		// Utilizamos 2 bucles para asiganr el listado de libros a cada editorial
		for (int i = 0; i < listaEditoriales.size(); i++) {
			editorial = listaEditoriales.get(i);
			for (int j = 0; j < listaLibros.size(); j++) {
				if (listaLibros.get(j).getEditorial() == editorial) {
					librosEditoriales.add(listaLibros.get(j));
				}
			}
			editorial.setLibrosEnEditorial(librosEditoriales);

			// Vaciamos la lista para la próxima iteración
			librosEditoriales.clear();
		}
	}

	// Método que asigna a los objetos en las relaciones Many to Many
	private static void asignacionesManyToMany() {

		Libro libro = new Libro();
		// Hay 3 listados de librerias: uno con cada una de las librerias y uno con
		// las dos librerías (listaLibrerias)
		List<Libreria> listadoLibreria1 = new ArrayList<>();
		listadoLibreria1.add(libreria1);
		List<Libreria> listadoLibreria2 = new ArrayList<>();
		listadoLibreria2.add(libreria2);

		List<Libro> librosLibreria1 = new ArrayList<>();
		List<Libro> librosLibreria2 = new ArrayList<>();

		/*
		 * Condiciones de asignación de libros a las librerías
		 * 
		 * Aunque el enunciado decía que cada librería tendrá 4 libros dados de alta
		 * previamente, hemos querido complicarlo con las siguientes condiciones
		 * 
		 * Los libros que ocupan las primeras posiciones de la listaLibros se encuentran
		 * en ambas librerias
		 * 
		 * Los libros que ocupan las posiciones 5 y 6 de la listaLibros se encuentra en
		 * la libreria1
		 * 
		 * Los libros que ocupan las posiciones 7 y 8 de la listaLibros se encuentra en
		 * la libreria2
		 */

		for (int i = 0; i < listaLibros.size(); i++) {

			if (i < 4) {
				libro = listaLibros.get(i);
				// Añadimos el libro a librosLibreria1
				librosLibreria1.add(libro);
				// Añadimos el libro a librosLibreria2
				librosLibreria2.add(libro);
				// Añadimos el libro a ambas librerias
				libro.setLibreriasConLibro(listaLibrerias);

			} else if (i < 6) {
				libro = listaLibros.get(i);
				// Añadimos el libro a librosLibreria1
				librosLibreria1.add(libro);
				// Añadimos el libro a la libreria1
				libro.setLibreriasConLibro(listadoLibreria1);

			} else {
				libro = listaLibros.get(i);
				/// Añadimos el libro a librosLibreria2
				librosLibreria2.add(libro);
				// Añadimos el libro a la libreria2
				libro.setLibreriasConLibro(listadoLibreria2);
			}
		}
		// Asignamos la lista de libros a cada libreria
		libreria1.setLibrosEnLibreria(librosLibreria1);
		libreria2.setLibrosEnLibreria(librosLibreria2);
	}
}