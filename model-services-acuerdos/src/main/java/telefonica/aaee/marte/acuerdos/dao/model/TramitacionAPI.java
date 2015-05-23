package telefonica.aaee.marte.acuerdos.dao.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tramitacionapis database table.
 * 
 */
@Entity
@Table(name="tramitacionapis")
public class TramitacionAPI implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="AcuerdoN")
	private String acuerdoNumero;

	@Column(name="AutoajusModImporte")
	private String planaAutoajustable;

	private String autorizacion;

	private String bajaCliente;

	@Column(name="C_Mensual")
	private double importeFijoMT;

	@Column(name="C_MensualAnterior")
	private double importeFijoMTAnterior;

	@Column(name="C_MensualNM")
	private double importeFijoNM;

	@Column(name="C_MensualNMAnterior")
	private double importeFijoNMAnterior;

	@Column(name="CampoP")
	private int descuentoPlanaMT;

	@Column(name="CampoP_Anterior")
	private int descuentoPlanaMTAnterior;

	@Column(name="CampoPNM")
	private int descuentoPlanaNM;

	@Column(name="CampoPNM_Anterior")
	private int descuentoPlanaNMAnterior;

	private String cargaGAE;

	private String ccc;

	@Column(name="CCC_Anteriror")
	private String cccAnteriror;

	private String cif;

	@Column(name="cmbImporteTemporal")
	private String cambioImporteTemporal;

	private String codAPI;

	@Column(name="CodApi_Orig")
	private String codAPIOrig;

	@Column(name="CORREO_SN")
	private String envioCorreo;

	private String direccionAnterior;

	@Column(name="E_Mail")
	private String email;

	private int estadoTram;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CORREO")
	private Date fechaCorreo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGAE;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPeticion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTramAPI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTramPrevista;

	@Temporal(TemporalType.TIMESTAMP)
	private Date finVigencia;

	@Column(name="id_motivo_baja_MARTE")
	private int idMotivoBajaMarte;

	private String idAcuerdo;

	private int idTareaGAE;

	@Column(name="Mat_JArea")
	private String matJArea;

	@Column(name="MATCIAL")
	private String matComercial;

	private String matPeticionario;

	@Column(name="Motivo_Baja")
	private short motivoBaja;

	@Column(name="NomCliente")
	private String nombre;

	private String observaciones;

	private String operadora;

	@Lob
	private String peticionTramitacion;

	@Lob
	private String respuesta;

	private String soporteAnterior;

	private String territorio;

	@Column(name="Tipo_Planas")
	private String tipoPlanas;

	private String tipoDoc;

	private String tipoSoporte;

	private String trabajo;

	private String tramitar;

	private String usuarioGAE;

	public TramitacionAPI() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcuerdoNumero() {
		return this.acuerdoNumero;
	}

	public void setAcuerdoNumero(String acuerdoNumero) {
		this.acuerdoNumero = acuerdoNumero;
	}

	public String getPlanaAutoajustable() {
		return this.planaAutoajustable;
	}

	public void setPlanaAutoajustable(String planaAutoajustable) {
		this.planaAutoajustable = planaAutoajustable;
	}

	public String getAutorizacion() {
		return this.autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getBajaCliente() {
		return this.bajaCliente;
	}

	public void setBajaCliente(String bajaCliente) {
		this.bajaCliente = bajaCliente;
	}

	public double getImporteFijoMT() {
		return this.importeFijoMT;
	}

	public void setImporteFijoMT(double importeFijoMT) {
		this.importeFijoMT = importeFijoMT;
	}

	public double getImporteFijoMTAnterior() {
		return this.importeFijoMTAnterior;
	}

	public void setImporteFijoMTAnterior(double importeFijoMTAnterior) {
		this.importeFijoMTAnterior = importeFijoMTAnterior;
	}

	public double getImporteFijoNM() {
		return this.importeFijoNM;
	}

	public void setImporteFijoNM(double importeFijoNM) {
		this.importeFijoNM = importeFijoNM;
	}

	public double getImporteFijoNMAnterior() {
		return this.importeFijoNMAnterior;
	}

	public void setImporteFijoNMAnterior(double importeFijoNMAnterior) {
		this.importeFijoNMAnterior = importeFijoNMAnterior;
	}

	public int getDescuentoPlanaMT() {
		return this.descuentoPlanaMT;
	}

	public void setDescuentoPlanaMT(int descuentoPlanaMT) {
		this.descuentoPlanaMT = descuentoPlanaMT;
	}

	public int getDescuentoPlanaMTAnterior() {
		return this.descuentoPlanaMTAnterior;
	}

	public void setDescuentoPlanaMTAnterior(int descuentoPlanaMTAnterior) {
		this.descuentoPlanaMTAnterior = descuentoPlanaMTAnterior;
	}

	public int getDescuentoPlanaNM() {
		return this.descuentoPlanaNM;
	}

	public void setDescuentoPlanaNM(int descuentoPlanaNM) {
		this.descuentoPlanaNM = descuentoPlanaNM;
	}

	public int getDescuentoPlanaNMAnterior() {
		return this.descuentoPlanaNMAnterior;
	}

	public void setDescuentoPlanaNMAnterior(int descuentoPlanaNMAnterior) {
		this.descuentoPlanaNMAnterior = descuentoPlanaNMAnterior;
	}

	public String getCargaGAE() {
		return this.cargaGAE;
	}

	public void setCargaGAE(String cargaGAE) {
		this.cargaGAE = cargaGAE;
	}

	public String getCcc() {
		return this.ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	public String getCccAnteriror() {
		return this.cccAnteriror;
	}

	public void setCccAnteriror(String cccAnteriror) {
		this.cccAnteriror = cccAnteriror;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getCambioImporteTemporal() {
		return this.cambioImporteTemporal;
	}

	public void setCambioImporteTemporal(String cambioImporteTemporal) {
		this.cambioImporteTemporal = cambioImporteTemporal;
	}

	public String getCodAPI() {
		return this.codAPI;
	}

	public void setCodAPI(String codAPI) {
		this.codAPI = codAPI;
	}

	public String getCodAPIOrig() {
		return this.codAPIOrig;
	}

	public void setCodAPIOrig(String codAPIOrig) {
		this.codAPIOrig = codAPIOrig;
	}

	public String getEnvioCorreo() {
		return this.envioCorreo;
	}

	public void setEnvioCorreo(String envioCorreo) {
		this.envioCorreo = envioCorreo;
	}

	public String getDireccionAnterior() {
		return this.direccionAnterior;
	}

	public void setDireccionAnterior(String direccionAnterior) {
		this.direccionAnterior = direccionAnterior;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEstadoTram() {
		return this.estadoTram;
	}

	public void setEstadoTram(int estadoTram) {
		this.estadoTram = estadoTram;
	}

	public Date getFechaCorreo() {
		return this.fechaCorreo;
	}

	public void setFechaCorreo(Date fechaCorreo) {
		this.fechaCorreo = fechaCorreo;
	}

	public Date getFechaGAE() {
		return this.fechaGAE;
	}

	public void setFechaGAE(Date fechaGAE) {
		this.fechaGAE = fechaGAE;
	}

	public Date getFechaPeticion() {
		return this.fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public Date getFechaTramAPI() {
		return this.fechaTramAPI;
	}

	public void setFechaTramAPI(Date fechaTramAPI) {
		this.fechaTramAPI = fechaTramAPI;
	}

	public Date getFechaTramPrevista() {
		return this.fechaTramPrevista;
	}

	public void setFechaTramPrevista(Date fechaTramPrevista) {
		this.fechaTramPrevista = fechaTramPrevista;
	}

	public Date getFinVigencia() {
		return this.finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}

	public int getIdMotivoBajaMarte() {
		return this.idMotivoBajaMarte;
	}

	public void setIdMotivoBajaMarte(int idMotivoBajaMarte) {
		this.idMotivoBajaMarte = idMotivoBajaMarte;
	}

	public String getIdAcuerdo() {
		return this.idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public int getIdTareaGAE() {
		return this.idTareaGAE;
	}

	public void setIdTareaGAE(int idTareaGAE) {
		this.idTareaGAE = idTareaGAE;
	}

	public String getMatJArea() {
		return this.matJArea;
	}

	public void setMatJArea(String matJArea) {
		this.matJArea = matJArea;
	}

	public String getMatComercial() {
		return this.matComercial;
	}

	public void setMatComercial(String matComercial) {
		this.matComercial = matComercial;
	}

	public String getMatPeticionario() {
		return this.matPeticionario;
	}

	public void setMatPeticionario(String matPeticionario) {
		this.matPeticionario = matPeticionario;
	}

	public short getMotivoBaja() {
		return this.motivoBaja;
	}

	public void setMotivoBaja(short motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getOperadora() {
		return this.operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getPeticionTramitacion() {
		return this.peticionTramitacion;
	}

	public void setPeticionTramitacion(String peticionTramitacion) {
		this.peticionTramitacion = peticionTramitacion;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getSoporteAnterior() {
		return this.soporteAnterior;
	}

	public void setSoporteAnterior(String soporteAnterior) {
		this.soporteAnterior = soporteAnterior;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getTipoPlanas() {
		return this.tipoPlanas;
	}

	public void setTipoPlanas(String tipoPlanas) {
		this.tipoPlanas = tipoPlanas;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTipoSoporte() {
		return this.tipoSoporte;
	}

	public void setTipoSoporte(String tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
	}

	public String getTrabajo() {
		return this.trabajo;
	}

	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}

	public String getTramitar() {
		return this.tramitar;
	}

	public void setTramitar(String tramitar) {
		this.tramitar = tramitar;
	}

	public String getUsuarioGAE() {
		return this.usuarioGAE;
	}

	public void setUsuarioGAE(String usuarioGAE) {
		this.usuarioGAE = usuarioGAE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acuerdoNumero == null) ? 0 : acuerdoNumero.hashCode());
		result = prime * result
				+ ((autorizacion == null) ? 0 : autorizacion.hashCode());
		result = prime * result
				+ ((bajaCliente == null) ? 0 : bajaCliente.hashCode());
		result = prime
				* result
				+ ((cambioImporteTemporal == null) ? 0 : cambioImporteTemporal
						.hashCode());
		result = prime * result
				+ ((cargaGAE == null) ? 0 : cargaGAE.hashCode());
		result = prime * result + ((ccc == null) ? 0 : ccc.hashCode());
		result = prime * result
				+ ((cccAnteriror == null) ? 0 : cccAnteriror.hashCode());
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((codAPI == null) ? 0 : codAPI.hashCode());
		result = prime * result
				+ ((codAPIOrig == null) ? 0 : codAPIOrig.hashCode());
		result = prime * result + descuentoPlanaMT;
		result = prime * result + descuentoPlanaMTAnterior;
		result = prime * result + descuentoPlanaNM;
		result = prime * result + descuentoPlanaNMAnterior;
		result = prime
				* result
				+ ((direccionAnterior == null) ? 0 : direccionAnterior
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((envioCorreo == null) ? 0 : envioCorreo.hashCode());
		result = prime * result + estadoTram;
		result = prime * result
				+ ((fechaCorreo == null) ? 0 : fechaCorreo.hashCode());
		result = prime * result
				+ ((fechaGAE == null) ? 0 : fechaGAE.hashCode());
		result = prime * result
				+ ((fechaPeticion == null) ? 0 : fechaPeticion.hashCode());
		result = prime * result
				+ ((fechaTramAPI == null) ? 0 : fechaTramAPI.hashCode());
		result = prime
				* result
				+ ((fechaTramPrevista == null) ? 0 : fechaTramPrevista
						.hashCode());
		result = prime * result
				+ ((finVigencia == null) ? 0 : finVigencia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idAcuerdo == null) ? 0 : idAcuerdo.hashCode());
		result = prime * result + idMotivoBajaMarte;
		result = prime * result + idTareaGAE;
		long temp;
		temp = Double.doubleToLongBits(importeFijoMT);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(importeFijoMTAnterior);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(importeFijoNM);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(importeFijoNMAnterior);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((matComercial == null) ? 0 : matComercial.hashCode());
		result = prime * result
				+ ((matJArea == null) ? 0 : matJArea.hashCode());
		result = prime * result
				+ ((matPeticionario == null) ? 0 : matPeticionario.hashCode());
		result = prime * result + motivoBaja;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((observaciones == null) ? 0 : observaciones.hashCode());
		result = prime * result
				+ ((operadora == null) ? 0 : operadora.hashCode());
		result = prime
				* result
				+ ((peticionTramitacion == null) ? 0 : peticionTramitacion
						.hashCode());
		result = prime
				* result
				+ ((planaAutoajustable == null) ? 0 : planaAutoajustable
						.hashCode());
		result = prime * result
				+ ((respuesta == null) ? 0 : respuesta.hashCode());
		result = prime * result
				+ ((soporteAnterior == null) ? 0 : soporteAnterior.hashCode());
		result = prime * result
				+ ((territorio == null) ? 0 : territorio.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
		result = prime * result
				+ ((tipoPlanas == null) ? 0 : tipoPlanas.hashCode());
		result = prime * result
				+ ((tipoSoporte == null) ? 0 : tipoSoporte.hashCode());
		result = prime * result + ((trabajo == null) ? 0 : trabajo.hashCode());
		result = prime * result
				+ ((tramitar == null) ? 0 : tramitar.hashCode());
		result = prime * result
				+ ((usuarioGAE == null) ? 0 : usuarioGAE.hashCode());
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
		TramitacionAPI other = (TramitacionAPI) obj;
		if (acuerdoNumero == null) {
			if (other.acuerdoNumero != null)
				return false;
		} else if (!acuerdoNumero.equals(other.acuerdoNumero))
			return false;
		if (autorizacion == null) {
			if (other.autorizacion != null)
				return false;
		} else if (!autorizacion.equals(other.autorizacion))
			return false;
		if (bajaCliente == null) {
			if (other.bajaCliente != null)
				return false;
		} else if (!bajaCliente.equals(other.bajaCliente))
			return false;
		if (cambioImporteTemporal == null) {
			if (other.cambioImporteTemporal != null)
				return false;
		} else if (!cambioImporteTemporal.equals(other.cambioImporteTemporal))
			return false;
		if (cargaGAE == null) {
			if (other.cargaGAE != null)
				return false;
		} else if (!cargaGAE.equals(other.cargaGAE))
			return false;
		if (ccc == null) {
			if (other.ccc != null)
				return false;
		} else if (!ccc.equals(other.ccc))
			return false;
		if (cccAnteriror == null) {
			if (other.cccAnteriror != null)
				return false;
		} else if (!cccAnteriror.equals(other.cccAnteriror))
			return false;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (codAPI == null) {
			if (other.codAPI != null)
				return false;
		} else if (!codAPI.equals(other.codAPI))
			return false;
		if (codAPIOrig == null) {
			if (other.codAPIOrig != null)
				return false;
		} else if (!codAPIOrig.equals(other.codAPIOrig))
			return false;
		if (descuentoPlanaMT != other.descuentoPlanaMT)
			return false;
		if (descuentoPlanaMTAnterior != other.descuentoPlanaMTAnterior)
			return false;
		if (descuentoPlanaNM != other.descuentoPlanaNM)
			return false;
		if (descuentoPlanaNMAnterior != other.descuentoPlanaNMAnterior)
			return false;
		if (direccionAnterior == null) {
			if (other.direccionAnterior != null)
				return false;
		} else if (!direccionAnterior.equals(other.direccionAnterior))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (envioCorreo == null) {
			if (other.envioCorreo != null)
				return false;
		} else if (!envioCorreo.equals(other.envioCorreo))
			return false;
		if (estadoTram != other.estadoTram)
			return false;
		if (fechaCorreo == null) {
			if (other.fechaCorreo != null)
				return false;
		} else if (!fechaCorreo.equals(other.fechaCorreo))
			return false;
		if (fechaGAE == null) {
			if (other.fechaGAE != null)
				return false;
		} else if (!fechaGAE.equals(other.fechaGAE))
			return false;
		if (fechaPeticion == null) {
			if (other.fechaPeticion != null)
				return false;
		} else if (!fechaPeticion.equals(other.fechaPeticion))
			return false;
		if (fechaTramAPI == null) {
			if (other.fechaTramAPI != null)
				return false;
		} else if (!fechaTramAPI.equals(other.fechaTramAPI))
			return false;
		if (fechaTramPrevista == null) {
			if (other.fechaTramPrevista != null)
				return false;
		} else if (!fechaTramPrevista.equals(other.fechaTramPrevista))
			return false;
		if (finVigencia == null) {
			if (other.finVigencia != null)
				return false;
		} else if (!finVigencia.equals(other.finVigencia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idAcuerdo == null) {
			if (other.idAcuerdo != null)
				return false;
		} else if (!idAcuerdo.equals(other.idAcuerdo))
			return false;
		if (idMotivoBajaMarte != other.idMotivoBajaMarte)
			return false;
		if (idTareaGAE != other.idTareaGAE)
			return false;
		if (Double.doubleToLongBits(importeFijoMT) != Double
				.doubleToLongBits(other.importeFijoMT))
			return false;
		if (Double.doubleToLongBits(importeFijoMTAnterior) != Double
				.doubleToLongBits(other.importeFijoMTAnterior))
			return false;
		if (Double.doubleToLongBits(importeFijoNM) != Double
				.doubleToLongBits(other.importeFijoNM))
			return false;
		if (Double.doubleToLongBits(importeFijoNMAnterior) != Double
				.doubleToLongBits(other.importeFijoNMAnterior))
			return false;
		if (matComercial == null) {
			if (other.matComercial != null)
				return false;
		} else if (!matComercial.equals(other.matComercial))
			return false;
		if (matJArea == null) {
			if (other.matJArea != null)
				return false;
		} else if (!matJArea.equals(other.matJArea))
			return false;
		if (matPeticionario == null) {
			if (other.matPeticionario != null)
				return false;
		} else if (!matPeticionario.equals(other.matPeticionario))
			return false;
		if (motivoBaja != other.motivoBaja)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		if (operadora == null) {
			if (other.operadora != null)
				return false;
		} else if (!operadora.equals(other.operadora))
			return false;
		if (peticionTramitacion == null) {
			if (other.peticionTramitacion != null)
				return false;
		} else if (!peticionTramitacion.equals(other.peticionTramitacion))
			return false;
		if (planaAutoajustable == null) {
			if (other.planaAutoajustable != null)
				return false;
		} else if (!planaAutoajustable.equals(other.planaAutoajustable))
			return false;
		if (respuesta == null) {
			if (other.respuesta != null)
				return false;
		} else if (!respuesta.equals(other.respuesta))
			return false;
		if (soporteAnterior == null) {
			if (other.soporteAnterior != null)
				return false;
		} else if (!soporteAnterior.equals(other.soporteAnterior))
			return false;
		if (territorio == null) {
			if (other.territorio != null)
				return false;
		} else if (!territorio.equals(other.territorio))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		if (tipoPlanas == null) {
			if (other.tipoPlanas != null)
				return false;
		} else if (!tipoPlanas.equals(other.tipoPlanas))
			return false;
		if (tipoSoporte == null) {
			if (other.tipoSoporte != null)
				return false;
		} else if (!tipoSoporte.equals(other.tipoSoporte))
			return false;
		if (trabajo == null) {
			if (other.trabajo != null)
				return false;
		} else if (!trabajo.equals(other.trabajo))
			return false;
		if (tramitar == null) {
			if (other.tramitar != null)
				return false;
		} else if (!tramitar.equals(other.tramitar))
			return false;
		if (usuarioGAE == null) {
			if (other.usuarioGAE != null)
				return false;
		} else if (!usuarioGAE.equals(other.usuarioGAE))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionAPI [id=");
		builder.append(id);
		builder.append(", acuerdoNumero=");
		builder.append(acuerdoNumero);
		builder.append(", planaAutoajustable=");
		builder.append(planaAutoajustable);
		builder.append(", autorizacion=");
		builder.append(autorizacion);
		builder.append(", bajaCliente=");
		builder.append(bajaCliente);
		builder.append(", importeFijoMT=");
		builder.append(importeFijoMT);
		builder.append(", importeFijoMTAnterior=");
		builder.append(importeFijoMTAnterior);
		builder.append(", importeFijoNM=");
		builder.append(importeFijoNM);
		builder.append(", importeFijoNMAnterior=");
		builder.append(importeFijoNMAnterior);
		builder.append(", descuentoPlanaMT=");
		builder.append(descuentoPlanaMT);
		builder.append(", descuentoPlanaMTAnterior=");
		builder.append(descuentoPlanaMTAnterior);
		builder.append(", descuentoPlanaNM=");
		builder.append(descuentoPlanaNM);
		builder.append(", descuentoPlanaNMAnterior=");
		builder.append(descuentoPlanaNMAnterior);
		builder.append(", cargaGAE=");
		builder.append(cargaGAE);
		builder.append(", ccc=");
		builder.append(ccc);
		builder.append(", cccAnteriror=");
		builder.append(cccAnteriror);
		builder.append(", cif=");
		builder.append(cif);
		builder.append(", cambioImporteTemporal=");
		builder.append(cambioImporteTemporal);
		builder.append(", codAPI=");
		builder.append(codAPI);
		builder.append(", codAPIOrig=");
		builder.append(codAPIOrig);
		builder.append(", envioCorreo=");
		builder.append(envioCorreo);
		builder.append(", direccionAnterior=");
		builder.append(direccionAnterior);
		builder.append(", email=");
		builder.append(email);
		builder.append(", estadoTram=");
		builder.append(estadoTram);
		builder.append(", fechaPeticion=");
		builder.append(sdf.format(fechaPeticion));
		builder.append(", fechaTramPrevista=");
		builder.append(sdf.format(fechaTramPrevista));
		builder.append(", fechaTramAPI=");
		builder.append(sdf.format(fechaTramAPI));
		builder.append(", fechaGAE=");
		builder.append(sdf.format(fechaGAE));
		builder.append(", fechaCorreo=");
		builder.append(sdf.format(fechaCorreo));
		builder.append(", finVigencia=");
		builder.append(sdf.format(finVigencia));
		builder.append(", idAcuerdo=");
		builder.append(idAcuerdo);
		builder.append(", idTareaGAE=");
		builder.append(idTareaGAE);
		builder.append(", matJArea=");
		builder.append(matJArea);
		builder.append(", matComercial=");
		builder.append(matComercial);
		builder.append(", matPeticionario=");
		builder.append(matPeticionario);
		builder.append(", motivoBaja=");
		builder.append(motivoBaja);
		builder.append(", idMotivoBajaMarte=");
		builder.append(idMotivoBajaMarte);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", observaciones=");
		builder.append(observaciones);
		builder.append(", operadora=");
		builder.append(operadora);
		builder.append(", peticionTramitacion=");
		builder.append(peticionTramitacion);
		builder.append(", respuesta=");
		builder.append(respuesta);
		builder.append(", soporteAnterior=");
		builder.append(soporteAnterior);
		builder.append(", territorio=");
		builder.append(territorio);
		builder.append(", tipoPlanas=");
		builder.append(tipoPlanas);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", tipoSoporte=");
		builder.append(tipoSoporte);
		builder.append(", trabajo=");
		builder.append(trabajo);
		builder.append(", tramitar=");
		builder.append(tramitar);
		builder.append(", usuarioGAE=");
		builder.append(usuarioGAE);
		builder.append("]");
		return builder.toString();
	}
	
	

}