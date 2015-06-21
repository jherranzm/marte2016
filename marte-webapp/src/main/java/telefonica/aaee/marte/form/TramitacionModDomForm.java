package telefonica.aaee.marte.form;

import java.io.Serializable;

public class TramitacionModDomForm extends TramitacionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DireccionVO domsoc;
	private DireccionVO domfac;
	
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

	
	
	
}
