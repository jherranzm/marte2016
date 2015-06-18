package telefonica.aaee.marte.mofa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the apuntes database table.
 * 
 */
@Entity
@Table(name="apuntes")
@NamedQuery(name="Apunte.findAll", query="SELECT a FROM Apunte a")
public class Apunte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FACTURA")
	private String factura;

	@Column(name="AnexoFactura")
	private String anexoFactura;

	@Column(name="ANULADO")
	private String anulado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AnulaDocGeico")
	private Date anulaDocGeico;

	@Column(name="Autorizacion")
	private String autorizacion;

	private String banco;

	@Column(name="BI")
	private double bi;

	@Column(name="CLIENTE")
	private String cliente;

	private String contacto;

	@Column(name="Cuenta")
	private String cuenta;

	@Column(name="DC")
	private String dc;

	@Column(name="Denegado")
	private String denegado;

	@Temporal(TemporalType.DATE)
	@Column(name="Desde")
	private Date desde;

	@Column(name="Domicilio")
	private String domicilio;

	@Column(name="DP")
	private String dp;

	private short envio;

	@Column(name="Estado")
	private String estado;

	@Column(name="ExentaImpuesto")
	private String exentaImpuesto;

	@Column(name="Extraer")
	private String extraer;

	@Column(name="Facturasrr")
	private String facturasrr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA")
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FechaEnvioSD")
	private Date fechaEnvioSD;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechafichero;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechafirma;

	@Column(name="FechaGeneracionFas3")
	private int fechaGeneracionFas3;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FechaInicioVisa")
	private Date fechaInicioVisa;

	private String fichero;

	@Column(name="FicheroCargaMasiva")
	private String ficheroCargaMasiva;

	@Column(name="FirmaElectronica")
	private String firmaElectronica;

	@Temporal(TemporalType.DATE)
	@Column(name="Hasta")
	private Date hasta;

	@Column(name="Horus")
	private String horus;

	private String idAcuerdo;

	private int idCarpeta;

	@Column(name="Idioma")
	private String idioma;

	private int IDTarea;

	@Column(name="IMPORTE")
	private double importe;

	@Column(name="ImporteFas3")
	private double importeFas3;

	@Column(name="Impuestos")
	private double impuestos;

	@Lob
	private String informe;

	@Lob
	@Column(name="LogFirmaElectronica")
	private String logFirmaElectronica;

	@Column(name="Moneda")
	private String moneda;

	@Column(name="Neto")
	private double neto;

	@Column(name="NIF")
	private String nif;

	private String numAcuerdo;

	@Lob
	private String observaciones;

	@Column(name="OrigenCarga")
	private String origenCarga;

	@Column(name="Pais")
	private String pais;

	@Column(name="PeriodoFas3")
	private String periodoFas3;

	@Column(name="PieFactura")
	private String pieFactura;

	@Column(name="Poblacion")
	private String poblacion;

	@Column(name="PropuestaFas3")
	private String propuestaFas3;

	@Column(name="Provincia")
	private String provincia;

	private String sistemacobro;

	private String sucursal;

	@Column(name="Supraacuerdo")
	private String supraacuerdo;

	@Column(name="Territorio")
	private String territorio;

	@Column(name="TIPO")
	private String tipo;

	@Column(name="TipoDocumento")
	private String tipoDocumento;

	@Column(name="TipoImpositivo")
	private String tipoImpositivo;

	@Column(name="USUARIO")
	private String usuario;

	private String VBCoordinador;

	private String VBDirector;

	private String VBDirectorGeneral;

	private String VBGerente;

	private String VBJefe;

	public Apunte() {
	}

	public String getFactura() {
		return this.factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getAnexoFactura() {
		return this.anexoFactura;
	}

	public void setAnexoFactura(String anexoFactura) {
		this.anexoFactura = anexoFactura;
	}

	public String getAnulado() {
		return this.anulado;
	}

	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}

	public Date getAnulaDocGeico() {
		return this.anulaDocGeico;
	}

	public void setAnulaDocGeico(Date anulaDocGeico) {
		this.anulaDocGeico = anulaDocGeico;
	}

	public String getAutorizacion() {
		return this.autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public double getBi() {
		return this.bi;
	}

	public void setBi(double bi) {
		this.bi = bi;
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
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

	public String getDenegado() {
		return this.denegado;
	}

	public void setDenegado(String denegado) {
		this.denegado = denegado;
	}

	public Date getDesde() {
		return this.desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
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

	public short getEnvio() {
		return this.envio;
	}

	public void setEnvio(short envio) {
		this.envio = envio;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getExentaImpuesto() {
		return this.exentaImpuesto;
	}

	public void setExentaImpuesto(String exentaImpuesto) {
		this.exentaImpuesto = exentaImpuesto;
	}

	public String getExtraer() {
		return this.extraer;
	}

	public void setExtraer(String extraer) {
		this.extraer = extraer;
	}

	public String getFacturasrr() {
		return this.facturasrr;
	}

	public void setFacturasrr(String facturasrr) {
		this.facturasrr = facturasrr;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaEnvioSD() {
		return this.fechaEnvioSD;
	}

	public void setFechaEnvioSD(Date fechaEnvioSD) {
		this.fechaEnvioSD = fechaEnvioSD;
	}

	public Date getFechafichero() {
		return this.fechafichero;
	}

	public void setFechafichero(Date fechafichero) {
		this.fechafichero = fechafichero;
	}

	public Date getFechafirma() {
		return this.fechafirma;
	}

	public void setFechafirma(Date fechafirma) {
		this.fechafirma = fechafirma;
	}

	public int getFechaGeneracionFas3() {
		return this.fechaGeneracionFas3;
	}

	public void setFechaGeneracionFas3(int fechaGeneracionFas3) {
		this.fechaGeneracionFas3 = fechaGeneracionFas3;
	}

	public Date getFechaInicioVisa() {
		return this.fechaInicioVisa;
	}

	public void setFechaInicioVisa(Date fechaInicioVisa) {
		this.fechaInicioVisa = fechaInicioVisa;
	}

	public String getFichero() {
		return this.fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public String getFicheroCargaMasiva() {
		return this.ficheroCargaMasiva;
	}

	public void setFicheroCargaMasiva(String ficheroCargaMasiva) {
		this.ficheroCargaMasiva = ficheroCargaMasiva;
	}

	public String getFirmaElectronica() {
		return this.firmaElectronica;
	}

	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}

	public Date getHasta() {
		return this.hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public String getHorus() {
		return this.horus;
	}

	public void setHorus(String horus) {
		this.horus = horus;
	}

	public String getIdAcuerdo() {
		return this.idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public int getIdCarpeta() {
		return this.idCarpeta;
	}

	public void setIdCarpeta(int idCarpeta) {
		this.idCarpeta = idCarpeta;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getIDTarea() {
		return this.IDTarea;
	}

	public void setIDTarea(int IDTarea) {
		this.IDTarea = IDTarea;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getImporteFas3() {
		return this.importeFas3;
	}

	public void setImporteFas3(double importeFas3) {
		this.importeFas3 = importeFas3;
	}

	public double getImpuestos() {
		return this.impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}

	public String getInforme() {
		return this.informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public String getLogFirmaElectronica() {
		return this.logFirmaElectronica;
	}

	public void setLogFirmaElectronica(String logFirmaElectronica) {
		this.logFirmaElectronica = logFirmaElectronica;
	}

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public double getNeto() {
		return this.neto;
	}

	public void setNeto(double neto) {
		this.neto = neto;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNumAcuerdo() {
		return this.numAcuerdo;
	}

	public void setNumAcuerdo(String numAcuerdo) {
		this.numAcuerdo = numAcuerdo;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getOrigenCarga() {
		return this.origenCarga;
	}

	public void setOrigenCarga(String origenCarga) {
		this.origenCarga = origenCarga;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPeriodoFas3() {
		return this.periodoFas3;
	}

	public void setPeriodoFas3(String periodoFas3) {
		this.periodoFas3 = periodoFas3;
	}

	public String getPieFactura() {
		return this.pieFactura;
	}

	public void setPieFactura(String pieFactura) {
		this.pieFactura = pieFactura;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getPropuestaFas3() {
		return this.propuestaFas3;
	}

	public void setPropuestaFas3(String propuestaFas3) {
		this.propuestaFas3 = propuestaFas3;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSistemacobro() {
		return this.sistemacobro;
	}

	public void setSistemacobro(String sistemacobro) {
		this.sistemacobro = sistemacobro;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getSupraacuerdo() {
		return this.supraacuerdo;
	}

	public void setSupraacuerdo(String supraacuerdo) {
		this.supraacuerdo = supraacuerdo;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getTipoImpositivo() {
		return this.tipoImpositivo;
	}

	public void setTipoImpositivo(String tipoImpositivo) {
		this.tipoImpositivo = tipoImpositivo;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getVBCoordinador() {
		return this.VBCoordinador;
	}

	public void setVBCoordinador(String VBCoordinador) {
		this.VBCoordinador = VBCoordinador;
	}

	public String getVBDirector() {
		return this.VBDirector;
	}

	public void setVBDirector(String VBDirector) {
		this.VBDirector = VBDirector;
	}

	public String getVBDirectorGeneral() {
		return this.VBDirectorGeneral;
	}

	public void setVBDirectorGeneral(String VBDirectorGeneral) {
		this.VBDirectorGeneral = VBDirectorGeneral;
	}

	public String getVBGerente() {
		return this.VBGerente;
	}

	public void setVBGerente(String VBGerente) {
		this.VBGerente = VBGerente;
	}

	public String getVBJefe() {
		return this.VBJefe;
	}

	public void setVBJefe(String VBJefe) {
		this.VBJefe = VBJefe;
	}

}