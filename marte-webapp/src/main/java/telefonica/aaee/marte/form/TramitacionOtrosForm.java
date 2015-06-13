package telefonica.aaee.marte.form;

import java.io.Serializable;

public class TramitacionOtrosForm extends TramitacionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public TramitacionOtrosForm() {
		super();
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionOtrosForm [idAcuerdo=");
		builder.append(getIdAcuerdo());
		builder.append(", fechaPeticion=");
		builder.append(getFechaPeticion());
		builder.append(", fechaTramPrevista=");
		builder.append(getFechaTramPrevista());
		builder.append(", peticionTramitacion=");
		builder.append(getPeticionTramitacion());
		builder.append("]");
		return builder.toString();
	}

}
