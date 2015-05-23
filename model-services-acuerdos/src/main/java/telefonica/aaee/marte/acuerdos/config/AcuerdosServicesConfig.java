package telefonica.aaee.marte.acuerdos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.acuerdos.service",
		"telefonica.aaee.marte.acuerdos.model"
		})
public class AcuerdosServicesConfig{ }