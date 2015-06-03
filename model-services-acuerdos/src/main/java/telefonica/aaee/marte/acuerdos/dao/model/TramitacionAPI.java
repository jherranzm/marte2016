package telefonica.aaee.marte.acuerdos.dao.model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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

	//bi-directional many-to-one association to EstadoTramitacion
	// el nombre de la columna es el de la tabla local
	@ManyToOne
	@JoinColumn(name="CodAPI")
	private CodAPI codAPI;

	@Column(name="CodApi_Orig")
	private String codAPIOrig;

	@Column(name="CORREO_SN")
	private String envioCorreo;

	private String direccionAnterior;

	@Column(name="E_Mail")
	private String email;

	//bi-directional many-to-one association to EstadoTramitacion
	// el nombre de la columna es el de la tabla local
	@ManyToOne
	@JoinColumn(name="EstadoTram")
	private EstadoTramitacion marteEstadoTramitacion;

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

	//bi-directional many-to-one association to MarteMotivosBaja
	@ManyToOne
	@JoinColumn(name="id_motivo_baja_MARTE")
	private MotivoBaja motivoBajaMARTE;

	//bi-directional many-to-one association to Acuerdo
	@ManyToOne
	@JoinColumn(name="idAcuerdo")
	private Acuerdo acuerdo;

	private int idTareaGAE;

	@Column(name="Mat_JArea")
	private String matJArea;

	@Column(name="MATCIAL")
	private String matComercial;

	//bi-directional many-to-one association to Acuerdo
	@ManyToOne
	@JoinColumn(name="matPeticionario")
	private MarteUsuario matPeticionario;

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

	public MotivoBaja getMotivoBajaMARTE() {
		return motivoBajaMARTE;
	}

	public void setMotivoBajaMARTE(MotivoBaja motivoBajaMARTE) {
		this.motivoBajaMARTE = motivoBajaMARTE;
	}

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

	public CodAPI getCodAPI() {
		return this.codAPI;
	}

	public void setCodAPI(CodAPI codAPI) {
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

	public Acuerdo getAcuerdo() {
		return this.acuerdo;
	}

	public void setAcuerdo(Acuerdo acuerdo) {
		this.acuerdo = acuerdo;
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

	public MarteUsuario getMatPeticionario() {
		return this.matPeticionario;
	}

	public void setMatPeticionario(MarteUsuario matPeticionario) {
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

	public EstadoTramitacion getMarteEstadoTramitacion() {
		return this.marteEstadoTramitacion;
	}

	public void setMarteEstadoTramitacion(EstadoTramitacion marteEstadoTramitacion) {
		this.marteEstadoTramitacion = marteEstadoTramitacion;
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
		builder.append(marteEstadoTramitacion);
		builder.append(", fechaPeticion=");
		builder.append(fechaPeticion == null ? "" : sdf.format(fechaPeticion));
		builder.append(", fechaTramPrevista=");
		builder.append(fechaTramPrevista == null ? "" : sdf.format(fechaTramPrevista));
		builder.append(", fechaTramAPI=");
		builder.append(fechaTramAPI == null ? "" : sdf.format(fechaTramAPI));
		builder.append(", fechaGAE=");
		builder.append(fechaGAE == null ? "" : sdf.format(fechaGAE));
		builder.append(", fechaCorreo=");
		builder.append(fechaCorreo == null ? "" : sdf.format(fechaCorreo));
		builder.append(", finVigencia=");
		builder.append(finVigencia == null ? "" : sdf.format(finVigencia));
		builder.append(", Acuerdo=");
		builder.append(acuerdo);
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
		builder.append(", motivoBajaMARTE=");
		builder.append(motivoBajaMARTE);
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