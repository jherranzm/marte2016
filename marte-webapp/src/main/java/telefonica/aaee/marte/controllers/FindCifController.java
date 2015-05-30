package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import telefonica.aaee.marte.marte.dao.model.Acuerdo;
import telefonica.aaee.marte.marte.dao.service.AcuerdoService;
import telefonica.aaee.marte.model.pagination.PageWrapper;
import eu.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/findcif")
public class FindCifController extends BasicController {

	private static final String ACUERDO_SHOW = "html/findcif/acuerdo-show";
	private static final String FIND_CIF_FORM = "html/findcif/findCif-form";
	private static final String RESULT_PAGE = "html/findcif/findCif-list";

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private AcuerdoService acuerdoService;
	
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
//			@RequestParam String idAcuerdo
			@PathVariable String idAcuerdo
			) {

		List<String> errores = new ArrayList<String>();
		idAcuerdo = (idAcuerdo == null) ? "" : idAcuerdo;

		ModelAndView modelAndView = new ModelAndView();
		if("".equals(idAcuerdo)){
			errores.add("El Acuerdo viene sin informar.");
			modelAndView.setViewName(FIND_CIF_FORM);
			return modelAndView;
		}
		Acuerdo acuerdo = acuerdoService.findById(idAcuerdo);
		if(acuerdo == null){
			errores.add("El Acuerdo NO existe en el sistema.");
			modelAndView.setViewName(FIND_CIF_FORM);
			return modelAndView;
		}
		modelAndView.addObject("acuerdo", acuerdo);
		modelAndView.setViewName(ACUERDO_SHOW);
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
