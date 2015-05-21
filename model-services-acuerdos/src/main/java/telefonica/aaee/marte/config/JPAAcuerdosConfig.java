package telefonica.aaee.marte.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import telefonica.aaee.util.Constantes;

@Configuration //Specifies the class as configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "acuerdosEntityManagerFactory", 
        transactionManagerRef = "acuerdosTransactionManager",
        basePackages = {"telefonica.aaee.dao.acuerdos"}
        )
@ComponentScan("telefonica.aaee.dao.acuerdos") //Specifies which package to scan
@PropertySource("file:${catalina.home}/conf/application.properties")
public class JPAAcuerdosConfig 
	{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String DRIVER = "db.driver";
	private static final String URL = "db.url.acuerdos";
    private static final String USERNAME = "db.username.acuerdos";
    private static final String PASSWORD = "db.password.acuerdos";

	@Resource
    private Environment environment;
	
	
	@Bean(name = "acuerdosDataSource")
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
	      dataSource.setUrl(environment.getRequiredProperty(URL));
	      dataSource.setUsername( environment.getRequiredProperty(USERNAME) );
	      dataSource.setPassword( environment.getRequiredProperty(PASSWORD) );
	      
	      
	      logger.info("***** acuerdosDataSource!");
	      return dataSource;
	   }

    @Bean(name = "acuerdosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() 
    		throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan("telefonica.aaee.dao.acuerdos");
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName(Constantes.JPAAcuerdosPU);
        
        JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        logger.info("***** acuerdosEntityManagerFactory!");
        
        return em;
    }
    
    @Bean(name = "acuerdosTransactionManager")
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		logger.info("***** acuerdosTransactionManager!");

        return transactionManager;
    }


    Properties additionalProperties() {
        return new Properties() {
			private static final long serialVersionUID = 1L;

		{  // Hibernate Specific: 
              setProperty("eclipselink.weaving", "false");
           }
        };
     }
}
