package telefonica.aaee.marte.mofa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mapabancario database table.
 * 
 */
@Entity
@Table(name="mapabancario")
@NamedQuery(name="Mapabancario.findAll", query="SELECT m FROM Mapabancario m")
public class Mapabancario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MapabancarioPK id;

	@Column(name="NombreEntidad")
	private String nombreEntidad;

	@Column(name="NombreSucursal")
	private String nombreSucursal;

	public Mapabancario() {
	}

	public MapabancarioPK getId() {
		return this.id;
	}

	public void setId(MapabancarioPK id) {
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

}