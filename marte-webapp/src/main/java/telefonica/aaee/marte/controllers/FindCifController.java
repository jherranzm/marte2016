package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlana;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.service.AcuerdoService;
import telefonica.aaee.marte.acuerdos.dao.service.CodAPIService;
import telefonica.aaee.marte.acuerdos.dao.service.MotivoBajaService;
import telefonica.aaee.marte.acuerdos.dao.service.SituacionPlanaService;
import telefonica.aaee.marte.acuerdos.dao.service.TramitacionAPIService;
import telefonica.aaee.marte.form.TramitacionBajaForm;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/findcif")
public class FindCifController extends BasicController {

	private static final String SHOW_ACUERDO = "html/findcif/acuerdo-show";
	private static final String SHOW_ACUERDO_TRAM = "html/findcif/show-acuerdo-tram";
	private static final String FIND_CIF_FORM = "html/findcif/findCif-form";
	private static final String RESULT_PAGE = "html/findcif/findCif-list";
	
	private static final String TRAM_BAJA_FORM = "html/findcif/tram-baja-form";
	private static final String TRAM_BAJA_CONF = "html/findcif/tram-baja-conf";

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private AcuerdoService acuerdoService;
	
	@Autowired
	private TramitacionAPIService tramitacionAPIService;
	
	@Autowired
	private MotivoBajaService motivoBajaService;
	
	@Autowired
	private SituacionPlanaService situacionPlanaService;
	
