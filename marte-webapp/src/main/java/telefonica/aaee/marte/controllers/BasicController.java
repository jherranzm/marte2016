package telefonica.aaee.marte.controllers;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import telefonica.aaee.dao.maestras.model.Cliente;
import telefonica.aaee.dao.maestras.model.VCliente;
import telefonica.aaee.dao.maestras.service.ClienteService;
import telefonica.aaee.dao.maestras.service.VClienteService;
import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.service.AcuerdoService;
import telefonica.aaee.marte.acuerdos.dao.service.CodAPIService;
import telefonica.aaee.marte.acuerdos.dao.service.EstadoTramitacionService;
import telefonica.aaee.marte.acuerdos.dao.service.MarteUsuarioService;
import telefonica.aaee.marte.acuerdos.dao.service.MotivoBajaService;
import telefonica.aaee.marte.acuerdos.dao.service.SituacionPlanaService;
import telefonica.aaee.marte.acuerdos.dao.service.TramitacionAPIService;
import telefonica.aaee.marte.helpers.CalculoFechas;
import telefonica.aaee.marte.marte.dao.service.AjustePlanaService;
import telefonica.aaee.util.Constantes;

public abstract class BasicController {

	protected static final String UTF_8 = "UTF-8";
	protected static final String ISO_8859_1 = "ISO-8859-1";
	protected static final String SHOW_ACUERDO_TRAM = "html/findcif/show-acuerdo-tram";
	protected static final String FIND_CIF_FORM = "html/findcif/findCif-form";
	protected static final String RESULT_PAGE = "html/findcif/findCif-list";

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	protected Environment environment;

	@Resource
	protected MessageSource messageSource;

	protected Integer numItemsPorPagina = 10;

	protected final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@Autowired
	protected AcuerdoService acuerdoService;

	@Autowired
	protected TramitacionAPIService tramitacionAPIService;

	@Autowired
	protected MotivoBajaService motivoBajaService;

	@Autowired
	protected SituacionPlanaService situacionPlanaService;

	@Autowired
	protected CodAPIService codAPIService;

	@Autowired
	protected ClienteService clienteService;

	@Autowired
	protected VClienteService vClienteService;

	@Autowired
	protected EstadoTramitacionService estadoTramitacionService;

	@Autowired
	protected AjustePlanaService ajustePlanaService;
	@Autowired
	protected MarteUsuarioService marteUsuarioService;

	@RequestMapping(value = "*", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView defaultMethod(HttpServletRequest request) {
		logger.info("--- Alerta! Intento de entrada directo...");
		logger.info("--- Inicio datos");
		logger.info(String.format("Local IP    : [%s]", request.getLocalAddr()));
		logger.info(String.format("Local Port  : [%s]", request.getLocalPort()));
		logger.info(String.format("Remote IP   : [%s]", request.getRemoteAddr()));
		logger.info(String.format("Remote Port : [%s]", request.getRemotePort()));
		logger.info(String.format("User  : [%s]", request.getRemoteUser()));
		logger.info(String.format("Query : [%s]", request.getQueryString()));
		logger.info("--- Fin datos");
		return new ModelAndView("/404");
	}


	public Integer getNumItemsPorPagina() {
		return numItemsPorPagina;
	}

	public void setNumItemsPorPagina(Integer numItemsPorPagina) {
		this.numItemsPorPagina = numItemsPorPagina;
	}
	
	

	protected abstract ModelAndView getMAVFromQueBuscar(String queBuscar, Integer pageNumber, Integer numItems);

	@RequestMapping(value = "/pages/{id}", method = RequestMethod.GET)
	public ModelAndView pagesByNumPage(@PathVariable Integer id) {
		return getMAVFromQueBuscar("", id, numItemsPorPagina);
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public ModelAndView pagesNoNumPage() {
		return getMAVFromQueBuscar("", 1, numItemsPorPagina);
	}

	protected String getCorrectEncoding(String str) {
		String ret = "--- ERROR ---";
		
		try {
			ret = new String(str.getBytes(ISO_8859_1), UTF_8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}


	protected void copyAcuerdoToTram(TramitacionAPI tramAPI, Acuerdo acuerdo) {
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


	protected void tramSetFechas(TramitacionAPI tramAPI, Date ahora) {
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
	}


	protected StringBuilder getDatosSession(
			final HttpServletRequest request
			, final Date ahora
			, final MarteUsuario marteUsuario) {
		StringBuilder datosSession = new StringBuilder();
		datosSession
			.append(Constantes.CRLF).append(Constantes.CRLF).append(Constantes.CRLF)
			.append("Usuario:")
				.append(marteUsuario.getCodUsuario())
				.append(" : ").append(marteUsuario.getNombre())
				.append(" ").append(marteUsuario.getApellido1())
				.append(Constantes.CRLF)
			.append("IP:[").append(request.getRemoteAddr()).append("]")
				.append(Constantes.CRLF)
			.append("Host:[").append(request.getRemoteHost()).append("]")
				.append(":")
				.append("[").append(request.getRemotePort()).append("]")
				.append(Constantes.CRLF)
			.append("SessionId:").append(request.getRequestedSessionId())
				.append(Constantes.CRLF)
			.append(sdf.format( ahora ))
				.append(Constantes.CRLF)
			.append("====================")
				.append(Constantes.CRLF);
		return datosSession;
	}


	protected void tramSetRdV(TramitacionAPI tramAPI, Acuerdo acuerdo) {
		// RdV
		Cliente cli = clienteService.findByCif(acuerdo.getCif());
		if(cli == null){
			tramAPI.setMatComercial("");
			tramAPI.setMatJArea("");
		}else{
			VCliente cliente = vClienteService.findById(cli.getId());
			tramAPI.setMatComercial(cliente.getMatVendedor());
			tramAPI.setMatJArea(cliente.getMatJArea());
		}
	}

	
}
