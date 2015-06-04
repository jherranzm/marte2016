package telefonica.aaee.marte.marte.config;

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
        entityManagerFactoryRef = "marteEntityManagerFactory", 
        transactionManagerRef = "marteTransactionManager",
        basePackages = {"telefonica.aaee.marte.marte"}
        )
@ComponentScan("telefonica.aaee.marte.marte") //Specifies which package to scan
@PropertySource("file:${catalina.home}/conf/marte.properties")
public class JPAMarteConfig 
	{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String DRIVER = "db.driver";
	private static final String URL = "db.url.marte";
    private static final String USERNAME = "db.username.marte";
    private static final String PASSWORD = "db.password.marte";

	@Resource
    private Environment environment;
	
	
	@Bean(name = "marteDataSource")
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();

	      logger.info(String.format("DRIVER : [%s]", environment.getRequiredProperty(DRIVER)));
	      logger.info(String.format("URL    : [%s]", environment.getRequiredProperty(URL)));
	      logger.info(String.format("USER   : [%s]", environment.getRequiredProperty(USERNAME)));
	      logger.info(String.format("PASS   : [%s]", environment.getRequiredProperty(PASSWORD)));
	      
	      dataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
	      dataSource.setUrl(environment.getRequiredProperty(URL));
	      dataSource.setUsername( environment.getRequiredProperty(USERNAME) );
	      dataSource.setPassword( environment.getRequiredProperty(PASSWORD) );
	      
	      
	      logger.info("***** marteDataSource!");
	      return dataSource;
	   }

    @Bean(name = "marteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() 
    		throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan("telefonica.aaee.marte.marte");
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName(Constantes.JPAMartePU);
        
        JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        logger.info("***** marteEntityManagerFactory!");
        
        return em;
    }
    
    @Bean(name = "marteTransactionManager")
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		logger.info("***** marteTransactionManager!");

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
