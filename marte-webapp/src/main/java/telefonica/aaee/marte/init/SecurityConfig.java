package telefonica.aaee.marte.init;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring4.SpringTemplateEngine;

import telefonica.aaee.dao.maestras.service.UsuarioUserDetailsService;
import telefonica.aaee.marte.process.ConversorUsuario;
import telefonica.aaee.marte.process.MailUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private Environment environment;

	@Autowired
	private UsuarioUserDetailsService uuds;
	
	@Autowired
	private JavaMailSender htmlMailSender;
	
	@Autowired
	@Qualifier("webTierTemplateEngine")
	private SpringTemplateEngine template;
	
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private ConversorUsuario cu;
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		logger.info("-- Configurando usuarios 'JPA'");
		// auth
		// .inMemoryAuthentication().withUser("jherranzm").password("illuminatti").roles("ADMIN");

		auth
		//
		.userDetailsService(uuds).passwordEncoder(passwordEncoder()).and()
				.inMemoryAuthentication().withUser("admin").password("admin")
				.roles("ADMIN", "USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("-- Configurando seguridad : 'ROLE_ADMIN'");

		http
			.authorizeRequests()
				.antMatchers("/findcif/**").access("hasRole('ROLE_USER')")
				.antMatchers("/role/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/usuario/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/consulta/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pestanya/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/informe/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/regladenegocio/**").access("hasRole('ROLE_ADMIN')")
				.and()
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.permitAll();
		http.exceptionHandling().accessDeniedPage("/login?error");
		
		logger.info(String.format("RAND : [%s]",RandomStringUtils.randomAlphabetic(3).toUpperCase()));
		
		cu.conversorUsuarios();
		
		logger.info(String.format("Conversión finalizada!",""));
		
		
	}
	
}