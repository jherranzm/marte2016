package telefonica.aaee.informes.validators;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import telefonica.aaee.dao.maestras.model.Usuario;
import telefonica.aaee.dao.maestras.service.UsuarioService;
import telefonica.aaee.informes.form.UsuarioForm;

@Component 
public class UsuarioFormValidator implements Validator {

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource
	private UsuarioService usuarioService;

	@Resource
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		 return UsuarioForm.class.isAssignableFrom(clazz); 
	}

	@Override
	public void validate(Object target, Errors errors) {

		UsuarioForm usuario = (UsuarioForm) target;
		
		logger.info("UsuarioFormValidator:validate:" + usuario.toString());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "usuario.username.empty"); 
		/**
		 * Comprobación nombre de usuario duplicado...
		 * 
		 */
		List<Usuario> usuarios = 
				usuarioService.findByUsernameDuplicado(usuario.getUsername(), usuario.getId());
		if (usuarios.size() > 0){
			errors.rejectValue("username", "usuario.username.duplicated");
		}
		
		
		for(ObjectError error : errors.getAllErrors()){
			logger.info(error.getCode() + ":" + error.getDefaultMessage() + ":" + error.getObjectName());
		}
	}

}
