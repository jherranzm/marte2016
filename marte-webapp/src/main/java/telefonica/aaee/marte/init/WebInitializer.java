package telefonica.aaee.marte.init;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import telefonica.aaee.marte.helpers.Constants;

public class WebInitializer implements WebApplicationInitializer {

	protected final Log logger = LogFactory.getLog(getClass());

	public void onStartup(ServletContext servletContext)
			throws ServletException {

		logger.info(Constants.CRLF+"App!");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setServletContext(servletContext);
		ctx.register(
				InformesConfig.class
				, ThymeleafConfig.class
				, StaticConfig.class
				, SecurityConfig.class
				);
		
		// De lo contrario no encuentra un ContextLoaderListener...!!
		servletContext.addListener(new ContextLoaderListener(ctx)); 

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);

		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
		
        logger.info(Constants.CRLF+"***** Encoding filter!");
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

	}

}
