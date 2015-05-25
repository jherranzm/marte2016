package telefonica.aaee.marte.acuerdos.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="marte_estado_tramitacion")
public class EstadoTramitacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=250)
	private String descripcion;

	@Column(nullable=false, length=25)
	private String estado;

	//bi-directional many-to-one association to TramitacionAPI
	@OneToMany(mappedBy="marteEstadoTramitacion")
	private List<TramitacionAPI> tramitacionapis;

	public EstadoTramitacion() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<TramitacionAPI> getTramitacionapis() {
		return this.tramitacionapis;
	}

	public void setTramitacionapis(List<TramitacionAPI> tramitacionapis) {
		this.tramitacionapis = tramitacionapis;
	}

	public TramitacionAPI addTramitacionapi(TramitacionAPI tramitacionapi) {
		getTramitacionapis().add(tramitacionapi);
		tramitacionapi.setMarteEstadoTramitacion(this);

		return tramitacionapi;
	}

	public TramitacionAPI removeTramitacionapi(TramitacionAPI tramitacionapi) {
		getTramitacionapis().remove(tramitacionapi);
		tramitacionapi.setMarteEstadoTramitacion(null);

		return tramitacionapi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EstadoTramitacion other = (EstadoTramitacion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EstadoTramitacion [id=");
		builder.append(id);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}