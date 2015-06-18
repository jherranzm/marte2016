package telefonica.aaee.marte.mofa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value ={
		"telefonica.aaee.marte.marte.dao.service"
		, "telefonica.aaee.marte.marte.dao.specitifactions"
		, "telefonica.aaee.marte.marte.dao.vo"
		, "telefonica.aaee.marte.marte.dao.model"
		})
public class MofaServicesConfig{ }