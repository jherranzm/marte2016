package telefonica.aaee.marte.init;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration 
public class ThymeleafConfig {

	protected final Log logger = LogFactory.getLog(getClass());
	   
	
	private ClassLoaderTemplateResolver emailTemplateResolver() {
		ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
		emailTemplateResolver.setPrefix("templates/");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setCharacterEncoding("UTF-8");
		emailTemplateResolver.setTemplateMode("HTML5");
		emailTemplateResolver.setOrder(0);
		return emailTemplateResolver;
	}
	
	@Bean 
	public ServletContextTemplateResolver htmlTemplateResolver() {
		ServletContextTemplateResolver htmlTemplateResolver = new ServletContextTemplateResolver();
		htmlTemplateResolver.setPrefix("/WEB-INF/");
		htmlTemplateResolver.setSuffix(".html");
		htmlTemplateResolver.setCharacterEncoding("UTF-8");
		htmlTemplateResolver.setTemplateMode("HTML5");
		htmlTemplateResolver.setOrder(1);
		return htmlTemplateResolver;
	}
	
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        SpringSecurityDialect dialect = new SpringSecurityDialect();
        return dialect;
    }
	
	@Bean(name="webTierTemplateEngine") 
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		Set<TemplateResolver> templateResolvers = new HashSet<TemplateResolver>();
		templateResolvers.add(emailTemplateResolver());
		templateResolvers.add(htmlTemplateResolver());
		engine.setTemplateResolvers(templateResolvers);
		
		//Spring Security
		Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(springSecurityDialect());
        engine.setAdditionalDialects(dialects);
		return engine;
	}
	
    @Bean 
    public ThymeleafViewResolver thymeleafViewResolver() { 
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver(); 
        thymeleafViewResolver.setViewClass(ThymeleafView.class);
        // Importante: todas las views que tengan la forma siguiente se procesan mediante Thymeleaf
        thymeleafViewResolver.setViewNames(new String[]{"html/*"});
        thymeleafViewResolver.setTemplateEngine(templateEngine()); 
        thymeleafViewResolver.setOrder(0);
        thymeleafViewResolver.setCharacterEncoding("UTF-8"); 
        thymeleafViewResolver.setContentType("text/html; charset=UTF-8");
        return thymeleafViewResolver; 
    } 

   	
    @Bean
    public InternalResourceViewResolver jspViewResolver() {
   	    InternalResourceViewResolver resolver  = new InternalResourceViewResolver();

   	    resolver.setPrefix("/WEB-INF/views/");
   	    resolver.setSuffix(".jsp");
   	    resolver.setViewClass(JstlView.class);
   	    resolver.setOrder(1);
   	    return resolver;

    }

}
