package telefonica.aaee.marte.form;

import java.io.Serializable;

public class TramitacionModCCCForm extends TramitacionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String formaPagoFx;
	private String descripcionFormaPago;

	private String banco;
	private String sucursal;
	private String digitoControl;
	private String numeroCuenta;

	
	public TramitacionModCCCForm() {
		super();
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionModCCCForm [formaPagoFx=");
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
