package telefonica.aaee.marte.marte.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.marte.service",
		"telefonica.aaee.marte.marte.model"
		})
public class MarteServicesConfig{ }