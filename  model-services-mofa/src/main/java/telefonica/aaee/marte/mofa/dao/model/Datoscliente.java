package telefonica.aaee.marte.mofa.dao.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the datoscliente database table.
 * 
 */
@Entity
@Table(name="datoscliente")
@NamedQuery(name="Datoscliente.findAll", query="SELECT d FROM Datoscliente d")
public class Datoscliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String banco;

	@Column(name="CIF")
	private String cif;

	private String contacto;

	@Column(name="CUC")
	private String cuc;

	@Column(name="Cuenta")
	private String cuenta;

	@Column(name="DC")
	private String dc;

	@Column(name="Domicilio")
	private String domicilio;

	@Column(name="DP")
	private String dp;

	@Column(name="NombreEntidad")
	private String nombreEntidad;

	@Column(name="NombreSucursal")
	private String nombreSucursal;

	@Column(name="Pais")
	private String pais;

	@Column(name="Poblacion")
	private String poblacion;

	@Column(name="Provincia")
	private String provincia;

	private String sucursal;

	@Column(name="Territorio")
	private String territorio;

	private String tipodocumento;

	@Column(name="Titular")
	private String titular;

	public Datoscliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getCuc() {
		return this.cuc;
	}

	public void setCuc(String cuc) {
		this.cuc = cuc;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDc() {
		return this.dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDp() {
		return this.dp;
	}

	public void setDp(String dp) {
		this.dp = dp;
	}

	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getNombreSucursal() {
		return this.nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getTitular() {
		return this.titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

}