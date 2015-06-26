package telefonica.aaee.marte.form;

import java.io.Serializable;

public class DireccionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aa;
	private String direccion;
	private String localidad;
	private String cp;
	private String provincia;
	private int idMunicipio;

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

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aa == null) ? 0 : aa.hashCode());
		result = prime * result + ((cp == null) ? 0 : cp.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + idMunicipio;
		result = prime * result
				+ ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result
				+ ((provincia == null) ? 0 : provincia.hashCode());
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
		DireccionVO other = (DireccionVO) obj;
		if (aa == null) {
			if (other.aa != null)
				return false;
		} else if (!aa.equals(other.aa))
			return false;
		if (cp == null) {
			if (other.cp != null)
				return false;
		} else if (!cp.equals(other.cp))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idMunicipio != other.idMunicipio)
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DireccionVO [aa=").append(aa)
				.append(", direccion=").append(direccion)
				.append(", cp=").append(cp)
				.append(", idMunicipio=").append(idMunicipio)
				.append(", localidad=").append(localidad)
				.append(", provincia=").append(provincia)
				.append("]");
		return builder.toString();
	}

}
