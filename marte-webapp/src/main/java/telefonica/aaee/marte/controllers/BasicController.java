package telefonica.aaee.marte.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public abstract class BasicController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	protected Environment environment;

	@Resource
	protected MessageSource messageSource;

	
	
	protected Integer numItemsPorPagina = 10;

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

	@RequestMapping(value = "/list.html")
	public ModelAndView listAll() {
		return getMAVFromQueBuscar("", 1, numItemsPorPagina);
	}

	
}
