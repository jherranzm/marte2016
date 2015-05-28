package telefonica.aaee.informes.model;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import telefonica.aaee.dao.maestras.model.Role;
import telefonica.aaee.dao.maestras.service.RoleService;

@Component
public class RoleEditor extends PropertyEditorSupport{

	protected final Log logger = LogFactory.getLog(getClass());

	private RoleService service;
	
	public RoleEditor(){
	}
	
	public RoleEditor(RoleService svc){
		this.service = svc;
	};

	
	
	// Converts a String to a Category (when submitting form)
    @Override
    public void setAsText(String text) {
    	logger.info("Llega:" + text);
    	long key = new Long(text);
    	logger.info("key:" + text);
    	if(service == null){
    		logger.error("\n\nMal vamos...!");
    	}else{
	        Role c = service.findById(key);
	        logger.info("Role:" + c.toString());
	        this.setValue(c);
    	}
    }

    // Converts a Category to a String (when displaying form)
    @Override
    public String getAsText() {
    	Role c = (Role) this.getValue();
        return (new Long(c.getId())).toString();
    }
}