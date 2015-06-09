package telefonica.aaee.marte.form;

import java.util.Calendar;
import java.util.Date;

import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.helpers.CalculoFechas;

public class TramitacionBajaForm {
	
	private String idAcuerdo;
	private MotivoBaja motivoBajaMARTE;
	private String nuevocif;
	private String horus;
	private String peticionTramitacion;
	private Date fechaTramPrevista;
	private Date fechaPeticion;
	private String bonificacion;
	
	
	public TramitacionBajaForm() {
		super();
		this.idAcuerdo = "";
		this.nuevocif = "";
		this.horus = "";
		this.peticionTramitacion = "";
		this.motivoBajaMARTE = null;
		java.util.Date ahora = Calendar.getInstance().getTime();
		this.fechaTramPrevista = CalculoFechas.primerDiaHabil(ahora, false);
		this.setFechaPeticion(ahora);
		this.setBonificacion("NO");
	}


	public String getIdAcuerdo() {
		return idAcuerdo;
	}


	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}


	public MotivoBaja getMotivoBajaMARTE() {
		return motivoBajaMARTE;
	}


	public void setMotivoBajaMARTE(MotivoBaja motivoBajaMARTE) {
		this.motivoBajaMARTE = motivoBajaMARTE;
	}


	public String getNuevocif() {
		return nuevocif;
	}


	public void setNuevocif(String nuevocif) {
		this.nuevocif = nuevocif;
	}


	public String getHorus() {
		return horus;
	}


	public void setHorus(String horus) {
		this.horus = horus;
	}


	public String getPeticionTramitacion() {
		return peticionTramitacion;
	}


	public void setPeticionTramitacion(String peticionTramitacion) {
		this.peticionTramitacion = peticionTramitacion;
	}


	public Date getFechaTramPrevista() {
		return fechaTramPrevista;
	}


	public void setFechaTramPrevista(Date fechaTramPrevista) {
		this.fechaTramPrevista = fechaTramPrevista;
	}


	public Date getFechaPeticion() {
		return fechaPeticion;
	}


	public void setFechaPeticion(Date fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}


	public String getBonificacion() {
		return bonificacion;
	}


	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionBajaForm [idAcuerdo=").append(idAcuerdo)
				.append(", motivoBajaMARTE=").append(motivoBajaMARTE)
				.append(", nuevocif=").append(nuevocif).append(", horus=")
				.append(horus).append(", peticionTramitacion=")
				.append(peticionTramitacion).append(", fechaTramPrevista=")
				.append(fechaTramPrevista).append(", fechaPeticion=")
				.append(fechaPeticion).append(", bonificacion=")
				.append(bonificacion).append("]");
		return builder.toString();
	}





	

}
