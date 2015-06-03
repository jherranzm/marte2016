package telefonica.aaee.marte.acuerdos.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the marte_usuarios database table.
 * 
 */
@Entity
@Table(name="marte_usuarios", schema="acuerdos")
@NamedQuery(name="MarteUsuario.findAll", query="SELECT m FROM MarteUsuario m")
public class MarteUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private Long idUsuario;

	private int accessos;

	@Column(length=60)
	private String apellido1;

	@Column(length=60)
	private String apellido2;

	@Column(length=5)
	private String codOficina;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(length=10)
	private String codUsuario;

	@Column(length=250)
	private String email;

	@Column(name="Fecha_Ultimo_Acceso", nullable=false)
	private Timestamp fechaUltimoAcceso;

	@Column(length=12)
	private String newPass;

	@Column(length=60)
	private String nombre;

	@Column(length=12)
	private String pass;

	@Column(name="Pass_enc", length=60)
	private String passEnc;

	@Column(length=15)
	private String tipoUsuario;

	public MarteUsuario() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getAccessos() {
		return this.accessos;
	}

	public void setAccessos(int accessos) {
		this.accessos = accessos;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCodOficina() {
		return this.codOficina;
	}

	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}

	public String getCodUsuario() {
		return this.codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getFechaUltimoAcceso() {
		return this.fechaUltimoAcceso;
	}

	public void setFechaUltimoAcceso(Timestamp fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}

	public String getNewPass() {
		return this.newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassEnc() {
		return this.passEnc;
	}

	public void setPassEnc(String passEnc) {
		this.passEnc = passEnc;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MarteUsuario [idUsuario=");
		builder.append(idUsuario);
		builder.append(", accessos=");
		builder.append(accessos);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", codOficina=");
		builder.append(codOficina);
		builder.append(", codUsuario=");
		builder.append(codUsuario);
		builder.append(", email=");
		builder.append(email);
		builder.append(", fechaUltimoAcceso=");
		builder.append(fechaUltimoAcceso);
		builder.append(", newPass=");
		builder.append(newPass);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", pass=");
		builder.append(pass);
		builder.append(", passEnc=");
		builder.append(passEnc);
		builder.append(", tipoUsuario=");
		builder.append(tipoUsuario);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}