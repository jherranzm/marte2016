package telefonica.aaee.marte.mofa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the facturasrr database table.
 * 
 */
@Embeddable
public class FacturasrrPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Factura")
	private String factura;

	@Column(name="FacturaRR")
	private String facturaRR;

	@Column(name="Fichero")
	private String fichero;

	public FacturasrrPK() {
	}
	public String getFactura() {
		return this.factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getFacturaRR() {
		return this.facturaRR;
	}
	public void setFacturaRR(String facturaRR) {
		this.facturaRR = facturaRR;
	}
	public String getFichero() {
		return this.fichero;
	}
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FacturasrrPK)) {
			return false;
		}
		FacturasrrPK castOther = (FacturasrrPK)other;
		return 
			this.factura.equals(castOther.factura)
			&& this.facturaRR.equals(castOther.facturaRR)
			&& this.fichero.equals(castOther.fichero);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.factura.hashCode();
		hash = hash * prime + this.facturaRR.hashCode();
		hash = hash * prime + this.fichero.hashCode();
		
		return hash;
	}
}