package telefonica.aaee.marte.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import telefonica.aaee.marte.mofa.dao.model.Municipio;

@Controller
@RequestMapping("/municipios")
public class MunicipioController  extends BasicController{

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/get/{cp}")
	public @ResponseBody List<Municipio> getMunicipiosByCP(
			@PathVariable String cp) {
		
		List<Municipio> lista = new ArrayList<Municipio>();
		if(cp == null && "".equals(cp)){
		}else{
			logger.info(String.format("Recibido [%s]", cp));
			Page<Municipio> page = municipioService.findByCP(cp, 1);
			logger.info(String.format("Localizados [%d] municipios!", page.getContent().size()));
			lista.addAll(page.getContent());
		}
		return lista;
	}

	@Override
	protected ModelAndView getMAVFromQueBuscar(String queBuscar,
			Integer pageNumber, Integer numItems) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
