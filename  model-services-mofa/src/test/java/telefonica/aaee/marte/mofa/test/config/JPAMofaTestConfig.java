package telefonica.aaee.marte.mofa.test.config;

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

@Configuration 
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mofaEntityManagerFactory", 
        transactionManagerRef = "mofaTransactionManager",
        basePackages = {"telefonica.aaee.marte.mofa.dao"}
        )
@ComponentScan("telefonica.aaee.marte.mofa.dao") //Specifies which package to scan
@PropertySource("file:/usr/local/apache-tomcat-7.0.57/conf/application.properties")
//@PropertySource("file:/usr/share/tomcat-7.0.53/conf/application.properties")
public class JPAMofaTestConfig {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String DRIVER = "db.driver";
	private static final String URL = "db.url.marte.mofa";
    private static final String USERNAME = "db.username.marte.mofa";
    private static final String PASSWORD = "db.password.marte.mofa";

	@Resource
    private Environment environment;
	
	
	@Bean(name = "mofaDataSource")
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
	      dataSource.setUrl(environment.getRequiredProperty(URL));
	      dataSource.setUsername( environment.getRequiredProperty(USERNAME) );
	      dataSource.setPassword( environment.getRequiredProperty(PASSWORD) );
	      
	      return dataSource;
	   }

    @Bean(name = "mofaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() 
    		throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan(
        		"telefonica.aaee.marte.mofa.dao.model"
        		, "telefonica.aaee.marte.mofa.dao.service"
        		, "telefonica.aaee.marte.mofa.dao.specifications"
        		);
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName("JPAMofaPU");
        
        JpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
        logger.info("***** EntityManagerFactory!");
        
        return em;
    }
    
    @Bean(name = "TransactionManager")
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

		logger.info("***** TransactionManager!");

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
