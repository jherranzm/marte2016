package telefonica.aaee.marte.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the vacuerdos database table.
 * 
 */
@Entity
@Table(name="vacuerdos")
public class Acuerdo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String acuerdoNumero;

	private String baja;

	private String causaBaja;

	private String cif;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Baja")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	private Date fechaVigor;

	private String finalizado;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDAcuerdo")
	private String IDAcuerdo;

	@Column(name="Nivel_de_Atencion")
	private String nivelDeAtencion;

	@Column(name="Nom_Sector")
	private String nomSector;

	private String nombre;

	private String operadora;

	private String redVentas;

	private String sector;

	private String segmento;

	private String territorio;

	private String tipoAcuerdo;

	private String tipoDoc;

	private String usuario;

	public Acuerdo() {
	}

	public String getAcuerdoNumero() {
		return this.acuerdoNumero;
	}

	public void setAcuerdoNumero(String acuerdoNumero) {
		this.acuerdoNumero = acuerdoNumero;
	}

	public String getBaja() {
		return this.baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public String getCausaBaja() {
		return this.causaBaja;
	}

	public void setCausaBaja(String causaBaja) {
		this.causaBaja = causaBaja;
	}

	public String getCif() {
		return this.cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaVigor() {
		return this.fechaVigor;
	}

	public void setFechaVigor(Date fechaVigor) {
		this.fechaVigor = fechaVigor;
	}

	public String getFinalizado() {
		return this.finalizado;
	}

	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}

	public String getIDAcuerdo() {
		return this.IDAcuerdo;
	}

	public void setIDAcuerdo(String IDAcuerdo) {
		this.IDAcuerdo = IDAcuerdo;
	}

	public String getNivelDeAtencion() {
		return this.nivelDeAtencion;
	}

	public void setNivelDeAtencion(String nivelDeAtencion) {
		this.nivelDeAtencion = nivelDeAtencion;
	}

	public String getNomSector() {
		return this.nomSector;
	}

	public void setNomSector(String nomSector) {
		this.nomSector = nomSector;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOperadora() {
		return this.operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public String getRedVentas() {
		return this.redVentas;
	}

	public void setRedVentas(String redVentas) {
		this.redVentas = redVentas;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSegmento() {
		return this.segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getTerritorio() {
		return this.territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public String getTipoAcuerdo() {
		return this.tipoAcuerdo;
	}

	public void setTipoAcuerdo(String tipoAcuerdo) {
		this.tipoAcuerdo = tipoAcuerdo;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Acuerdo [IDAcuerdo=");
		builder.append(IDAcuerdo);
		builder.append(", tipoAcuerdo=");
		builder.append(tipoAcuerdo);
		builder.append(", tipoDoc=");
		builder.append(tipoDoc);
		builder.append(", cif=");
		builder.append(cif);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", acuerdoNumero=");
		builder.append(acuerdoNumero);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", fechaVigor=");
		builder.append(fechaVigor);
		builder.append(", baja=");
		builder.append(baja);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", fechaBaja=");
		builder.append(fechaBaja);
		builder.append(", causaBaja=");
		builder.append(causaBaja);
		builder.append(", finalizado=");
		builder.append(finalizado);
		builder.append(", operadora=");
		builder.append(operadora);
		builder.append(", territorio=");
		builder.append(territorio);
		builder.append(", segmento=");
		builder.append(segmento);
		builder.append(", nivelDeAtencion=");
		builder.append(nivelDeAtencion);
		builder.append(", sector=");
		builder.append(sector);
		builder.append(", nomSector=");
		builder.append(nomSector);
		builder.append(", redVentas=");
		builder.append(redVentas);
		builder.append("]");
		return builder.toString();
	}

}