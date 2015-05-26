package telefonica.aaee.marte.acuerdos.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.acuerdos.dao.model"
		, "telefonica.aaee.marte.acuerdos.dao.service"
		, "telefonica.aaee.marte.acuerdos.dao.specifications"
		})
public class AcuerdosServicesTestConfig{ }