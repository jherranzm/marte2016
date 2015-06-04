package telefonica.aaee.marte.acuerdos.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the marte_tsituacionplana database table.
 * 
 */
@Entity
@Table(name="marte_tsituacionplana")
@NamedQuery(name="SituacionPlana.findAll", query="SELECT s FROM SituacionPlana s")
public class SituacionPlana implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=14)
	private String idAcuerdo;

	@Column(name= "acuerdo", length=25)
	private String acuerdofx;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRecepcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTramitacionFX;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTramitacionGAE;

	//bi-directional one-to-one association to Acuerdo
//	@OneToOne
//	@JoinColumn(name="idAcuerdo", referencedColumnName="IDAcuerdo", nullable=false, insertable=false, updatable=false)
	@ManyToOne
	@JoinColumn(name="idAcuerdo")
	private Acuerdo acuerdo;

	//bi-directional many-to-one association to SituacionPlanaEstado
	@ManyToOne
	@JoinColumn(name="renovar")
	private SituacionPlanaEstado situacionPlanaEstado;

	public SituacionPlana() {
	}

	public String getIdAcuerdo() {
		return this.idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public String getAcuerdoFX() {
		return this.acuerdofx;
	}

	public void setAcuerdoFX(String acuerdofx) {
		this.acuerdofx = acuerdofx;
	}

	public Date getFechaRecepcion() {
		return this.fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaTramitacionFX() {
		return this.fechaTramitacionFX;
	}

	public void setFechaTramitacionFX(Date fechaTramitacionFX) {
		this.fechaTramitacionFX = fechaTramitacionFX;
	}

	public Date getFechaTramitacionGAE() {
		return this.fechaTramitacionGAE;
	}

	public void setFechaTramitacionGAE(Date fechaTramitacionGAE) {
		this.fechaTramitacionGAE = fechaTramitacionGAE;
	}

	public Acuerdo getAcuerdo() {
		return this.acuerdo;
	}

	public void setAcuerdo(Acuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	public SituacionPlanaEstado getSituacionPlanaEstado() {
		return this.situacionPlanaEstado;
	}

	public void setSituacionPlanaEstado(SituacionPlanaEstado spe) {
		this.situacionPlanaEstado = spe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acuerdofx == null) ? 0 : acuerdofx.hashCode());
		result = prime * result
				+ ((fechaRecepcion == null) ? 0 : fechaRecepcion.hashCode());
		result = prime
				* result
				+ ((fechaTramitacionFX == null) ? 0 : fechaTramitacionFX
						.hashCode());
		result = prime
				* result
				+ ((fechaTramitacionGAE == null) ? 0 : fechaTramitacionGAE
						.hashCode());
		result = prime * result
				+ ((idAcuerdo == null) ? 0 : idAcuerdo.hashCode());
		result = prime
				* result
				+ ((situacionPlanaEstado == null) ? 0 : situacionPlanaEstado
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
		SituacionPlana other = (SituacionPlana) obj;
		if (acuerdofx == null) {
			if (other.acuerdofx != null)
				return false;
		} else if (!acuerdofx.equals(other.acuerdofx))
			return false;
		if (fechaRecepcion == null) {
			if (other.fechaRecepcion != null)
				return false;
		} else if (!fechaRecepcion.equals(other.fechaRecepcion))
			return false;
		if (fechaTramitacionFX == null) {
			if (other.fechaTramitacionFX != null)
				return false;
		} else if (!fechaTramitacionFX.equals(other.fechaTramitacionFX))
			return false;
		if (fechaTramitacionGAE == null) {
			if (other.fechaTramitacionGAE != null)
				return false;
		} else if (!fechaTramitacionGAE.equals(other.fechaTramitacionGAE))
			return false;
		if (idAcuerdo == null) {
			if (other.idAcuerdo != null)
				return false;
		} else if (!idAcuerdo.equals(other.idAcuerdo))
			return false;
		if (situacionPlanaEstado == null) {
			if (other.situacionPlanaEstado != null)
				return false;
		} else if (!situacionPlanaEstado.equals(other.situacionPlanaEstado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SituacionPlana [acuerdo=");
		builder.append(acuerdo);
		builder.append(", situacionPlanaEstado=");
		builder.append(situacionPlanaEstado);
		builder.append(", acuerdofx=");
		builder.append(acuerdofx);
		builder.append(", fechaRecepcion=");
		builder.append(fechaRecepcion);
		builder.append(", fechaTramitacionFX=");
		builder.append(fechaTramitacionFX);
		builder.append(", fechaTramitacionGAE=");
		builder.append(fechaTramitacionGAE);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}