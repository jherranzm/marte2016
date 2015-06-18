package telefonica.aaee.marte.mofa.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.mofa.dao.service"
		, "telefonica.aaee.marte.mofa.dao.specitifactions"
		, "telefonica.aaee.marte.mofa.dao.vo"
		, "telefonica.aaee.marte.mofa.dao.model"
		})
public class MofaServicesTestConfig{ }