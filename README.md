# Introducción

En la rama main del repositorio se encuentra el código de la actividad "AE-3. JPA" realizado por el grupo.

# Estructura de la aplicación

Para la resolución de la actividad hemos utilizado un proyecto Maven con las especificaciones que se encuentran en el archivo ![pom](/Actividad3/pom.xml).

Las conexiones con la base de datos se encuentran en el fichero ![persistence.xml](/Actividad3/src/main/resources/META-INF/persistence.xml)

La estructura por la que hemos optado es para la resolución de la actividad la siguiente.![]()

## Paquete modelo-entidad
El paquete ![modelo.entidad](/Actividad3/src/main/java/modelo/entidad/) contiene las clases que van a definir las entidades y relaciones de la base de datos.

### Autor
El código de la clase ![Autor](Actividad3/src/main/java/modelo/entidad/Autor.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Editorial
El código de la clase ![Editorial](Actividad3/src/main/java/modelo/entidad/Editorial.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Librería
El código de la clase ![Libreria](Actividad3/src/main/java/modelo/entidad/Libreria.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Libro
El código de la clase ![Libro](Actividad3/src/main/java/modelo/entidad/Libro.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Sgae
El código de la clase ![Sgae](Actividad3/src/main/java/modelo/entidad/Sgae.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

Esta clase tiene como finalidad dar contestación al requerimiento 3 de la actividad que nos solicita representar todas las relacioneses estudiadas por lo que decidimos crear esta clase que establece una relación *One to One* con Autor.

De esta manera, en el modelo mostrado quedan recogidas las relaciones:
- One to one.- Entre Autor y Sgae.
- One to Many.- Entre Autor y Libro, Libro y Lditorial.
- Many to Many.- Entre Libro y Libreria.

## Métodos específicos para cumplir con la bidireccionalidad en las relaciones
En las clases que tienen una relación One to Many hemos implementado un método en la entidad del lado *One* que se encarga de dar cumplimiento a la bidireccionalidad de la relación.

A continuación se extracta dicho código

```java
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

```

```java
	/**
	 * Método que asigna un sgae al autor y asigna el autor a la sgae
	 * 
	 * @param sgae Sgae a asignar al autor
	 */
	public void asignarSgaeAAutor(Sgae sgae) {
		this.setSgae(sgae);
		sgae.setAutor(this);
	}
```

```java
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
  ```


## Paquete test
El paquete ![test](/Actividad3/src/main/java/test/) contiene la clase ![InitBBDD](/Actividad3/src/main/java/test/InitBBDD.java) que realiza la carga de los datos del requerimiento 1 en la bases de datos así como la clase ![Main](/Actividad3/src/main/java/test/Main.java) que se encarga de lanzar la aplicación y de ejecutar las consultas a la base de datos solicitadas en el requerimiento 2.


# Base de datos
La estructura de la base que da resolución a los requerimientos de la actividad es la que se muestra en la imagen a continuación. ![](/Actividad3/images/bbdd.png)

Esta estructura viene definida por las clases que definen cada una de las entidades que con forman la base de datos.





