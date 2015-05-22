package telefonica.aaee.marte.marte.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.marte.model"
		, "telefonica.aaee.marte.marte.service"
		, "telefonica.aaee.marte.marte.specifications"
		})
public class ServicesTestConfig{ }