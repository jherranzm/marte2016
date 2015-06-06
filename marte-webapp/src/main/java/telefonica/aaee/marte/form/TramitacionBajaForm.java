package telefonica.aaee.marte.form;

public class TramitacionBajaForm {
	
	private String idAcuerdo;
	private long motivoBajaMARTE;
	private String nuevocif;
	private String horus;
	private String peticionTramitacion;
	
	
	public TramitacionBajaForm() {
		super();
	}


	public String getIdAcuerdo() {
		return idAcuerdo;
	}


	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}


	public long getMotivoBajaMARTE() {
		return motivoBajaMARTE;
	}


	public void setMotivoBajaMARTE(long motivoBajaMARTE) {
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


	@Override
	public String toString() {
		return "TramitacionBajaForm [idAcuerdo=" + idAcuerdo
				+ ", motivoBajaMARTE=" + motivoBajaMARTE + ", nuevocif="
				+ nuevocif + ", horus=" + horus + ", peticionTramitacion="
				+ peticionTramitacion + "]";
	}
	
	
	

}
