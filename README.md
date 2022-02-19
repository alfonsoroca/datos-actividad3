# Introducción

En la rama main del repositorio se encuentra el código de la actividad "AE-3. JPA" realizada por el grupo.

# Estructura de la aplicación

Para la resolución de la actividad hemos utilizado un proyecto Maven con las especificaciones que se encuentran en el archivo ![pom](/Actividad3/pom.xml).

Las conexiones con la base de datos se encuentran en el fichero ![persistence.xml](/Actividad3/src/main/resources/META-INF/persistence.xml)

La estructura por la que hemos optado es para la resolución de la actividad la siguiente.![](/Actividad3/images/estructura.png)

## Paquete modelo-entidad
El paquete ![modelo.entidad](/Actividad3/src/main/java/modelo/entidad/) contiene las clases que van a definir las entidades y relaciones de la base de datos.

### Clase Autor
El código de la clase ![Autor](Actividad3/src/main/java/modelo/entidad/Autor.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Clase Editorial
El código de la clase ![Editorial](Actividad3/src/main/java/modelo/entidad/Editorial.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Clase Librería
El código de la clase ![Libreria](Actividad3/src/main/java/modelo/entidad/Libreria.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Clase Libro
El código de la clase ![Libro](Actividad3/src/main/java/modelo/entidad/Libro.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

### Clase Sgae
El código de la clase ![Sgae](Actividad3/src/main/java/modelo/entidad/Sgae.java) recoge los campos de la entidad así como sus distintas relaciones con el resto de entidades.

Esta clase tiene como finalidad dar contestación al requerimiento 3 de la actividad que nos solicita representar todas las relacioneses estudiadas por lo que decidimos crear esta clase que establece una relación *One to One* con Autor.

De esta manera, en el modelo mostrado quedan recogidas las relaciones:
- One to one.- Entre Autor y Sgae.
- One to Many.- Entre Autor y Libro, Libro y Editorial.
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
El paquete ![test](/Actividad3/src/main/java/test/) contiene 2 clases que ejecutan la aplicación y la carga de datos de la base de datos.

### Clase InitBBDD
La clase ![InitBBDD](/Actividad3/src/main/java/test/InitBBDD.java) realiza la carga de los datos del requerimiento 1 en la bases de datos.

### Clase Main
La clase ![Main](/Actividad3/src/main/java/test/Main.java) se encarga de lanzar la aplicación y de ejecutar las consultas a la base de datos solicitadas en el requerimiento 2.

# Base de datos
La estructura de la base que da resolución a los requerimientos de la actividad es la que se muestra en la imagen a continuación. ![](/Actividad3/images/bbdd.png)

Esta estructura viene establecida por las clases que definen cada una de las entidades que con forman la base de datos.

# Requerimiento 1
A continuación se muestra el resultado de la aplicación al ejecutar el código que da solución al requerimiento 1 de la actividad.

### Autores ![](/Actividad3/images/bbdd_autores.png)
### Editoriales ![](/Actividad3/images/bbdd_editoriales.png)
### Librerias ![](/Actividad3/images/bbdd_librerias.png)
### Librerias_Libros
En este caso en lugar de 4 libros a cada librería, como indicaba el requerimiento hemos querido complicarlo con las siguientes condiciones:
- Los libros que ocupan las posiciones 0,1,2,3 de la listaLibros se encuentran en ambas librerias. 
- Los libros que ocupan las posiciones 4,5 de la listaLibros se encuentra en la libreria denominada en el código como libreria1.
- Los libros que ocupan las posiciones 6,7 de la listaLibros se encuentra en la libreria denominada en el código como libreria2.

![](/Actividad3/images/bbdd_librerias_libros.png)
### Libros ![](/Actividad3/images/bbdd_libros.png)
### Sgae ![](/Actividad3/images/bbdd_sgae.png)

# Requerimiento 2

### 1. Mostrar todos los libros dados de alta, con su editorial y su autor
![](/Actividad3/images/requerimiento_2_1.png)

### 2. Mostrar todos los autores dados de alta, con sus libros asociados
![](/Actividad3/images/requerimiento_2_2.png)

### 3. Mostrar todas las librerías, con solamente sus libros asociados
![](/Actividad3/images/requerimiento_2_3.png)

### 4. Mostrar todos los libros dados de alta, y en la librería en la que están
![](/Actividad3/images/requerimiento_2_4.png)

# Requerimiento 3
El modelo de datos que recoge todas las relaciones estudiadas se ha realizado al añadir la clase Sgae que establece una relación One to One con la clase Autor de tal manera que las relaciones presentes en la aplicación son las siguientes:

- One to one.- Entre Autor y Sgae.
- One to Many.- Entre Autor y Libro, Libro y Editorial.
- Many to Many.- Entre Libro y Libreria.

