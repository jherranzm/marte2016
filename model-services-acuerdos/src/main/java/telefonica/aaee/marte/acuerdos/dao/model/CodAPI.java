package telefonica.aaee.marte.acuerdos.dao.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the marte_codapi database table.
 * 
 */
@Entity
@Table(name="marte_codapi")
@NamedQuery(name="CodAPI.findAll", query="SELECT c FROM CodAPI c")
public class CodAPI implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private Long id;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length=15)
	private String codApi;

	@Lob
	private String descripcion;

	@Column(name="fecha_creacion", nullable=false)
	private Timestamp fechaCreacion;

	public CodAPI() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodApi() {
		return this.codApi;
	}

	public void setCodApi(String codApi) {
		this.codApi = codApi;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CodAPI [id=");
		builder.append(id);
		builder.append(", codApi=");
		builder.append(codApi);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append("]");
		return builder.toString();
	}
	
	

}