package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
import telefonica.aaee.marte.form.TramitacionForm;
import telefonica.aaee.marte.form.TramitacionModCCCForm;
import telefonica.aaee.marte.marte.model.FacturaPagaLibroFacturacion;
import telefonica.aaee.marte.marte.vo.CuentaCorriente;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import telefonica.aaee.util.Constantes;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/tram/modccc")
public class TramModCCCController extends BasicController {
	
	private HashMap<CuentaCorriente, Date> cuentas = new HashMap<CuentaCorriente, Date>();

	private static final String TRAM_MOD_CCC_FORM = "html/findcif/tram-mod-ccc-form";
	private static final String TRAM_MOD_CCC_CONF = "html/findcif/tram-mod-ccc-conf";

	protected final Log logger = LogFactory.getLog(getClass());
	
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


	@RequestMapping(value="/form/{idAcuerdo}", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showFormModCCCAcuerdo(
			@ModelAttribute TramitacionModCCCForm form,
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
		modelAndView.setViewName(TRAM_MOD_CCC_FORM);
		
		getCCCPorAcuerdoConcertada(acuerdo);
		
		modelAndView.addObject("cuentas", cuentas.keySet());
		
		if(form == null){
			form = new TramitacionModCCCForm();
			form.setIdAcuerdo(acuerdo.getIDAcuerdo());
		}
		logger.info(String.format("[%s]", form.toString()));
		modelAndView.addObject("tramModCCCForm", form);
		
		addSituacionPlanaToMAV(modelAndView, acuerdo);
	
		return modelAndView; 
	}


	@RequestMapping(value="/form", method=RequestMethod.POST)
	public ModelAndView tramModCCCAcuerdoForm(
			@ModelAttribute TramitacionModCCCForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		ModelAndView modelAndView = new ModelAndView();

		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		logger.info(String.format("[%s]", acuerdo.toString()));
		
		getCCCPorAcuerdoConcertada(acuerdo);
		
		modelAndView.addObject("cuentas", cuentas.keySet());

		modelAndView.setViewName(TRAM_MOD_CCC_FORM);
		modelAndView.addObject("tramModCCCForm", form);
		modelAndView.addObject("acuerdo", acuerdo);
		return modelAndView;
	}


	@RequestMapping(value="/conf", method=RequestMethod.POST)
	public ModelAndView tramModCCCAcuerdoConf(
			@ModelAttribute TramitacionModCCCForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		ModelAndView modelAndView = new ModelAndView();

		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		logger.info(String.format("[%s]", acuerdo.toString()));
		
		getCCCPorAcuerdoConcertada(acuerdo);
		
		modelAndView.addObject("cuentas", cuentas.keySet());
		
		modelAndView.setViewName(TRAM_MOD_CCC_CONF);
		modelAndView.addObject("tramModCCCForm", form);
		modelAndView.addObject("acuerdo", acuerdo);
		return modelAndView;
	}


	@RequestMapping(value="/ok", method=RequestMethod.POST)
	public ModelAndView tramModCCCAcuerdoConfirmada(
			@ModelAttribute TramitacionModCCCForm form,
			HttpServletRequest request,  
			final RedirectAttributes redirectAttributes, 
			Authentication auth,
			Locale locale
			) {
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
	
		getSignature(form, request, auth);
		
		form.setPeticionTramitacion(getCorrectEncoding(form.getPeticionTramitacion()));
		
		TramitacionAPI tramAPI = new TramitacionAPI();
		CodAPI codAPI = codAPIService.findById("Otros");
		tramAPI.setCodAPI(codAPI);
		tramAPI.setCodAPIOrig(codAPI);
		
		Date ahora = Calendar.getInstance().getTime();
		tramSetFechas(tramAPI, ahora);
		
		Acuerdo acuerdo = acuerdoService.findById(form.getIdAcuerdo());
		copyAcuerdoToTram(tramAPI, acuerdo);
		
		tramAPI.setObservaciones("");
		tramAPI.setSoporteAnterior("");
		tramAPI.setDireccionAnterior("");
		tramAPI.setCcc("");
		tramAPI.setCccAnteriror("");
		tramAPI.setAutorizacion("");
		tramAPI.setBajaCliente("False");
		tramAPI.setTramitar("False");
		tramAPI.setCargaGAE("False");
		tramAPI.setEnvioCorreo("S");
		
		tramSetRdV(tramAPI, acuerdo);

		tramAPI.setEmail("");
		tramAPI.setTipoSoporte("05");
		
		MotivoBaja motivoBaja = motivoBajaService.findById(99L);
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
	
		String ccc = (form.getBanco() == null ? "" : form.getBanco()) +
				(form.getSucursal() == null ? "" : form.getSucursal()) +
				(form.getDigitoControl() == null ? "" : form.getDigitoControl()) +
				(form.getNumeroCuenta() == null ? "" : form.getNumeroCuenta())
		;
		tramAPI.setCcc(ccc);
	
		
		StringBuffer sbPeticionTramitacion = new StringBuffer();
		sbPeticionTramitacion
			.append("Cambio Cuenta Bancaria.").append(Constantes.CRLF)
			.append("CCC:").append(ccc).append(Constantes.CRLF)
			.append("================================").append(Constantes.CRLF)
			.append(Constantes.CRLF);		
		if(!"".equals(form.getBanco())){
			sbPeticionTramitacion
				.append("Banco:").append(form.getBanco()).append(Constantes.CRLF);
		}
		if(!"".equals(form.getSucursal())){
			sbPeticionTramitacion
			.append("Sucursal:").append(form.getSucursal()).append(Constantes.CRLF);
		}
		if(!"".equals(form.getDigitoControl())){
			sbPeticionTramitacion
			.append("DC:").append(form.getDigitoControl()).append(Constantes.CRLF);
		}
		if(!"".equals(form.getNumeroCuenta())){
			sbPeticionTramitacion
			.append("Número de Cuenta:").append(form.getNumeroCuenta()).append(Constantes.CRLF);
		}
		sbPeticionTramitacion
		.append("CCC:").append(ccc).append(Constantes.CRLF);
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


	private void getCCCPorAcuerdoConcertada(Acuerdo acuerdo) {
		Page<FacturaPagaLibroFacturacion> paginaFacturas = facturasService.findByAcuerdoConcertada(acuerdo.getAcuerdoFX(), 1);
		List<FacturaPagaLibroFacturacion> facturas = paginaFacturas.getContent();
		for(FacturaPagaLibroFacturacion factura : facturas){
			logger.info(String.format("%s", factura));
			CuentaCorriente cuenta = new CuentaCorriente(factura);
			if(!cuentas.containsKey(cuenta)){
				cuentas.put(cuenta, factura.getFechaEmision());
			}
		}
		for(CuentaCorriente key : cuentas.keySet()){
			logger.info(String.format("%tY/%tm/%td : %s "
					, cuentas.get(key)
					, cuentas.get(key)
					, cuentas.get(key)
					, key));
		}
	}

}
