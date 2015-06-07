package telefonica.aaee.marte.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.bitwalker.useragentutils.UserAgent;


@Controller
@RequestMapping("/")
public class MainController extends BasicController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String pageLogin(Model model) {
         return "html/login";
    }
	
	@RequestMapping(value="/index")
	public ModelAndView home(
			HttpServletRequest req
			) {
		
		logger.info("/index");
		
		String userAgent = req.getHeader("user-agent");
		
		logger.info("UserAgent: " + userAgent);
		
		UserAgent ua = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));		
		
		logger.info("Browser: " + ua.getBrowser());
		logger.info("MajorVersion: " + ua.getBrowserVersion().getMajorVersion());
		logger.info("MinorVersion: " + ua.getBrowserVersion().getMinorVersion());
		
		
		ModelAndView model2 = new ModelAndView();
		model2.setViewName("html/index");
		
		return model2;
	}

	public ModelAndView resolveException(
			HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			Object arg2, 
			Exception exception) {
	
		return new ModelAndView("/404");
	}

	@Override
	protected ModelAndView getMAVFromQueBuscar(String queBuscar,
			Integer pageNumber, Integer numItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	protected ModelAndView searchGet(@RequestParam(value = "queBuscar", required = false) String queBuscar, @RequestParam(value = "page", required = false) Integer pageNumber, @RequestParam(value = "size", required = false) Integer numItems,
			final RedirectAttributes redirectAttributes, Locale locale, Pageable pageable) {
				
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	protected ModelAndView searchPost(@RequestParam(value = "queBuscar", required = false) String queBuscar, @RequestParam(value = "page", required = false) Integer pageNumber, @RequestParam(value = "size", required = false) Integer numItems,
			final RedirectAttributes redirectAttributes, Locale locale, Pageable pageable) {
				
				ModelAndView modelAndView = getMAVFromQueBuscar(queBuscar, pageNumber,
						numItems);
				
				return modelAndView;
			}

}
