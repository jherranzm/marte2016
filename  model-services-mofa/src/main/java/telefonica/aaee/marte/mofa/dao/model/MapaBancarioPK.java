package telefonica.aaee.marte.mofa.dao.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the mapabancario database table.
 * 
 */
@Embeddable
public class MapaBancarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Entidad")
	private String entidad;

	@Column(name="Oficina")
	private String oficina;

	public MapaBancarioPK() {
	}
	public String getEntidad() {
		return this.entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getOficina() {
		return this.oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MapaBancarioPK)) {
			return false;
		}
		MapaBancarioPK castOther = (MapaBancarioPK)other;
		return 
			this.entidad.equals(castOther.entidad)
			&& this.oficina.equals(castOther.oficina);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.entidad.hashCode();
		hash = hash * prime + this.oficina.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapabancarioPK [entidad=").append(entidad)
				.append(", oficina=").append(oficina).append("]");
		return builder.toString();
	}
	
	
}