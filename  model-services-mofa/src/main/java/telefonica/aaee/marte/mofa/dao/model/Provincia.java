package telefonica.aaee.marte.mofa.dao.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_provincias database table.
 * 
 */
@Entity
@Table(name="t_provincias")
@NamedQuery(name="Provincia.findAll", query="SELECT p FROM Provincia p")
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CodProv")
	private String codProv;

	@Column(name="Provincia")
	private String provincia;

	public Provincia() {
	}

	public String getCodProv() {
		return this.codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codProv == null) ? 0 : codProv.hashCode());
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
		Provincia other = (Provincia) obj;
		if (codProv == null) {
			if (other.codProv != null)
				return false;
		} else if (!codProv.equals(other.codProv))
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
		builder.append("Provincia [codProv=").append(codProv)
				.append(", provincia=").append(provincia).append("]");
		return builder.toString();
	}
	
	

}