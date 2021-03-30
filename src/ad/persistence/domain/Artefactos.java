package ad.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artefactos")
public class Artefactos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArtefacto;

	private String nombreArtefacto;

	private String tipoArtefacto;

	@ManyToOne
	@JoinColumn(name = "Personajes_idPersonaje")
	private Personaje personaje;

	public Artefactos() {

	}

	public Artefactos(String nombre, String tipo) {
		this.nombreArtefacto = nombre;
		this.tipoArtefacto = tipo;
	}

	public int getIdArtefacto() {
		return idArtefacto;
	}

	public void setIdArtefacto(int idArtefacto) {
		this.idArtefacto = idArtefacto;
	}

	public String getNombreArtefacto() {
		return nombreArtefacto;
	}

	public void setNombreArtefacto(String nombreArtefacto) {
		this.nombreArtefacto = nombreArtefacto;
	}

	public String getTipoArtefacto() {
		return tipoArtefacto;
	}

	public void setTipoArtefacto(String tipoArtefacto) {
		this.tipoArtefacto = tipoArtefacto;
	}

	@Override
	public String toString() {
		return "Artefacto del set: " + nombreArtefacto + " - Tipo de artefacto: " + tipoArtefacto;
	}

}
