package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import telefonica.aaee.dao.maestras.model.Role;
import telefonica.aaee.dao.maestras.service.RoleService;
import telefonica.aaee.marte.form.RoleForm;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import telefonica.aaee.marte.validators.RoleFormValidator;
import telefonica.aaee.util.StringConvertUtil;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/role")
public class RoleController  extends BasicController{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	private static final String FORM_NEW = "role-form-new";
	private static final String FORM_EDIT = "role-form-edit";
	private static final String RESULT_PAGE = "html/roles/roles-show";

	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleFormValidator roleFormValidator;
	
	@InitBinder("role")
	private void initBinder(WebDataBinder binder) {
		binder.addValidators(roleFormValidator);
	}
	
	private boolean checkSecurity(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().contains(new SimpleGrantedAuthority(ROLE_ADMIN));
	}


	@RequestMapping(value="/new")
	public ModelAndView nueva() {
		
		if(!checkSecurity()){
			return new ModelAndView("index");
		}
		return new ModelAndView(FORM_NEW, "roleForm", new RoleForm());
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET) 
	public ModelAndView edit(
			@PathVariable Long id,
            final RedirectAttributes redirectAttributes) throws RoleNotFoundException { 
		
		if(!checkSecurity()){
			return new ModelAndView("index");
		}
		
		logger.info("Se ha recibido el parámetro {id} :{"+id+"}");
          
		Role roleAModificar = roleService.findById(id);
		
		RoleForm roleForm = roleToForm(roleAModificar);
		
		ModelAndView modelAndView = new ModelAndView(FORM_EDIT);
		modelAndView.addObject("role", roleForm);
		
        return modelAndView;
    } 
	
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST) 
	public ModelAndView update(
			@ModelAttribute @Valid RoleForm roleForm,
			BindingResult result,
			@PathVariable Integer id,  
            final RedirectAttributes redirectAttributes,
            Locale locale) throws RoleNotFoundException { 
		
		
		if(!checkSecurity()){
			return new ModelAndView("index");
		}
		
		logger.info(environment.getProperty("role.nombre.empty"));
		logger.info(environment.getProperty("role.definicion.empty"));
		
		logger.info("updateRole:Se ha recibido el parámetro {id} :{"+id+"}");
		logger.info("updateRole:roleForm:{"+ roleForm.toString()+"}");
          
		if (result.hasErrors()){
			ModelAndView modelAndView = new ModelAndView(FORM_EDIT);
			getModelAndViewWithErrors(roleForm, result, locale,
					modelAndView);
            return modelAndView;  
		}

		logger.info("update:{"+ roleForm.toString() +"}");
		
		Role mod = formToRole(roleForm);
		
		Role role = roleService.update(mod);
		
		logger.info("guardada!:role :{"+ role.toString() +"}");
		
		ModelAndView modelAndView = getPageByNumber(1);
		modelAndView.addObject("nuevaRole", roleToForm(role));
		return modelAndView;
		
    }

	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute @Valid RoleForm roleForm,  
            BindingResult result,  
            final RedirectAttributes redirectAttributes, 
            Locale locale) {
		
		
		if(!checkSecurity()){
			return new ModelAndView("index");
		}
		
		Assert.notNull(roleForm.getNombre());
		
		if (result.hasErrors()){
			ModelAndView modelAndView = new ModelAndView(FORM_NEW);
			getModelAndViewWithErrors(roleForm, result, locale, modelAndView);
            return modelAndView;  
		}
		
		List<Role> roles = roleService.findByNombre(
				roleForm.getNombre());
		
		logger.info("roles.size():{"+ roles.size() +"}");
		if(roles.size() == 0){
			
			Role nuevaRole = new Role.Builder()
						.nombre(roleForm.getNombre())
						.comentarios(roleForm.getComentarios())
						.build();
			
			nuevaRole = roleService.create(nuevaRole);
			
			logger.info("nuevaRole:" + nuevaRole.toString());
			
			ModelAndView modelAndView = getPageByNumber(1);
			modelAndView.addObject("nuevaRole", roleToForm(nuevaRole));
			return modelAndView;
		}else{
			logger.info("Upss! Hay errores...: Nombre repetido!");
			ModelAndView modelAndView = new ModelAndView(FORM_NEW);
			modelAndView.addObject("mensajeError", "Nombre de role ["+roleForm.getNombre()+"]... repetido!");
			modelAndView.addObject("roleForm", roleForm);
			return modelAndView;
		}
	}


	private Role formToRole(RoleForm roleForm) {
		Role mod = new Role();
		
		logger.info(String.format("Se recibe de la página JSP: [%s]", roleForm.toString()));
		
		mod.setId(roleForm.getId());
		mod.setNombre(StringConvertUtil.fromISOtoUTF8(roleForm.getNombre()));
		mod.setComentarios(StringConvertUtil.fromISOtoUTF8(roleForm.getComentarios()));

		logger.info(String.format("Corrección (iso-8859-1)->(utf-8): [%s]", mod.toString()));

		
		return mod;
		
		
	} 

	private RoleForm roleToForm(Role role) {
		RoleForm mod = new RoleForm();
		
		mod.setId(role.getId());
		mod.setNombre(role.getNombre());
		mod.setComentarios(role.getComentarios());
		
		return mod;
		
		
	} 


	private void getModelAndViewWithErrors(RoleForm roleForm, BindingResult result,
			Locale locale, ModelAndView modelAndView) {
		StringBuffer errores = new StringBuffer();
		for(FieldError fieldError : result.getFieldErrors()){
			logger.info("fieldError:" + fieldError.getCode());
			logger.info(messageSource.getMessage(fieldError.getCode(), null, null, locale));
			
			errores.append(messageSource.getMessage(fieldError.getCode(), null, null, locale));
			errores.append("<br>");
		}
		modelAndView.addObject("mensajeError", errores.toString());
		modelAndView.addObject("roleForm", roleForm);
		logger.info("Upss! Hay errores..." + errores.toString());
	}


	private ModelAndView getPageByNumber(Integer pageNumber) {
		
		if(!checkSecurity()){
			return new ModelAndView("index");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(RESULT_PAGE);
		Page<Role> page = roleService.getPage(pageNumber);
		
		logger.info("Se ha recibido page :{"+page.toString()+"}");
		
		for(Role c : page){
			logger.info(c.toString());
		}
	
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    int pageSize = page.getNumberOfElements();
		
		logger.info(String.format("current : [%d]", current));
		logger.info(String.format("begin : [%d]", begin));
		logger.info(String.format("end : [%d]", end));
		logger.info(String.format("pageSize : [%d]", pageSize));
	    
	    modelAndView.addObject("roles", page.getContent());
	    modelAndView.addObject("beginIndex", begin);
	    modelAndView.addObject("endIndex", end);
	    modelAndView.addObject("currentIndex", current);
	    modelAndView.addObject("totalPages", page.getTotalPages());
	    modelAndView.addObject("pageSize", pageSize);
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
	
		Page<Role> lista = roleService.findByGeneric(queBuscar, pageNumber, numItems);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(RESULT_PAGE);
		modelAndView.addObject("page", new PageWrapper<Role>(lista, this.getUrl(queBuscar)));
		modelAndView.addObject("labels", this.getLabels(queBuscar));
		return modelAndView;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	protected ModelAndView searchGet(@RequestParam(value = "queBuscar", required = false) String queBuscar, @RequestParam(value = "page", required = false) Integer pageNumber, @RequestParam(value = "size", required = false) Integer numItems,
			final RedirectAttributes redirectAttributes, Locale locale, Pageable pageable) {
				
			if(!checkSecurity()){
				return new ModelAndView("index");
			}
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	protected ModelAndView searchPost(@RequestParam(value = "queBuscar", required = false) String queBuscar, @RequestParam(value = "page", required = false) Integer pageNumber, @RequestParam(value = "size", required = false) Integer numItems,
			final RedirectAttributes redirectAttributes, Locale locale, Pageable pageable) {
				
			if(!checkSecurity()){
				return new ModelAndView("index");
			}
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}	


	private String getUrl(String queBuscar) {
		StringBuilder url = new StringBuilder();
		url.append("/role/search/?");
		url.append("queBuscar=" + queBuscar);
		return url.toString();
	}
	
	private List<String> getLabels(String queBuscar) {
		List<String> labels = new ArrayList<>();
		labels.add(queBuscar);
		return labels;
	}
}
