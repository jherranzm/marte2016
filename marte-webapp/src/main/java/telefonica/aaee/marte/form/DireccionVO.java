package telefonica.aaee.marte.form;

import java.io.Serializable;

public class DireccionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aa;
	private String direccion;
	private String localidad;
	private String cp;
	private String provincia;

	public DireccionVO() {
	}

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


}
