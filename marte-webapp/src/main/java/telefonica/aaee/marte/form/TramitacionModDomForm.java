package telefonica.aaee.marte.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import telefonica.aaee.marte.mofa.dao.model.Municipio;

public class TramitacionModDomForm extends TramitacionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DireccionVO domsoc;
	private DireccionVO domfac;
	
	private List<Municipio> municipiosSoc = new ArrayList<Municipio>();
	private List<Municipio> municipiosFac = new ArrayList<Municipio>();
	
	public TramitacionModDomForm() {
		super();
	}

	public DireccionVO getDomsoc() {
		return domsoc;
	}

	public void setDomsoc(DireccionVO domsoc) {
		this.domsoc = domsoc;
	}

	public DireccionVO getDomfac() {
		return domfac;
	}

	public void setDomfac(DireccionVO domfac) {
		this.domfac = domfac;
	}

	public List<Municipio> getMunicipiosSoc() {
		return municipiosSoc;
	}

	public void setMunicipiosSoc(List<Municipio> municipiosSoc) {
		this.municipiosSoc = municipiosSoc;
	}

	public List<Municipio> getMunicipiosFac() {
		return municipiosFac;
	}

	public void setMunicipiosFac(List<Municipio> municipiosFac) {
		this.municipiosFac = municipiosFac;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TramitacionModDomForm [domsoc=");
		builder.append(domsoc);
		builder.append(", domfac=");
		builder.append(domfac);
		builder.append(", municipiosSoc=");
		builder.append(municipiosSoc);
		builder.append(", municipiosFac=");
		builder.append(municipiosFac);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
