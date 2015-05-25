package telefonica.aaee.marte.acuerdos.dao.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the marte_motivos_baja database table.
 * 
 */
@Entity
@Table(name="marte_motivos_baja")
public class MotivoBaja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_motivo_baja_MARTE", unique=true, nullable=false)
	private Long idMotivoBajaMARTE;

	@Lob
	@Column(name="desc_motivo_baja_FX", nullable=false)
	private String descMotivoBajaFX;

	@Lob
	@Column(name="desc_motivo_baja_MARTE", nullable=false)
	private String descMotivoBajaMARTE;

	@Column(name="id_motivo_Baja_FX")
	private Long idMotivoBajaFX;

	//bi-directional many-to-one association to TramitacionAPI
	@OneToMany(mappedBy="motivoBajaMARTE", cascade={CascadeType.ALL})
	private List<TramitacionAPI> tramitacionapis;

	public MotivoBaja() {
	}

	public Long getIdMotivoBajaMARTE() {
		return this.idMotivoBajaMARTE;
	}

	public void setIdMotivoBajaMARTE(Long idMotivoBajaMARTE) {
		this.idMotivoBajaMARTE = idMotivoBajaMARTE;
	}

	public String getDescMotivoBajaFX() {
		return this.descMotivoBajaFX;
	}

	public void setDescMotivoBajaFX(String descMotivoBajaFX) {
		this.descMotivoBajaFX = descMotivoBajaFX;
	}

	public String getDescMotivoBajaMARTE() {
		return this.descMotivoBajaMARTE;
	}

	public void setDescMotivoBajaMARTE(String descMotivoBajaMARTE) {
		this.descMotivoBajaMARTE = descMotivoBajaMARTE;
	}

	public Long getIdMotivoBajaFX() {
		return this.idMotivoBajaFX;
	}

	public void setIdMotivoBajaFX(Long idMotivoBajaFX) {
		this.idMotivoBajaFX = idMotivoBajaFX;
	}

	public List<TramitacionAPI> getTramitacionapis() {
		return this.tramitacionapis;
	}

	public void setTramitacionapis(List<TramitacionAPI> tramitacionapis) {
		this.tramitacionapis = tramitacionapis;
	}

	public TramitacionAPI addTramitacionapi(TramitacionAPI tramitacionapi) {
		getTramitacionapis().add(tramitacionapi);
		tramitacionapi.setMotivoBajaMARTE(this);

		return tramitacionapi;
	}

	public TramitacionAPI removeTramitacionapi(TramitacionAPI tramitacionapi) {
		getTramitacionapis().remove(tramitacionapi);
		tramitacionapi.setMotivoBajaMARTE(null);

		return tramitacionapi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((descMotivoBajaFX == null) ? 0 : descMotivoBajaFX.hashCode());
		result = prime
				* result
				+ ((descMotivoBajaMARTE == null) ? 0 : descMotivoBajaMARTE
						.hashCode());
		result = prime * result
				+ ((idMotivoBajaFX == null) ? 0 : idMotivoBajaFX.hashCode());
		result = prime
				* result
				+ ((idMotivoBajaMARTE == null) ? 0 : idMotivoBajaMARTE
						.hashCode());
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
		MotivoBaja other = (MotivoBaja) obj;
		if (descMotivoBajaFX == null) {
			if (other.descMotivoBajaFX != null)
				return false;
		} else if (!descMotivoBajaFX.equals(other.descMotivoBajaFX))
			return false;
		if (descMotivoBajaMARTE == null) {
			if (other.descMotivoBajaMARTE != null)
				return false;
		} else if (!descMotivoBajaMARTE.equals(other.descMotivoBajaMARTE))
			return false;
		if (idMotivoBajaFX == null) {
			if (other.idMotivoBajaFX != null)
				return false;
		} else if (!idMotivoBajaFX.equals(other.idMotivoBajaFX))
			return false;
		if (idMotivoBajaMARTE == null) {
			if (other.idMotivoBajaMARTE != null)
				return false;
		} else if (!idMotivoBajaMARTE.equals(other.idMotivoBajaMARTE))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MotivoBaja [idMotivoBajaMARTE=");
		builder.append(idMotivoBajaMARTE);
		builder.append(", descMotivoBajaMARTE=");
		builder.append(descMotivoBajaMARTE);
		builder.append(", idMotivoBajaFX=");
		builder.append(idMotivoBajaFX);
		builder.append(", descMotivoBajaFX=");
		builder.append(descMotivoBajaFX);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}