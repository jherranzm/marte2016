package telefonica.aaee.marte.form;

import java.io.Serializable;
import java.util.Calendar;

import telefonica.aaee.marte.helpers.CalculoFechas;

public class TramitacionBajaForm extends TramitacionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long motivoBajaMARTE;
	private String nuevocif;
	private String horus;
	private String bonificacion;
	
	
	public TramitacionBajaForm() {
		super();
		this.setIdAcuerdo("");
		this.nuevocif = "";
		this.horus = "";
		this.setPeticionTramitacion("");
		this.motivoBajaMARTE = null;
		java.util.Date ahora = Calendar.getInstance().getTime();
		this.setFechaTramPrevista(CalculoFechas.primerDiaHabil(ahora, false));
		this.setFechaPeticion(ahora);
		this.setBonificacion("NO");
	}


	public Long getMotivoBajaMARTE() {
		return motivoBajaMARTE;
	}


	public void setMotivoBajaMARTE(Long motivoBajaMARTE) {
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




	public String getBonificacion() {
		return bonificacion;
	}


	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionBajaForm [idAcuerdo=").append(getIdAcuerdo())
				.append(", motivoBajaMARTE=").append(motivoBajaMARTE)
				.append(", nuevocif=").append(nuevocif).append(", horus=")
				.append(horus).append(", peticionTramitacion=")
				.append(getPeticionTramitacion()).append(", fechaTramPrevista=")
				.append(getFechaTramPrevista()).append(", fechaPeticion=")
				.append(getFechaPeticion()).append(", bonificacion=")
				.append(bonificacion).append("]");
		return builder.toString();
	}





	

}
