package telefonica.aaee.marte.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.model"
		, "telefonica.aaee.marte.service"
		, "telefonica.aaee.marte.specifications"
		})
public class ServicesTestConfig{ }