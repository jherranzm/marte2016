package telefonica.aaee.marte.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import telefonica.aaee.dao.maestras.model.Cliente;
import telefonica.aaee.dao.maestras.model.VCliente;
import telefonica.aaee.dao.maestras.service.ClienteService;
import telefonica.aaee.dao.maestras.service.VClienteService;
import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.CodAPI;
import telefonica.aaee.marte.acuerdos.dao.model.EstadoTramitacion;
import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;
import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlana;
import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.service.AcuerdoService;
import telefonica.aaee.marte.acuerdos.dao.service.CodAPIService;
import telefonica.aaee.marte.acuerdos.dao.service.EstadoTramitacionService;
import telefonica.aaee.marte.acuerdos.dao.service.MarteUsuarioService;
import telefonica.aaee.marte.acuerdos.dao.service.MotivoBajaService;
import telefonica.aaee.marte.acuerdos.dao.service.SituacionPlanaService;
import telefonica.aaee.marte.acuerdos.dao.service.TramitacionAPIService;
import telefonica.aaee.marte.editor.MotivoBajaEditor;
import telefonica.aaee.marte.form.TramitacionBajaForm;
import telefonica.aaee.marte.helpers.CalculoFechas;
import telefonica.aaee.marte.marte.dao.service.AjustePlanaService;
import telefonica.aaee.marte.marte.model.AjustePlana;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import telefonica.aaee.util.Constantes;
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
	protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	@Autowired
	private MarteUsuarioService usuarioService;

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
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VClienteService vClienteService;
	
	@Autowired
	private EstadoTramitacionService estadoTramitacionService;
	
	@Autowired
	private AjustePlanaService ajustePlanaService;

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
	
	@RequestMapping(value="/baja/form/{idAcuerdo}", method={RequestMethod.GET, RequestMethod.POST})
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

	@RequestMapping(value="/baja/form", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoForm(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
            final RedirectAttributes redirectAttributes, 
			Authentication auth,
            Locale locale
			) {
		
		getSignature(form, request, auth);
		
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


	@RequestMapping(value="/baja/conf", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoConf(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		getSignature(form, request, auth);
		
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
	
	@RequestMapping(value="/baja/ok", method=RequestMethod.POST)
	public ModelAndView tramBajaAcuerdoConfirmada(
			@ModelAttribute TramitacionBajaForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();

		getSignature(form, request, auth);
		
		TramitacionAPI tramAPI = new TramitacionAPI();
		CodAPI codAPIBaja = codAPIService.findById("Baja");
		tramAPI.setCodAPI(codAPIBaja);
		tramAPI.setCodAPIOrig(codAPIBaja);
		
		Date ahora = Calendar.getInstance().getTime();
		Calendar fechaNula = Calendar.getInstance();
		fechaNula.set(Calendar.YEAR, 2500);
		fechaNula.set(Calendar.MONTH, Calendar.DECEMBER);
		fechaNula.set(Calendar.DAY_OF_MONTH, 31);
		
		// Fechas
		tramAPI.setFechaPeticion(ahora);
		tramAPI.setFechaTramPrevista(CalculoFechas.primerDiaHabil(ahora, false));
		tramAPI.setFechaTramAPI(fechaNula.getTime());
		tramAPI.setFechaGAE(fechaNula.getTime());
		tramAPI.setFinVigencia(fechaNula.getTime());
		tramAPI.setFechaCorreo(fechaNula.getTime());
		
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
		
		// RdV
		Cliente cli = clienteService.findByCif(acuerdo.getCif());
		if(cli == null){
			tramAPI.setMatComercial("");
			tramAPI.setMatJArea("");
		}else{
			logger.info(String.format("%s", cli.toString()));
			VCliente cliente = vClienteService.findById(cli.getId());
			tramAPI.setMatComercial(cliente.getMatVendedor());
			tramAPI.setMatJArea(cliente.getMatJArea());
		}
		
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
		
		MarteUsuario marteUsuario = usuarioService.findByUsername(userDetails.getUsername());
		logger.info(String.format("%s", marteUsuario.toString()));
		tramAPI.setMatPeticionario(marteUsuario);
		
		EstadoTramitacion estadoTramitacion = estadoTramitacionService.findById((short)0);
		logger.info(String.format("%s", estadoTramitacion.toString()));
		tramAPI.setMarteEstadoTramitacion(estadoTramitacion);
		
		tramAPI.setCambioImporteTemporal("P");
		tramAPI.setTrabajo("AAEE"+sdf.format(ahora));
		
		StringBuilder datosSession = new StringBuilder();
		datosSession.append(Constantes.CRLF).append(Constantes.CRLF).append(Constantes.CRLF);
		datosSession.append("Usuario:").append(marteUsuario.getCodUsuario());
		datosSession.append(" : ").append(marteUsuario.getNombre());
		datosSession.append(" ").append(marteUsuario.getApellido1()).append(Constantes.CRLF);
		datosSession.append("IP:").append(request.getRemoteAddr()).append(Constantes.CRLF);
		datosSession.append("Host:").append(request.getRemoteHost()).append(":").append(request.getRemotePort()).append(Constantes.CRLF);
		datosSession.append("SessionId:").append(request.getRequestedSessionId()).append(Constantes.CRLF);
		datosSession.append(sdf.format( ahora )).append(Constantes.CRLF);
		datosSession.append("====================").append(Constantes.CRLF);


		
		StringBuffer sbPeticionTramitacion = new StringBuffer();
		sbPeticionTramitacion.append(sbAjustes);
		sbPeticionTramitacion
			.append("BAJA de Acuerdo").append(Constantes.CRLF)
			.append("===============").append(Constantes.CRLF);
		sbPeticionTramitacion.append(String.format("Nuevo CIF : [%s]", form.getNuevocif())).append(Constantes.CRLF);
		sbPeticionTramitacion.append(String.format("HORUS Bonificación : [%s]", form.getHorus())).append(Constantes.CRLF);
		sbPeticionTramitacion.append(form.getPeticionTramitacion());
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


	private void copyAcuerdoToTram(TramitacionAPI tramAPI, Acuerdo acuerdo) {
		tramAPI.setAcuerdo(acuerdo);
		tramAPI.setTipoDoc(acuerdo.getTipoDoc());
		tramAPI.setCif(acuerdo.getCif());
		tramAPI.setNombre(acuerdo.getNombre());
		tramAPI.setAcuerdoNumero(acuerdo.getAcuerdoNumero());
		tramAPI.setTerritorio(acuerdo.getTerritorio());
		tramAPI.setImporteFijoMT(acuerdo.getImporteFijoMT());
		tramAPI.setImporteFijoNM(acuerdo.getImporteFijoNM());
		tramAPI.setDescuentoPlanaMT((int)acuerdo.getDescuentoPlanaMT());
		tramAPI.setDescuentoPlanaNM((int)acuerdo.getDescuentoPlanaNM());
		tramAPI.setImporteFijoMTAnterior(acuerdo.getImporteFijoMT());
		tramAPI.setImporteFijoNMAnterior(acuerdo.getImporteFijoNM());
		tramAPI.setDescuentoPlanaMTAnterior((int)acuerdo.getDescuentoPlanaMT());
		tramAPI.setDescuentoPlanaNMAnterior((int)acuerdo.getDescuentoPlanaNM());
		tramAPI.setTipoPlanas(acuerdo.getModalidadConcertada());
		tramAPI.setOperadora(acuerdo.getOperadora());
		tramAPI.setPlanaAutoajustable(acuerdo.getPlanaAutoajustable());
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


	private void getSignature(
			TramitacionBajaForm form
			, HttpServletRequest request
			, Authentication auth) {
		
		String sessionId = request.getSession().getId();
		String userAgent = request.getHeader("user-agent");
		UserAgent ua = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		MarteUsuario marteUsuario = usuarioService.findByUsername(userDetails.getUsername());
		
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** USER      : [%s]", userDetails.toString()));
		logger.info(String.format("** Username  : [%s]", userDetails.getUsername()));
		logger.info(String.format("** Usuario   : [%s]", marteUsuario.toString()));
		logger.info(String.format("***********************************", ""));
		logger.info(String.format("** TramBajaForm       *************", ""));
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
