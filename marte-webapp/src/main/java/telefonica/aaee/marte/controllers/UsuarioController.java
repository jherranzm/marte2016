package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import telefonica.aaee.dao.maestras.exceptions.UsuarioNotFoundException;
import telefonica.aaee.dao.maestras.model.Role;
import telefonica.aaee.dao.maestras.model.Usuario;
import telefonica.aaee.dao.maestras.service.RoleService;
import telefonica.aaee.dao.maestras.service.UsuarioService;
import telefonica.aaee.marte.form.UsuarioForm;
import telefonica.aaee.marte.model.RoleEditor;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import telefonica.aaee.marte.validators.UsuarioFormValidator;
import telefonica.aaee.util.StringConvertUtil;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/usuario")
public class UsuarioController  extends BasicController{

	protected final Log logger = LogFactory.getLog(getClass());
	
	//private static final String FORM_NEW = "usuario-form-new";
	private static final String FORM_NEW = "html/usuarios/usuario-new";
//	private static final String FORM_EDIT = "usuario-form-edit";
	private static final String FORM_EDIT = "html/usuarios/usuario-edit";
	private static final String RESULT_PAGE = "html/usuarios/usuarios-show";
	
	private List<Role> cacheRoles = new ArrayList<Role>();
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioFormValidator usuarioValidator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@InitBinder("usuarioForm")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(usuarioValidator);
		binder.registerCustomEditor(Role.class, new RoleEditor(roleService));
	}
	

	@RequestMapping(value="/new")
	public ModelAndView showFormNew() {
		
		cacheRoles = roleService.findAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(FORM_NEW);
		modelAndView.addObject("usuarioForm", new UsuarioForm());
		modelAndView.addObject("rolesDisponibles", cacheRoles);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET) 
	public ModelAndView deleteById(
			@PathVariable Long id,  
            final RedirectAttributes redirectAttributes, Locale locale) throws UsuarioNotFoundException { 
		
		logger.info(String.format("id : [%d]", id));
          
		Usuario aBorrar = usuarioService.findById(id);
		if(aBorrar == null){
			//TODO: informar error en la búsqueda
		}else{
			logger.info(String.format("Usuario a Borrar LOCALIZADO! [%s]", aBorrar.toString()));
			if(usuarioService.delete(id)){
				logger.info(String.format("Usuario a Borrar ELIMINADO! [%s]", aBorrar));
			}else{
				//TODO: informar error en el borrado de usuario
			}
		}
		
		ModelAndView modelAndView = getMAVFromQueBuscar("", 1, numItemsPorPagina);
        return modelAndView;  
    } 

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET) 
	public ModelAndView edit(
			@PathVariable Long id,  
            final RedirectAttributes redirectAttributes) throws UsuarioNotFoundException { 
		
		logger.info("Se ha recibido el parámetro {id} :{"+id+"}");
          
		Usuario modificable = usuarioService.findById(id);  
		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm = usuarioToForm(modificable);
		
		ModelAndView modelAndView = new ModelAndView(FORM_EDIT);
		modelAndView.addObject("rolesDisponibles", roleService.findAll());
		modelAndView.addObject("usuarioForm", usuarioForm);
		
        return modelAndView;
    } 

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST) 
	public ModelAndView update(
			HttpServletRequest request,
			@ModelAttribute @Valid UsuarioForm usuarioForm,
			BindingResult result,
			@PathVariable Long id,  
            final RedirectAttributes redirectAttributes, 
            Locale locale) throws UsuarioNotFoundException { 
		
		logger.info(String.format("request.getCharacterEncoding: [%s]", request.getCharacterEncoding()));

		logger.info("update:Se ha recibido el parámetro {id} :{"+id+"}");
		logger.info("update:usuarioForm :{"+ usuarioForm.toString() +"}");
          
		if (result.hasErrors()){
			ModelAndView modelAndView = new ModelAndView(FORM_EDIT);
			getModelAndViewWithErrors(usuarioForm, result, locale, modelAndView);
            return modelAndView;  
		}
		
		logger.info("Buscando nombre de usuario duplicado...");
		
		List<Usuario> usuarios = usuarioService.findByUsernameDuplicado(
				usuarioForm.getNombre(),
				usuarioForm.getId());
		
		logger.info("usuarios.size():{"+ usuarios.size() +"}");
		if(usuarios.size() == 0){
			logger.info("update:{"+ usuarioForm.toString() +"}");
			
			Usuario usuario = formToUsuario(usuarioForm);
			
			usuario = usuarioService.update(usuario);
			
			usuarioForm = usuarioToForm(usuario);
			
			logger.info("guardada!:usuario.id :{" + usuario.getId() +"}");
			logger.info("guardada!:usuario :{" + usuario.toString() +"}");
		}else{
			logger.info("update:usuario.nombre :{"+ usuarioForm.getNombre()+"}");
		}
	
		ModelAndView modelAndView = getMAVFromQueBuscar("", 1, numItemsPorPagina);
		modelAndView.addObject("nuevoUsuario", usuarioForm);
		
        return modelAndView;
    }

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute @Valid UsuarioForm usuarioForm,  
	        BindingResult result,  
	        final RedirectAttributes redirectAttributes, 
	        Locale locale) {
		
		if (result.hasErrors()){
			ModelAndView modelAndView = new ModelAndView(FORM_NEW);
			getModelAndViewWithErrors(usuarioForm, result, locale, modelAndView);
            return modelAndView;  
		}
		
		List<Usuario> usuarios = usuarioService.findByNombre(
				usuarioForm.getNombre());
		
		logger.info("usuarios.size():{"+ usuarios.size() +"}");
		if(usuarios.size() == 0){
			
			Usuario nuevoUsuario =  formToUsuario(usuarioForm);
			
			//nuevoUsuario.setPassword(UtilNombre.nombreAleatorio());
			String pass = nuevoUsuario.getPassword();
			String encPass = passwordEncoder.encode(pass);
			nuevoUsuario.setPassword(encPass);
			
			nuevoUsuario = usuarioService.create(nuevoUsuario);
			
			logger.info("Hemos guardado el nuevo usuario : " + nuevoUsuario.toString());
			usuarioForm = usuarioToForm(nuevoUsuario);
			
			ModelAndView modelAndView = getMAVFromQueBuscar("", 1, numItemsPorPagina);
			modelAndView.addObject("nuevoUsuario", usuarioForm);
			return modelAndView;
			
		}else{
			logger.info("Upss! Hay errores...: Nombre repetido!");
			ModelAndView modelAndView = new ModelAndView(FORM_NEW);
			modelAndView.addObject("mensajeError", "Nombre del usuario : ["+usuarioForm.getNombre()+"]... repetido!");
			modelAndView.addObject("usuarioForm", usuarioForm);
			modelAndView.addObject("rolesDisponibles", roleService.findAll());
			return modelAndView;
		}
	}


	private Usuario formToUsuario(UsuarioForm usuarioForm) {
		
		logger.info(String.format("Recibimos: [%s]", usuarioForm.toString()));
		
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioForm.getId());		
		usuario.setUsername(StringConvertUtil.fromISOtoUTF8(usuarioForm.getUsername()));
		usuario.setPassword(StringConvertUtil.fromISOtoUTF8(usuarioForm.getPassword1()));
		
		usuario.setNombre(StringConvertUtil.fromISOtoUTF8(usuarioForm.getNombre()));
		usuario.setApellido1(StringConvertUtil.fromISOtoUTF8(usuarioForm.getApellido1()));
		usuario.setApellido2(StringConvertUtil.fromISOtoUTF8(usuarioForm.getApellido2()));
		
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setEnabled(usuarioForm.isEnabled());
		usuario.setComentarios(StringConvertUtil.fromISOtoUTF8(usuarioForm.getComentarios()));
		
		Set<Role> roles = new HashSet<Role>();
		for(Role p : usuarioForm.getRoles()){
			roles.add(p);
		}
		usuario.setRoles(roles);
		
		logger.info(String.format("Obtenemos: [%s]", usuario.toString()));
		
		return usuario;
	}	

	private UsuarioForm usuarioToForm(Usuario usuario) {
		
		UsuarioForm usuarioForm = new UsuarioForm();
		
		logger.info(usuario.toString());
		
		usuarioForm.setId(usuario.getId());
		
		usuarioForm.setUsername(usuario.getUsername());

		usuarioForm.setNombre(usuario.getNombre());
		usuarioForm.setApellido1(usuario.getApellido1());
		usuarioForm.setApellido2(usuario.getApellido2());
		
		usuarioForm.setEmail(usuario.getEmail());
		usuarioForm.setEnabled(usuario.isEnabled());
		usuarioForm.setComentarios(usuario.getComentarios());
		
		Role[] roles = new Role[usuario.getRoles().size()];
		int k=0;
		for(Role p : usuario.getRoles()){
			roles[k++] = p;
			logger.info("Cargando role:" + p.toString());
		}
		usuarioForm.setRoles(roles);
		
		logger.info(usuarioForm.toString());
		
		return usuarioForm;
	}


	private void getModelAndViewWithErrors(UsuarioForm form, BindingResult result,
			Locale locale, ModelAndView modelAndView) {
		StringBuffer errores = new StringBuffer();
		for(FieldError fieldError : result.getFieldErrors()){
			logger.info("fieldError:" + fieldError.getCode());
			logger.info(messageSource.getMessage(fieldError.getCode(), null, null, locale));
			
			errores.append(messageSource.getMessage(fieldError.getCode(), null, null, locale));
			errores.append("<br>");
		}
		modelAndView.addObject("mensajeError", errores.toString());
		modelAndView.addObject("usuarioFormForm", form);
		modelAndView.addObject("rolesDisponibles", roleService.findAll());
		logger.info("Upss! Hay errores..." + errores.toString());
	}	
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	protected ModelAndView searchGet(
			@RequestParam(value = "queBuscar", required = false) String queBuscar
			, @RequestParam(value = "page", required = false) Integer pageNumber
			, @RequestParam(value = "size", required = false) Integer numItems
			, final RedirectAttributes redirectAttributes
			, Locale locale
			, Pageable pageable) {
				
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}


	@RequestMapping(value = "/search", method = RequestMethod.POST)
	protected ModelAndView searchPost(
			@RequestParam(value = "queBuscar", required = false) String queBuscar
			, @RequestParam(value = "page", required = false) Integer pageNumber
			, @RequestParam(value = "size", required = false) Integer numItems
			, final RedirectAttributes redirectAttributes
			, Locale locale
			, Pageable pageable) {
				
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}


	@Override
	protected ModelAndView getMAVFromQueBuscar(String queBuscar,
			Integer pageNumber, Integer numItems) {
		queBuscar = (queBuscar == null ? "" : queBuscar);
		queBuscar = ("--".equals(queBuscar) ? "" : queBuscar);
		
		pageNumber = (pageNumber == null ? 1 : pageNumber);
		numItems = (numItems == null ? numItemsPorPagina : numItems);
		
		logger.info(String.format("Search(queBuscar)  : [%s]", queBuscar));
		logger.info(String.format("Search(pageNumber) : [%d]", pageNumber));
		logger.info(String.format("Search(numItems)   : [%d]", numItems));
	
		Page<Usuario> lista = usuarioService.findByGeneric(queBuscar, pageNumber, numItems);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(RESULT_PAGE);
		modelAndView.addObject("page", new PageWrapper<Usuario>(lista, this.getUrl(queBuscar)));
		modelAndView.addObject("labels", this.getLabels(queBuscar));
		return modelAndView;
	}	

	private String getUrl(String queBuscar) {
		StringBuilder url = new StringBuilder();
		url.append("/usuario/search/?");
		url.append("queBuscar=" + queBuscar);
		return url.toString();
	}
	
	private List<String> getLabels(String queBuscar) {
		List<String> labels = new ArrayList<>();
		labels.add(queBuscar);
		return labels;
	}
	
	@RequestMapping(value = "/getRandPass", method = RequestMethod.GET)
	public @ResponseBody
	String getRandomPassword(
			@RequestParam(value = "username", required = true) String username) {
		logger.info(String.format("username : [%s] ", username));
		String ret = RandomStringUtils.randomAlphabetic(3).toUpperCase()+username;
		logger.info(String.format("password : [%s] ", ret));
		return ret;
	}

}
