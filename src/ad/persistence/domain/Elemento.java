package ad.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elementos")
public class Elemento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idElemento;

	private String nombreElemento;

	@OneToOne
	@JoinColumn(name = "Personajes_idPersonaje")
	private Personaje personaje;

	public Elemento() {

	}

	public Elemento(String nombre) {
		this.nombreElemento = nombre;
	}

	public int getIdElemento() {
		return idElemento;
	}

	public void setIdElemento(int idElemento) {
		this.idElemento = idElemento;
	}

	public String getNombreElemento() {
		return nombreElemento;
	}

	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombreElemento;
	}

}
