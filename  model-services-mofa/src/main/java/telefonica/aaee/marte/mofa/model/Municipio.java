package telefonica.aaee.marte.mofa.model;

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

	@Column(name="CodProv")
	private String codProv;

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

	public String getCodProv() {
		return this.codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}