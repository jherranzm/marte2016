package telefonica.aaee.marte.form;

import java.io.Serializable;

public class TramitacionModTipoSoporteForm extends TramitacionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tipoSoporte;
	private String email;

	public TramitacionModTipoSoporteForm() {
		super();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoSoporte() {
		return tipoSoporte;
	}

	public void setTipoSoporte(String tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionModTipoSoporteForm [tipoSoporte=");
		builder.append(tipoSoporte);
		builder.append(", email=");
		builder.append(email);
		builder.append(", getIdAcuerdo()=");
		builder.append(getIdAcuerdo());
		builder.append(", getFechaPeticion()=");
		builder.append(getFechaPeticion());
		builder.append(", getFechaTramPrevista()=");
		builder.append(getFechaTramPrevista());
		builder.append(", getPeticionTramitacion()=");
		builder.append(getPeticionTramitacion());
		builder.append("]");
		return builder.toString();
	}


	
	

}
