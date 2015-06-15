package telefonica.aaee.marte.marte.vo;

import java.io.Serializable;

import telefonica.aaee.marte.marte.model.FacturaPagaLibroFacturacion;

public class CuentaCorriente  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String formaPagoFx;
	private String descripcionFormaPago;

	private String banco;
	private String sucursal;
	private String digitoControl;
	private String numeroCuenta;

	public CuentaCorriente() {
	}
	
	public CuentaCorriente(FacturaPagaLibroFacturacion factura){
		this.formaPagoFx = factura.getFormaPagoFx();
		this.descripcionFormaPago = factura.getDescripcionFormaPago();

		this.banco = factura.getBanco();
		this.sucursal = factura.getSucursal();
		this.digitoControl = factura.getDigitoControl();
		this.numeroCuenta = factura.getNumeroCuenta();
	}

	public String getFormaPagoFx() {
		return formaPagoFx;
	}

	public void setFormaPagoFx(String formaPagoFx) {
		this.formaPagoFx = formaPagoFx;
	}

	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}

	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getDigitoControl() {
		return digitoControl;
	}

	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banco == null) ? 0 : banco.hashCode());
		result = prime
				* result
				+ ((descripcionFormaPago == null) ? 0 : descripcionFormaPago
						.hashCode());
		result = prime * result
				+ ((digitoControl == null) ? 0 : digitoControl.hashCode());
		result = prime * result
				+ ((formaPagoFx == null) ? 0 : formaPagoFx.hashCode());
		result = prime * result
				+ ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
		result = prime * result
				+ ((sucursal == null) ? 0 : sucursal.hashCode());
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
		CuentaCorriente other = (CuentaCorriente) obj;
		if (banco == null) {
			if (other.banco != null)
				return false;
		} else if (!banco.equals(other.banco))
			return false;
		if (descripcionFormaPago == null) {
			if (other.descripcionFormaPago != null)
				return false;
		} else if (!descripcionFormaPago.equals(other.descripcionFormaPago))
			return false;
		if (digitoControl == null) {
			if (other.digitoControl != null)
				return false;
		} else if (!digitoControl.equals(other.digitoControl))
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
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaCorriente [formaPagoFx=");
		builder.append(formaPagoFx);
		builder.append(", descripcionFormaPago=");
		builder.append(descripcionFormaPago);
		builder.append(", banco=");
		builder.append(banco);
		builder.append(", sucursal=");
		builder.append(sucursal);
		builder.append(", digitoControl=");
		builder.append(digitoControl);
		builder.append(", numeroCuenta=");
		builder.append(numeroCuenta);
		builder.append("]");
		return builder.toString();
	}
	
	
}
