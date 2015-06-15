package telefonica.aaee.marte.marte.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the view_ultimas_12_facturaciones database table.
 * 
 */
@Entity
@Table(name="view_ultimas_12_facturaciones")
@NamedQuery(name="FacturaPagaLibroFacturacion.findAll", query="SELECT f FROM FacturaPagaLibroFacturacion f")
public class FacturaPagaLibroFacturacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACUERDO_CONCERTADA")
	private String acuerdoConcertada;

	private String banco;

	@Column(name="BASE_IMPONIBLE")
	private double baseImponible;

	private String cargo;

	private String cif;

	private String cp;

	@Column(name="DESCRIPCION_CARGO")
	private String descripcionCargo;

	@Column(name="DESCRIPCION_FORMA_PAGO")
	private String descripcionFormaPago;

	@Column(name="DESCRIPCION_IMPUESTO")
	private String descripcionImpuesto;

	@Column(name="DIGITO_CONTROL")
	private String digitoControl;

	@Column(name="DIRECCION_ENVIO_FACTURA")
	private String direccionEnvioFactura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_EMISION")
	private Date fechaEmision;

	@Column(name="FORMA_PAGO_FX")
	private String formaPagoFx;

	@Column(name="NUMERO_CUENTA")
	private String numeroCuenta;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NUMERO_FACTURA")
	private String numeroFactura;

	@Column(name="PERSONA_CONTACTO")
	private String personaContacto;

	private String poblacion;

	private String sucursal;

	@Column(name="TIPO_DOC")
	private String tipoDoc;

	@Column(name="TIPO_IMPOSITIVO")
	private double tipoImpositivo;

	@Column(name="TIPO_IMPUESTO")
	private String tipoImpuesto;

	@Column(name="TOTAL_IMPUESTO")
	private double totalImpuesto;

	@Column(name="TOTAL_NETO")
	private double totalNeto;

	public FacturaPagaLibroFacturacion() {
	}

	public String getAcuerdoConcertada() {
		return this.acuerdoConcertada;
	}

	public void setAcuerdoConcertada(String acuerdoConcertada) {
		this.acuerdoConcertada = acuerdoConcertada;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public double getBaseImponible() {
		return this.baseImponible;
	}

	public void setBaseImponible(double baseImponible) {
		this.baseImponible = baseImponible;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDescripcionCargo() {
		return this.descripcionCargo;
	}

	public void setDescripcionCargo(String descripcionCargo) {
		this.descripcionCargo = descripcionCargo;
	}

	public String getDescripcionFormaPago() {
		return this.descripcionFormaPago;
	}

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	public String getDescripcionImpuesto() {
		return this.descripcionImpuesto;
	}

	public void setDescripcionImpuesto(String descripcionImpuesto) {
		this.descripcionImpuesto = descripcionImpuesto;
	}

	public String getDigitoControl() {
		return this.digitoControl;
	}

	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	public String getDireccionEnvioFactura() {
		return this.direccionEnvioFactura;
	}

	public void setDireccionEnvioFactura(String direccionEnvioFactura) {
		this.direccionEnvioFactura = direccionEnvioFactura;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFormaPagoFx() {
		return this.formaPagoFx;
	}

	public void setFormaPagoFx(String formaPagoFx) {
		this.formaPagoFx = formaPagoFx;
	}

	public String getNumeroCuenta() {
		return this.numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getPersonaContacto() {
		return this.personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public double getTipoImpositivo() {
		return this.tipoImpositivo;
	}

	public void setTipoImpositivo(double tipoImpositivo) {
		this.tipoImpositivo = tipoImpositivo;
	}

	public String getTipoImpuesto() {
		return this.tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public double getTotalImpuesto() {
		return this.totalImpuesto;
	}

	public void setTotalImpuesto(double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public double getTotalNeto() {
		return this.totalNeto;
	}

	public void setTotalNeto(double totalNeto) {
		this.totalNeto = totalNeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((acuerdoConcertada == null) ? 0 : acuerdoConcertada
						.hashCode());
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		long temp;
		temp = Double.doubleToLongBits(baseImponible);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime
				* result
				+ ((descripcionCargo == null) ? 0 : descripcionCargo.hashCode());
		result = prime
				* result
				+ ((descripcionFormaPago == null) ? 0 : descripcionFormaPago
						.hashCode());
		result = prime
				* result
				+ ((descripcionImpuesto == null) ? 0 : descripcionImpuesto
						.hashCode());
		result = prime * result
				+ ((digitoControl == null) ? 0 : digitoControl.hashCode());
		result = prime
				* result
				+ ((direccionEnvioFactura == null) ? 0 : direccionEnvioFactura
						.hashCode());
		result = prime * result
				+ ((fechaEmision == null) ? 0 : fechaEmision.hashCode());
		result = prime * result
				+ ((formaPagoFx == null) ? 0 : formaPagoFx.hashCode());
		result = prime * result
				+ ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
		result = prime * result
				+ ((numeroFactura == null) ? 0 : numeroFactura.hashCode());
		result = prime * result
				+ ((personaContacto == null) ? 0 : personaContacto.hashCode());
		result = prime * result
				+ ((poblacion == null) ? 0 : poblacion.hashCode());
		result = prime * result
				+ ((sucursal == null) ? 0 : sucursal.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		temp = Double.doubleToLongBits(tipoImpositivo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((tipoImpuesto == null) ? 0 : tipoImpuesto.hashCode());
		temp = Double.doubleToLongBits(totalImpuesto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalNeto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		FacturaPagaLibroFacturacion other = (FacturaPagaLibroFacturacion) obj;
		if (acuerdoConcertada == null) {
			if (other.acuerdoConcertada != null)
				return false;
		} else if (!acuerdoConcertada.equals(other.acuerdoConcertada))
			return false;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (Double.doubleToLongBits(baseImponible) != Double
				.doubleToLongBits(other.baseImponible))
			return false;
		if (cargo == null) {
			if (other.cargo != null)
				return false;
		} else if (!cargo.equals(other.cargo))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (descripcionCargo == null) {
			if (other.descripcionCargo != null)
				return false;
		} else if (!descripcionCargo.equals(other.descripcionCargo))
			return false;
		if (descripcionFormaPago == null) {
			if (other.descripcionFormaPago != null)
				return false;
		} else if (!descripcionFormaPago.equals(other.descripcionFormaPago))
			return false;
		if (descripcionImpuesto == null) {
			if (other.descripcionImpuesto != null)
				return false;
		} else if (!descripcionImpuesto.equals(other.descripcionImpuesto))
			return false;
		if (digitoControl == null) {
			if (other.digitoControl != null)
				return false;
		} else if (!digitoControl.equals(other.digitoControl))
			return false;
		if (direccionEnvioFactura == null) {
			if (other.direccionEnvioFactura != null)
				return false;
		} else if (!direccionEnvioFactura.equals(other.direccionEnvioFactura))
			return false;
		if (fechaEmision == null) {
			if (other.fechaEmision != null)
				return false;
		} else if (!fechaEmision.equals(other.fechaEmision))
			return false;
		if (formaPagoFx == null) {
			if (other.formaPagoFx != null)
				return false;
		} else if (!formaPagoFx.equals(other.formaPagoFx))
			return false;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		if (numeroFactura == null) {
			if (other.numeroFactura != null)
				return false;
		} else if (!numeroFactura.equals(other.numeroFactura))
			return false;
		if (personaContacto == null) {
			if (other.personaContacto != null)
				return false;
		} else if (!personaContacto.equals(other.personaContacto))
			return false;
		if (poblacion == null) {
			if (other.poblacion != null)
				return false;
		} else if (!poblacion.equals(other.poblacion))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (Double.doubleToLongBits(tipoImpositivo) != Double
				.doubleToLongBits(other.tipoImpositivo))
			return false;
		if (tipoImpuesto == null) {
			if (other.tipoImpuesto != null)
				return false;
		} else if (!tipoImpuesto.equals(other.tipoImpuesto))
			return false;
		if (Double.doubleToLongBits(totalImpuesto) != Double
				.doubleToLongBits(other.totalImpuesto))
			return false;
		if (Double.doubleToLongBits(totalNeto) != Double
				.doubleToLongBits(other.totalNeto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FacturaPagaLibroFacturacion [acuerdoConcertada=");
		builder.append(acuerdoConcertada);
		builder.append(", banco=");
		builder.append(banco);
		builder.append(", baseImponible=");
		builder.append(baseImponible);
		builder.append(", cargo=");
		builder.append(cargo);
		builder.append(", cif=");
		builder.append(cif);
		builder.append(", cp=");
		builder.append(cp);
		builder.append(", descripcionCargo=");
		builder.append(descripcionCargo);
		builder.append(", descripcionFormaPago=");
		builder.append(descripcionFormaPago);
		builder.append(", descripcionImpuesto=");
		builder.append(descripcionImpuesto);
		builder.append(", digitoControl=");
		builder.append(digitoControl);
		builder.append(", direccionEnvioFactura=");
		builder.append(direccionEnvioFactura);
		builder.append(", fechaEmision=");
		builder.append(fechaEmision);
		builder.append(", formaPagoFx=");
		builder.append(formaPagoFx);
		builder.append(", numeroCuenta=");
		builder.append(numeroCuenta);
		builder.append(", numeroFactura=");
		builder.append(numeroFactura);
		builder.append(", personaContacto=");
		builder.append(personaContacto);
		builder.append(", poblacion=");
		builder.append(poblacion);
		builder.append(", sucursal=");
		builder.append(sucursal);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", tipoImpositivo=");
		builder.append(tipoImpositivo);
		builder.append(", tipoImpuesto=");
		builder.append(tipoImpuesto);
		builder.append(", totalImpuesto=");
		builder.append(totalImpuesto);
		builder.append(", totalNeto=");
		builder.append(totalNeto);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}