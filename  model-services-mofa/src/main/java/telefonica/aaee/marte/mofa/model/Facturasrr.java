package telefonica.aaee.marte.mofa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the facturasrr database table.
 * 
 */
@Entity
@Table(name="facturasrr")
@NamedQuery(name="Facturasrr.findAll", query="SELECT f FROM Facturasrr f")
public class Facturasrr implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FacturasrrPK id;

	@Column(name="CIF")
	private String cif;

	@Temporal(TemporalType.DATE)
	@Column(name="FechaEmision")
	private Date fechaEmision;

	@Column(name="Importe")
	private double importe;

	@Column(name="TipoDoc")
	private String tipoDoc;

	public Facturasrr() {
	}

	public FacturasrrPK getId() {
		return this.id;
	}

	public void setId(FacturasrrPK id) {
		this.id = id;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

}