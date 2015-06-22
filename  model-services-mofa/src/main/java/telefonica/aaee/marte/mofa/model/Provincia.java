package telefonica.aaee.marte.mofa.model;

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

}