package telefonica.aaee.marte.marte.vo;

import java.io.Serializable;

import telefonica.aaee.marte.marte.model.FacturaPagaLibroFacturacion;

public class DireccionEnvio  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String personaContacto;
	private String direccionEnvioFactura;
	private String poblacion;
	private String cp;

	public DireccionEnvio() {
	}

	public DireccionEnvio(FacturaPagaLibroFacturacion factura) {
		this.personaContacto = factura.getPersonaContacto();
		this.direccionEnvioFactura = factura.getDireccionEnvioFactura();
		this.poblacion = factura.getPoblacion();
		this.cp = factura.getCp();
	}
	
	public String getPersonaContacto() {
		return personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getDireccionEnvioFactura() {
		return direccionEnvioFactura;
	}

	public void setDireccionEnvioFactura(String direccionEnvioFactura) {
		this.direccionEnvioFactura = direccionEnvioFactura;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime
				* result
				+ ((direccionEnvioFactura == null) ? 0 : direccionEnvioFactura
						.hashCode());
		result = prime * result
				+ ((personaContacto == null) ? 0 : personaContacto.hashCode());
		result = prime * result
				+ ((poblacion == null) ? 0 : poblacion.hashCode());
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
		DireccionEnvio other = (DireccionEnvio) obj;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (direccionEnvioFactura == null) {
			if (other.direccionEnvioFactura != null)
				return false;
		} else if (!direccionEnvioFactura.equals(other.direccionEnvioFactura))
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
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DireccionEnvio [personaContacto=");
		builder.append(personaContacto);
		builder.append(", direccionEnvioFactura=");
		builder.append(direccionEnvioFactura);
		builder.append(", poblacion=");
		builder.append(poblacion);
		builder.append(", cp=");
		builder.append(cp);
		builder.append("]");
		return builder.toString();
	}
	
	
}
