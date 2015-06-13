package telefonica.aaee.marte.form;

import java.io.Serializable;

public class TramitacionModEmailForm extends TramitacionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String email;

	public TramitacionModEmailForm() {
		super();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionModEmailForm [email=");
		builder.append(email);
		builder.append(", getFechaPeticion()=");
		builder.append(getFechaPeticion());
		builder.append(", getFechaTramPrevista()=");
		builder.append(getFechaTramPrevista());
		builder.append(", getPeticionTramitacion()=");
		builder.append(getPeticionTramitacion());
		builder.append(", getIdAcuerdo()=");
		builder.append(getIdAcuerdo());
		builder.append("]");
		return builder.toString();
	}

	
	

}
