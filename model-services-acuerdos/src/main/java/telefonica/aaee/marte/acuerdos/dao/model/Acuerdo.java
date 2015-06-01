package telefonica.aaee.marte.acuerdos.dao.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the vacuerdosplanaempresa database table.
 * 
 */
@Entity
@Table(name="vacuerdos", schema="acuerdos")
public class Acuerdo implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String IDAcuerdo;

	private String acuerdoNumero;

	private float descuentoPlanaMT;

	private float descuentoPlanaNM;

	private double importeFijoMT;

	private double importeFijoNM;

	private String modalidadConcertada;

	private String nombre;

	private String nuevoMercado;

	private String operadora;

	private String planaAutoajustable;

	private String planaSimplificada;

	private String segmento;

	@Column(name="CIF")
	private String cif;

	private String territorio;

	private String tipoDoc;
	
	@Column(name="BAJA")
	private boolean baja;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FechaBaja")
	private Date fechaBaja;
	
	@Column(name="CausaBaja")
	private String causaBaja;
	
	@Column(name="TipoAcuerdo")
	private String tipoAcuerdo;
	
	@Column(name="DescTipoAcuerdo")
	private String descTipoAcuerdo;
	
	@Transient
	public String acuerdoFX;
	
	//bi-directional many-to-one association to TramitacionAPI
	@OneToMany(mappedBy="acuerdo", cascade={CascadeType.ALL})
	private List<TramitacionAPI> tramitaciones;
	

	public Acuerdo() {
	}

	public String getAcuerdoNumero() {
		return acuerdoNumero;
	}

	public void setAcuerdoNumero(String acuerdoNumero) {
		this.acuerdoNumero = acuerdoNumero;
	}

	public float getDescuentoPlanaMT() {
		return descuentoPlanaMT;
	}

	public void setDescuentoPlanaMT(float descuentoPlanaMT) {
		this.descuentoPlanaMT = descuentoPlanaMT;
	}

	public float getDescuentoPlanaNM() {
		return descuentoPlanaNM;
	}

	public void setDescuentoPlanaNM(float descuentoPlanaNM) {
		this.descuentoPlanaNM = descuentoPlanaNM;
	}

	public String getIDAcuerdo() {
		return IDAcuerdo;
	}

	public void setIDAcuerdo(String iDAcuerdo) {
		IDAcuerdo = iDAcuerdo;
	}

	public double getImporteFijoMT() {
		return importeFijoMT;
	}

	public void setImporteFijoMT(double importeFijoMT) {
		this.importeFijoMT = importeFijoMT;
	}

	public double getImporteFijoNM() {
		return importeFijoNM;
	}

	public void setImporteFijoNM(double importeFijoNM) {
		this.importeFijoNM = importeFijoNM;
	}

	public String getModalidadConcertada() {
		return modalidadConcertada;
	}

	public void setModalidadConcertada(String modalidadConcertada) {
		this.modalidadConcertada = modalidadConcertada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNuevoMercado() {
		return nuevoMercado;
	}

	public void setNuevoMercado(String nuevoMercado) {
		this.nuevoMercado = nuevoMercado;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getPlanaAutoajustable() {
		return planaAutoajustable;
	}

	public void setPlanaAutoajustable(String planaAutoajustable) {
		this.planaAutoajustable = planaAutoajustable;
	}

	public String getPlanaSimplificada() {
		return planaSimplificada;
	}

	public void setPlanaSimplificada(String planaSimplificada) {
		this.planaSimplificada = planaSimplificada;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getCausaBaja() {
		return causaBaja;
	}

	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}

	public String getTipoAcuerdo() {
		return tipoAcuerdo;
	}

	public void setTipoAcuerdo(String tipoAcuerdo) {
		this.tipoAcuerdo = tipoAcuerdo;
	}
	
	public String getDescTipoAcuerdo() {
		return descTipoAcuerdo;
	}
	
	public void setDescTipoAcuerdo(String descTipoAcuerdo) {
		this.descTipoAcuerdo = descTipoAcuerdo;
	}
	
	public String getAcuerdoFX() {
		return String.format("%s%s  %s", this.tipoDoc, this.cif, this.acuerdoNumero);
	}

	public void setAcuerdoFX(String acuerdoFX) {
		this.acuerdoFX = acuerdoFX;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Acuerdo [acuerdoNumero=");
		builder.append(acuerdoNumero);
		builder.append(", descuentoPlanaMT=");
		builder.append(descuentoPlanaMT);
		builder.append(", descuentoPlanaNM=");
		builder.append(descuentoPlanaNM);
		builder.append(", IDAcuerdo=");
		builder.append(IDAcuerdo);
		builder.append(", importeFijoMT=");
		builder.append(importeFijoMT);
		builder.append(", importeFijoNM=");
		builder.append(importeFijoNM);
		builder.append(", modalidadConcertada=");
		builder.append(modalidadConcertada);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", nuevoMercado=");
		builder.append(nuevoMercado);
		builder.append(", operadora=");
		builder.append(operadora);
		builder.append(", planaAutoajustable=");
		builder.append(planaAutoajustable);
		builder.append(", planaSimplificada=");
		builder.append(planaSimplificada);
		builder.append(", segmento=");
		builder.append(segmento);
		builder.append(", cif=");
		builder.append(cif);
		builder.append(", territorio=");
		builder.append(territorio);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", baja=");
		builder.append(baja);
		builder.append(", fechaBaja=");
		builder.append(fechaBaja);
		builder.append(", causaBaja=");
		builder.append(causaBaja);
		builder.append(", tipoAcuerdo=");
		builder.append(tipoAcuerdo);
		builder.append(", descTipoAcuerdo=");
		builder.append(descTipoAcuerdo);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IDAcuerdo == null) ? 0 : IDAcuerdo.hashCode());
		result = prime * result
				+ ((acuerdoNumero == null) ? 0 : acuerdoNumero.hashCode());
		result = prime * result + (baja ? 1231 : 1237);
		result = prime * result
				+ ((causaBaja == null) ? 0 : causaBaja.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + Float.floatToIntBits(descuentoPlanaMT);
		result = prime * result + Float.floatToIntBits(descuentoPlanaNM);
		result = prime * result
				+ ((fechaBaja == null) ? 0 : fechaBaja.hashCode());
		long temp;
		temp = Double.doubleToLongBits(importeFijoMT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(importeFijoNM);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((modalidadConcertada == null) ? 0 : modalidadConcertada
						.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((nuevoMercado == null) ? 0 : nuevoMercado.hashCode());
		result = prime * result
				+ ((operadora == null) ? 0 : operadora.hashCode());
		result = prime
				* result
				+ ((planaAutoajustable == null) ? 0 : planaAutoajustable
						.hashCode());
		result = prime
				* result
				+ ((planaSimplificada == null) ? 0 : planaSimplificada
						.hashCode());
		result = prime * result
				+ ((segmento == null) ? 0 : segmento.hashCode());
		result = prime * result
				+ ((territorio == null) ? 0 : territorio.hashCode());
		result = prime * result
				+ ((tipoAcuerdo == null) ? 0 : tipoAcuerdo.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
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
		Acuerdo other = (Acuerdo) obj;
		if (IDAcuerdo == null) {
			if (other.IDAcuerdo != null)
				return false;
		} else if (!IDAcuerdo.equals(other.IDAcuerdo))
			return false;
		if (acuerdoNumero == null) {
			if (other.acuerdoNumero != null)
				return false;
		} else if (!acuerdoNumero.equals(other.acuerdoNumero))
			return false;
		if (baja != other.baja)
			return false;
		if (causaBaja == null) {
			if (other.causaBaja != null)
				return false;
		} else if (!causaBaja.equals(other.causaBaja))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (Float.floatToIntBits(descuentoPlanaMT) != Float
				.floatToIntBits(other.descuentoPlanaMT))
			return false;
		if (Float.floatToIntBits(descuentoPlanaNM) != Float
				.floatToIntBits(other.descuentoPlanaNM))
			return false;
		if (fechaBaja == null) {
			if (other.fechaBaja != null)
				return false;
		} else if (!fechaBaja.equals(other.fechaBaja))
			return false;
		if (Double.doubleToLongBits(importeFijoMT) != Double
				.doubleToLongBits(other.importeFijoMT))
			return false;
		if (Double.doubleToLongBits(importeFijoNM) != Double
				.doubleToLongBits(other.importeFijoNM))
			return false;
		if (modalidadConcertada == null) {
			if (other.modalidadConcertada != null)
				return false;
		} else if (!modalidadConcertada.equals(other.modalidadConcertada))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nuevoMercado == null) {
			if (other.nuevoMercado != null)
				return false;
		} else if (!nuevoMercado.equals(other.nuevoMercado))
			return false;
		if (operadora == null) {
			if (other.operadora != null)
				return false;
		} else if (!operadora.equals(other.operadora))
			return false;
		if (planaAutoajustable == null) {
			if (other.planaAutoajustable != null)
				return false;
		} else if (!planaAutoajustable.equals(other.planaAutoajustable))
			return false;
		if (planaSimplificada == null) {
			if (other.planaSimplificada != null)
				return false;
		} else if (!planaSimplificada.equals(other.planaSimplificada))
			return false;
		if (segmento == null) {
			if (other.segmento != null)
				return false;
		} else if (!segmento.equals(other.segmento))
			return false;
		if (territorio == null) {
			if (other.territorio != null)
				return false;
		} else if (!territorio.equals(other.territorio))
			return false;
		if (tipoAcuerdo == null) {
			if (other.tipoAcuerdo != null)
				return false;
		} else if (!tipoAcuerdo.equals(other.tipoAcuerdo))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		return true;
	}

	
}
