package telefonica.aaee.marte.init;

import java.util.Locale;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 
 * Replaces dispatcher-servlet.xml
 * 
 * @author aaeeadmin
 *
 */
@Configuration
@ComponentScan(value = { 
		"telefonica.aaee.dao.config" // project:model-services-977r
		, "telefonica.aaee.dao.maestras" // project:model-services-maestras

		, "telefonica.aaee.segmentacion" // project:process-segmentacion
		, "telefonica.aaee.escenarios" // project:process-escenarios

		, "telefonica.aaee.capture977r.process" // project:process-capture977r
		, "telefonica.aaee.excel.export" // project:process-genera-excel

		, "telefonica.aaee.informes" // project:informes-webapp
		, "telefonica.aaee.marte" // project:informes-altaplanas
		
		, "telefonica.aaee.prefijos" // project:informes-altaplanas

		, "telefonica.aaee.queues" // project:queue-manager
		
		, "telefonica.aaee.dao.operations" // project:informes-altaplanas
		
		, "telefonica.aaee.scheduler.config" // project:job-scheduler
})
@EnableWebMvc
@PropertySource("file:${catalina.home}/conf/marte.properties")
public class InformesConfig extends WebMvcConfigurationSupport {

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource
	private Environment environment;

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	/**
	 * i18n
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {

		LocaleChangeInterceptor result = new LocaleChangeInterceptor();
		result.setParamName("lang");

		return result;

	}

	@Bean
	public LocaleResolver localeResolver() {

		SessionLocaleResolver result = new SessionLocaleResolver();
		result.setDefaultLocale(new Locale("es", "ES"));

		return result;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		return handlerMapping;
	}

	@Bean(name = "mailSender")
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(environment.getProperty("smtp.host"));
		mailSenderImpl.setPort(environment.getProperty("smtp.port",
				Integer.class));
		mailSenderImpl.setProtocol(environment.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(environment.getProperty("smtp.username"));
		mailSenderImpl.setPassword(environment.getProperty("smtp.password"));

		Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", true);
		javaMailProps.put("mail.smtp.starttls.enable", true);

		mailSenderImpl.setJavaMailProperties(javaMailProps);

		return mailSenderImpl;
	}

}