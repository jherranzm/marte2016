package telefonica.aaee.marte.form;

import java.util.Calendar;
import java.util.Date;

import telefonica.aaee.marte.helpers.CalculoFechas;

public class TramitacionForm {

	private String idAcuerdo;
	private String peticionTramitacion;
	private Date fechaTramPrevista;
	private Date fechaPeticion;

	public TramitacionForm() {
		super();
		this.setIdAcuerdo("");
		this.setPeticionTramitacion("");
		java.util.Date ahora = Calendar.getInstance().getTime();
		this.setFechaTramPrevista(CalculoFechas.primerDiaHabil(ahora, false));
		this.setFechaPeticion(ahora);
	}

	public Date getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public Date getFechaTramPrevista() {
		return fechaTramPrevista;
	}

	public void setFechaTramPrevista(Date fechaTramPrevista) {
		this.fechaTramPrevista = fechaTramPrevista;
	}

	public String getPeticionTramitacion() {
		return peticionTramitacion;
	}

	public void setPeticionTramitacion(String peticionTramitacion) {
		this.peticionTramitacion = peticionTramitacion;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	
}