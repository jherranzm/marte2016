package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlana;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.editor.MotivoBajaEditor;
import telefonica.aaee.marte.form.TramitacionBajaForm;
import telefonica.aaee.marte.model.pagination.PageWrapper;

@Controller
@RequestMapping("/findcif")
public class FindCifController extends BasicController {

	private static final String SHOW_ACUERDO = "html/findcif/acuerdo-show";
	protected final Log logger = LogFactory.getLog(getClass());
	
	@InitBinder("tramitacionBajaForm")
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MotivoBaja.class, new MotivoBajaEditor(motivoBajaService));
	}

	
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public ModelAndView findCifGet(
			HttpServletRequest req
			) {
		
		logger.info("/findcif/find");
		
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
	
	@RequestMapping(value="/show/{idAcuerdo}/{idTramitacion}", method=RequestMethod.GET)
	public ModelAndView showAcuerdoAndTramitacion(
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
