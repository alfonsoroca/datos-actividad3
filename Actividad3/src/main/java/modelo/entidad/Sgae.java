package modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * Clase que va a establecer una relación one to one con Autor para dar
 * cumplimiento al requerimiento 3
 * 
 * @since 16.02.2022
 *
 */
@Entity
public class Sgae {
	
	// Atributos de la clase Autor
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String codigoSocio;
	
	// Relación bidireccional one to one con Autor (a efectos del requeriento 3)
	@OneToOne
	@JoinColumn(name="fk_autor", referencedColumnName = "id")
	private Autor autor;
	
	private String iban;
	
	// Constructores vacíos y con todo
	public Sgae() {
		super();
	}

	public Sgae(Integer id, String codigoSocio, Autor autor, String iban) {
		super();
		this.id = id;
		this.codigoSocio = codigoSocio;
		this.autor = autor;
		this.iban = iban;
	}

	
	// Getter y setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoSocio() {
		return codigoSocio;
	}

	public void setCodigoSocio(String codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "SGAE [id=" + id + ", codigoSocio=" + codigoSocio + ", autor=" + autor + ", iban=" + iban + "]";
	}	

}