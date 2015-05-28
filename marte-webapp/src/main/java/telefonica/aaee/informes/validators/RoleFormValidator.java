package telefonica.aaee.informes.validators;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import telefonica.aaee.dao.maestras.model.Role;
import telefonica.aaee.dao.maestras.service.RoleService;
import telefonica.aaee.informes.form.RoleForm;

@Component 
public class RoleFormValidator implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private RoleService roleService;

	@Resource
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		 return RoleForm.class.isAssignableFrom(clazz); 
	}

	@Override
	public void validate(Object target, Errors errors) {
		

		RoleForm role = (RoleForm) target;
		
		logger.info(String.format("RoleValidator:validate:[%s]", role.toString()));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors
				, "nombre"
				, "role.nombre.empty"
				); 
		ValidationUtils.rejectIfEmptyOrWhitespace(
				errors
				, "comentarios"
				, "role.comentarios.empty"
				);
		
		
		/**
		 * Comprobación nombre de role duplicado...
		 * 
		 */
		List<Role> roles = 
				roleService.findByNombreDuplicado(role.getNombre(), role.getId());
		if (roles.size() > 0){
			errors.rejectValue("nombre", "role.nombre.duplicated");
		}
	}

}