	@Autowired
	private CodAPIService codAPIService;
	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public ModelAndView findCifGet(
			HttpServletRequest req
			) {
		
		logger.info("/findcif/find");
		
		String userAgent = req.getHeader("user-agent");
		
		logger.info("UserAgent: " + userAgent);
		
		UserAgent ua = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));		
		
		logger.info("Browser: " + ua.getBrowser());
		logger.info("MajorVersion: " + ua.getBrowserVersion().getMajorVersion());
		logger.info("MinorVersion: " + ua.getBrowserVersion().getMinorVersion());
		
		
		ModelAndView model2 = new ModelAndView();
		model2.setViewName(FIND_CIF_FORM);
		
		return model2;
	}
	
	@RequestMapping(value="/getAcuerdos", method=RequestMethod.POST)
	public ModelAndView findCifPost(
			@RequestParam String cif
			) {
		
		cif = (cif == null) ? "" : cif;
		
		logger.info(String.format("Cif recibido: [%s]", cif));
		
		ModelAndView modelAndView = getMAVFromQueBuscar(cif, 1, numItemsPorPagina);
        return modelAndView;  
	}
	
	@RequestMapping(value="/show/{idAcuerdo}", method=RequestMethod.GET)
	public ModelAndView showAcuerdo(
			@PathVariable String idAcuerdo
			) {

		List<String> errores = new ArrayList<String>();
		idAcuerdo = (idAcuerdo == null) ? "" : idAcuerdo;

		ModelAndView modelAndView = new ModelAndView();
		if("".equals(idAcuerdo)){
			errores.add("El Acuerdo viene sin informar.");
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		Acuerdo acuerdo = acuerdoService.findById(idAcuerdo);
		if(acuerdo == null){
			errores.add("El Acuerdo NO existe en el sistema.");
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.setViewName(SHOW_ACUERDO);
		
		// Tramitaciones
		Page<TramitacionAPI> tramitaciones = tramitacionAPIService.findByIDAcuerdo(acuerdo, 1);
		logger.info(String.format("Número de tramitaciones : [%s][%d]", idAcuerdo, tramitaciones.getContent().size()));
		modelAndView.addObject("page", new PageWrapper<TramitacionAPI>(tramitaciones, this.getUrl("")));
		modelAndView.addObject("labels", this.getLabels(""));
		
		TramitacionBajaForm tramBajaForm = new TramitacionBajaForm();
		tramBajaForm.setIdAcuerdo(acuerdo.getIDAcuerdo());
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.MONTH) == Calendar.DECEMBER){
			tramBajaForm.setBonificacion("SI");
		}

		logger.info(String.format("[%s]", tramBajaForm.toString()));
		
		modelAndView.addObject("tramBajaForm", tramBajaForm);
		modelAndView.addObject("causas", motivoBajaService.findAll());
		modelAndView.addObject("bonificaciones", new String[]{ "SI", "NO"});

		addSituacionPlanaToMAV(modelAndView, acuerdo);
		
		
		
        return modelAndView;  
	}
	
	@RequestMapping(value="/baja/{idAcuerdo}", method=RequestMethod.GET)
	public ModelAndView showFormBajaAcuerdo(
			@ModelAttribute TramitacionBajaForm form,
			@PathVariable String idAcuerdo
			) {
		List<String> errores = new ArrayList<String>();
		idAcuerdo = (idAcuerdo == null) ? "" : idAcuerdo;

		ModelAndView modelAndView = new ModelAndView();
		if("".equals(idAcuerdo)){
			errores.add("El Acuerdo viene sin informar.");
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		Acuerdo acuerdo = acuerdoService.findById(idAcuerdo);
		if(acuerdo == null){
			errores.add("El Acuerdo NO existe en el sistema.");
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.setViewName(TRAM_BAJA_FORM);
		
		if(form == null){
			form = new TramitacionBajaForm();
			form.setIdAcuerdo(acuerdo.getIDAcuerdo());
			Calendar cal = Calendar.getInstance();
			if(cal.get(Calendar.MONTH) == Calendar.DECEMBER){
				form.setBonificacion("SI");
			}

		}
		logger.info(String.format("[%s]", form.toString()));
		modelAndView.addObject("tramBajaForm", form);
		modelAndView.addObject("causas", motivoBajaService.findAll());
		modelAndView.addObject("bonificaciones", new String[]{ "SI", "NO"});
		
		addSituacionPlanaToMAV(modelAndView, acuerdo);

		return modelAndView; 
	}

	private void addSituacionPlanaToMAV(ModelAndView modelAndView,
			Acuerdo acuerdo) {
		SituacionPlana sp = situacionPlanaService.findByIDAcuerdo(acuerdo.getIDAcuerdo());
		if(sp == null){
			SituacionPlanaEstado spe = new SituacionPlanaEstado();
			spe.setEstado(null);
			sp = new SituacionPlana();
			sp.setSituacionPlanaEstado(spe);
		}
		logger.info(String.format("[%s]", sp));
		modelAndView.addObject("sp", sp);
	}
	
	@RequestMapping(value="/baja/1", method=RequestMethod.POST)
	public ModelAndView bajaAcuerdo(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
            final RedirectAttributes redirectAttributes, 
            Locale locale
			) {
		
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("**    BAJA               **********", ""));
		logger.info(String.format("***********************************", ""));
		for(String param : java.util.Collections.list(request.getAttributeNames())){
			logger.info(String.format("REQUEST : [%s][%s]", param, request.getAttribute(param)));
		}
		logger.info(String.format("FORM : [%s]", form.toString()));
		logger.info(String.format("***********************************", ""));
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		MotivoBaja motivoBaja = motivoBajaService.findById(form.getMotivoBajaMARTE());

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(TRAM_BAJA_CONF);
		modelAndView.addObject("tramBajaForm", form);
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.addObject("motivoBaja", motivoBaja);
		modelAndView.addObject("causas", motivoBajaService.findAll());
		return modelAndView;
	}
		
	@RequestMapping(value="/baja/2", method=RequestMethod.POST)
	public ModelAndView bajaAcuerdoConfirmada(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Locale locale
			) {
		
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("**    BAJA               **********", ""));
		logger.info(String.format("***********************************", ""));
		for(String param : java.util.Collections.list(request.getAttributeNames())){
			logger.info(String.format("REQUEST : [%s][%s]", param, request.getAttribute(param)));
		}
		logger.info(String.format("FORM : [%s]", form.toString()));
		logger.info(String.format("***********************************", ""));
		
//		TramitacionAPI tramAPI = new TramitacionAPI();
//		CodAPI codAIBaja = codAPIService.findByCodAPI("Baja");
//		tramAPI.setCodAPI(codAPIBaja);
//		TramitacionAPI tram = tramitacionAPIService.save(tramAPI);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(FIND_CIF_FORM);
		return modelAndView;
	}
	
	@RequestMapping(value="/show/{idAcuerdo}/{idTramitacion}", method=RequestMethod.GET)
	public ModelAndView showAcuerdo(
			@PathVariable String idAcuerdo,
			@PathVariable Long idTramitacion
			) {
		List<String> errores = new ArrayList<String>();
		idAcuerdo = (idAcuerdo == null) ? "" : idAcuerdo;
		idTramitacion = (idTramitacion == null) ? 0 : idTramitacion;

		ModelAndView modelAndView = new ModelAndView();
		if("".equals(idAcuerdo)){
			errores.add("El Acuerdo viene sin informar.");
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		Acuerdo acuerdo = acuerdoService.findById(idAcuerdo);
		if(acuerdo == null){
			errores.add(String.format("El Acuerdo [%s] NO existe en el sistema.", idAcuerdo));
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		logger.info(acuerdo);
		modelAndView.addObject("acuerdo", acuerdo);
		
		addSituacionPlanaToMAV(modelAndView, acuerdo);

		TramitacionAPI tramitacion = tramitacionAPIService.findById(idTramitacion);
		if(tramitacion == null){
			errores.add(String.format("La Tramitación [%d] NO existe en el sistema.", idTramitacion));
			modelAndView.setViewName(FIND_CIF_FORM);
			modelAndView.addObject("errores", errores);
			return modelAndView;
		}
		logger.info(tramitacion);
		modelAndView.addObject("tramitacion", tramitacion);
		modelAndView.setViewName(SHOW_ACUERDO_TRAM);
		
        return modelAndView;  
		
	}
	
	
	
	private String getUrl(String queBuscar) {
		StringBuilder url = new StringBuilder();
		url.append("findcif/find");
		url.append("cif=" + queBuscar);
		return url.toString();
	}
	
	private List<String> getLabels(String queBuscar) {
		List<String> labels = new ArrayList<>();
		labels.add(queBuscar);
		return labels;
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
	
		Page<Acuerdo> lista = acuerdoService.findByCif(queBuscar, pageNumber);
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(RESULT_PAGE);
		modelAndView.addObject("page", new PageWrapper<Acuerdo>(lista, this.getUrl(queBuscar)));
		modelAndView.addObject("labels", this.getLabels(queBuscar));
		return modelAndView;
	}

}
