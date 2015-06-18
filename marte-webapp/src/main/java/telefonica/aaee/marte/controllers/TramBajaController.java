package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.CodAPI;
import telefonica.aaee.marte.acuerdos.dao.model.EstadoTramitacion;
import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;
import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlana;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.editor.MotivoBajaEditor;
import telefonica.aaee.marte.form.TramitacionBajaForm;
import telefonica.aaee.marte.form.TramitacionForm;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import telefonica.aaee.util.Constantes;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/tram/baja")
public class TramBajaController extends BasicController {

	private static final String TRAM_BAJA_FORM = "html/findcif/tram-baja-form";
	private static final String TRAM_BAJA_CONF = "html/findcif/tram-baja-conf";
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@InitBinder("tramitacionBajaForm")
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MotivoBaja.class, new MotivoBajaEditor(motivoBajaService));
	}

	
	@RequestMapping(value="/form/{idAcuerdo}", method={RequestMethod.GET, RequestMethod.POST})
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


	@RequestMapping(value="/form", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoForm(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
            final RedirectAttributes redirectAttributes, 
			Authentication auth,
            Locale locale
			) {
		
		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		logger.info(String.format("[%s]", acuerdo.toString()));
		MotivoBaja motivoBaja = motivoBajaService.findById(form.getMotivoBajaMARTE());
		logger.info(String.format("[%s]", motivoBaja.toString()));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(TRAM_BAJA_FORM);
		modelAndView.addObject("tramBajaForm", form);
		modelAndView.addObject("acuerdo", acuerdo);
		//modelAndView.addObject("motivoBaja", motivoBaja);
		modelAndView.addObject("causas", motivoBajaService.findAll());
		return modelAndView;
	}


	@RequestMapping(value="/conf", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoConf(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		logger.info(String.format("[%s]", acuerdo.toString()));
		MotivoBaja motivoBaja = motivoBajaService.findById(form.getMotivoBajaMARTE());
		logger.info(String.format("[%s]", motivoBaja.toString()));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(TRAM_BAJA_CONF);
		modelAndView.addObject("tramBajaForm", form);
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.addObject("causas", motivoBajaService.findAll());
		modelAndView.addObject("motivoBaja", motivoBaja.getDescMotivoBajaMARTE());
		return modelAndView;
	}
	
	@RequestMapping(value="/ok", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoConfirmada(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		TramitacionAPI tramAPI = new TramitacionAPI();
		CodAPI codAPIBaja = codAPIService.findById("Baja");
		tramAPI.setCodAPI(codAPIBaja);
		tramAPI.setCodAPIOrig(codAPIBaja);
		
		Date ahora = Calendar.getInstance().getTime();
		tramSetFechas(tramAPI, ahora);
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		copyAcuerdoToTram(tramAPI, acuerdo);
		
		tramAPI.setObservaciones("");
		tramAPI.setEmail("");
		tramAPI.setTipoSoporte("");
		tramAPI.setSoporteAnterior("");
		tramAPI.setDireccionAnterior("");
		tramAPI.setCcc("");
		tramAPI.setCccAnteriror("");
		tramAPI.setAutorizacion(form.getHorus() != null ? form.getHorus() : "");
		tramAPI.setBajaCliente("False");
		tramAPI.setTramitar("False");
		tramAPI.setCargaGAE("False");
		tramAPI.setEnvioCorreo("S");
		
		tramSetRdV(tramAPI, acuerdo);
		
		//AjustesPlana
		AjustePlana ajuste = new AjustePlana();
		ajuste.setTipoDoc(acuerdo.getTipoDoc());
		ajuste.setCif(acuerdo.getCif());
		ajuste.setAcuerdoNumero(acuerdo.getAcuerdoNumero());
		ajuste.setFechaAny("2014");
		Page<AjustePlana> ajustes = ajustePlanaService.findByAcuerdoAny(ajuste, 1);
		List<AjustePlana> listaAjustes = ajustes.getContent();
		
		
		StringBuilder sbAjustes = new StringBuilder();
		if(listaAjustes.size() > 0){
			sbAjustes
			.append("**********************************").append(Constantes.CRLF)
			.append("* ACUERDO CON AJUSTES PENDIENTES *").append(Constantes.CRLF)
			.append("**********************************").append(Constantes.CRLF)
			;
			for(AjustePlana a : listaAjustes){
				logger.info(String.format("----- %s", a));
			}
		}
		
		MotivoBaja motivoBaja = motivoBajaService.findById(form.getMotivoBajaMARTE());
		logger.info(String.format("%s", motivoBaja.toString()));
		tramAPI.setMotivoBajaMARTE(motivoBaja);
		tramAPI.setMotivoBaja(motivoBaja.getIdMotivoBajaFX().shortValue());
		
		MarteUsuario marteUsuario = marteUsuarioService.findByUsername(userDetails.getUsername());
		logger.info(String.format("%s", marteUsuario.toString()));
		tramAPI.setMatPeticionario(marteUsuario);
		
		EstadoTramitacion estadoTramitacion = estadoTramitacionService.findById((short)0);
		logger.info(String.format("%s", estadoTramitacion.toString()));
		tramAPI.setMarteEstadoTramitacion(estadoTramitacion);
		
		tramAPI.setCambioImporteTemporal("P");
		tramAPI.setTrabajo("AAEE"+sdf.format(ahora));
		
		StringBuilder datosSession = getDatosSession(request, ahora,
				marteUsuario);


		
		StringBuffer sbPeticionTramitacion = new StringBuffer();
		sbPeticionTramitacion.append(sbAjustes);
		sbPeticionTramitacion
			.append("BAJA de Acuerdo").append(Constantes.CRLF)
			.append("===============").append(Constantes.CRLF)
			.append(String.format("Motivo de la Baja FX:[%d] %s" 
				, motivoBaja.getIdMotivoBajaFX()
				, motivoBaja.getDescMotivoBajaFX()))
			.append(Constantes.CRLF)
			.append(String.format("Motivo de la Baja MARTE:[%d] %s" 
				, motivoBaja.getIdMotivoBajaMARTE()
				, motivoBaja.getDescMotivoBajaMARTE()))
			.append(Constantes.CRLF);		
		if(!"".equals(form.getNuevocif())){
			sbPeticionTramitacion.append(String.format("Nuevo CIF : [%s]", form.getNuevocif())).append(Constantes.CRLF);
		}
		if(!"".equals(form.getHorus())){
			sbPeticionTramitacion.append(String.format("HORUS Bonificación : [%s]", form.getHorus())).append(Constantes.CRLF);
		}
		if(!"".equals(form.getPeticionTramitacion())){
			sbPeticionTramitacion.append(form.getPeticionTramitacion());
		}
		sbPeticionTramitacion.append(datosSession);
		tramAPI.setPeticionTramitacion(sbPeticionTramitacion.toString());
		
		
		

		logger.info(String.format("%s", tramAPI.toString()));

		TramitacionAPI tram = tramitacionAPIService.save(tramAPI);
		
		logger.info(String.format("%s", tramAPI.toString()));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.addObject("tramitacion", tram);
		
		addSituacionPlanaToMAV(modelAndView, acuerdo);
		
		modelAndView.setViewName(SHOW_ACUERDO_TRAM);
		return modelAndView;
	}



	private void getSignature(
			TramitacionForm form
			, HttpServletRequest request
			, Authentication auth) {
		
		String sessionId = request.getSession().getId();
		String userAgent = request.getHeader("user-agent");
		UserAgent ua = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		MarteUsuario marteUsuario = marteUsuarioService.findByUsername(userDetails.getUsername());
		
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** USER      : [%s]", userDetails.toString()));
		logger.info(String.format("** Username  : [%s]", userDetails.getUsername()));
		logger.info(String.format("** Usuario   : [%s]", marteUsuario.toString()));
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** FORM      : [%s]", form.toString()));
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** SessionId : [%s]", sessionId));
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** UserAgent : [%s]", userAgent));
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** Browser     : [%s]", ua.getBrowser()));
		logger.info(String.format("** MajorVersion: [%s]", ua.getBrowserVersion().getMajorVersion()));
		logger.info(String.format("** MinorVersion: [%s]", ua.getBrowserVersion().getMinorVersion()));
		logger.info(String.format("***********************************", ""));
		
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
