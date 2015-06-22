package telefonica.aaee.marte.mofa.dao.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the t_municipios database table.
 * 
 */
@Entity
@Table(name="t_municipios")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idMunicipio")
	private Long id;

	@Column(name="CodPostal")
	private String codPostal;

	//@Column(name="CodProv")
	@ManyToOne
	@JoinColumn(name="CodProv")
	private Provincia provincia;

	@Column(name="Municipio")
	private String municipio;

	public Municipio() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public Provincia getCodProv() {
		return this.provincia;
	}

	public void setCodProv(Provincia codProv) {
		this.provincia = codProv;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codPostal == null) ? 0 : codPostal.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((municipio == null) ? 0 : municipio.hashCode());
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
		Municipio other = (Municipio) obj;
		if (codPostal == null) {
			if (other.codPostal != null)
				return false;
		} else if (!codPostal.equals(other.codPostal))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Municipio [id=").append(id).append(", codPostal=")
				.append(codPostal).append(", codProv=").append(provincia)
				.append(", municipio=").append(municipio).append("]");
		return builder.toString();
	}
	
	
	

}