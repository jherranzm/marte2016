package telefonica.aaee.marte.process;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class MailUtil {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private Environment environment;

	@Autowired
	private JavaMailSender htmlMailSender;

	@Autowired
	@Qualifier("webTierTemplateEngine")
	private SpringTemplateEngine template;
	
}
