package telefonica.aaee.marte.mofa.dao.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the mapabancario database table.
 * 
 */
@Entity
@Table(name="mapabancario")
@NamedQuery(name="MapaBancario.findAll", query="SELECT m FROM MapaBancario m")
public class MapaBancario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MapaBancarioPK id;

	@Column(name="NombreEntidad")
	private String nombreEntidad;

	@Column(name="NombreSucursal")
	private String nombreSucursal;

	public MapaBancario() {
	}

	public MapaBancarioPK getId() {
		return this.id;
	}

	public void setId(MapaBancarioPK id) {
		this.id = id;
	}

	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getNombreSucursal() {
		return this.nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((nombreEntidad == null) ? 0 : nombreEntidad.hashCode());
		result = prime * result
				+ ((nombreSucursal == null) ? 0 : nombreSucursal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapaBancario other = (MapaBancario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreEntidad == null) {
			if (other.nombreEntidad != null)
				return false;
		} else if (!nombreEntidad.equals(other.nombreEntidad))
			return false;
		if (nombreSucursal == null) {
			if (other.nombreSucursal != null)
				return false;
		} else if (!nombreSucursal.equals(other.nombreSucursal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mapabancario [id=").append(id)
				.append(", nombreEntidad=").append(nombreEntidad)
				.append(", nombreSucursal=").append(nombreSucursal).append("]");
		return builder.toString();
	}
	
	

}