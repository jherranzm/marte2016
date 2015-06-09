package telefonica.aaee.marte.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.acuerdos.dao.service.MotivoBajaService;

@Component
public class MotivoBajaEditor  extends PropertyEditorSupport{

	protected final Log logger = LogFactory.getLog(getClass());

	private MotivoBajaService MotivoBajaService;
	
	public MotivoBajaEditor(){
	}
	
	public MotivoBajaEditor(MotivoBajaService MotivoBajaService){
		this.MotivoBajaService = MotivoBajaService;
	};

	
	
	// Converts a String to a Category (when submitting form)
    @Override
    public void setAsText(String text) {
    	logger.info("Llega:" + text);
    	long key = new Long(text);
    	logger.info("key:" + text);
    	if(MotivoBajaService == null){
    		logger.error("\n\nMal vamos...!");
    	}else{
	        MotivoBaja c = MotivoBajaService.findById(key);
	        logger.info("MotivoBaja:" + c.toString());
	        this.setValue(c);
    	}
    }

    // Converts a Category to a String (when displaying form)
    @Override
    public String getAsText() {
    	MotivoBaja c = (MotivoBaja) this.getValue();
        return (new Long(c.getIdMotivoBajaMARTE())).toString();
    }

}
