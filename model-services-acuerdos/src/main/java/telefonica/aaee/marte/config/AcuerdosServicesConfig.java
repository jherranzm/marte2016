package telefonica.aaee.marte.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.repository",
		"telefonica.aaee.marte.service",
		"telefonica.aaee.marte.model"
		})
public class AcuerdosServicesConfig{ }