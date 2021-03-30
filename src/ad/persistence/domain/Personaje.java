package ad.persistence.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "personajes")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonaje;

	private String nombrePersonaje;

	private String nivelPersonaje;

	@OneToOne(mappedBy = "personaje")
	private Elemento elemento;

	@OneToMany(mappedBy = "personaje")
	private Set<Artefactos> artefactos;

	public Personaje() {

	}

	public Personaje(String nombre, String nivel) {
		this.nombrePersonaje = nombre;
		this.nivelPersonaje = nivel;
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}

	public String getNivelPersonaje() {
		return nivelPersonaje;
	}

	public void setNivelPersonaje(String nivelPersonaje) {
		this.nivelPersonaje = nivelPersonaje;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombrePersonaje + " - Nivel: " + nivelPersonaje;
	}

}
