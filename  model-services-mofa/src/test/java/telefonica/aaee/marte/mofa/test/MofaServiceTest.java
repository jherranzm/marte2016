package telefonica.aaee.marte.mofa.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import telefonica.aaee.marte.mofa.dao.model.MapaBancario;
import telefonica.aaee.marte.mofa.dao.model.MapaBancarioPK;
import telefonica.aaee.marte.mofa.dao.model.Municipio;
import telefonica.aaee.marte.mofa.dao.service.MapaBancarioService;
import telefonica.aaee.marte.mofa.dao.service.MunicipioService;
import telefonica.aaee.marte.mofa.test.config.JPAMofaTestConfig;
import telefonica.aaee.marte.mofa.test.config.MofaServicesTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MofaServicesTestConfig.class, JPAMofaTestConfig.class})
@Transactional
public class MofaServiceTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MunicipioService apunteService;

	@Autowired
	private MapaBancarioService mapaBancarioService;

	@Test
	public void testMunicipio() {

		boolean ret = false;

		List<Municipio> page = apunteService.findAll();
		
		int total = page.size();
		logger.info(String.format("Número de apuntes : [%d]", total));

		ret = total > 0;
		
		for(Municipio acuerdo : page){
			logger.info(String.format("Municipio : [%s]", acuerdo.toString()));
		}

		assertTrue(ret);
		
	}
	
	@Test
	public void testMunicipioByCP() {
		
		boolean ret = false;
		
		Page<Municipio> page = apunteService.findByCP("08031", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de apuntes : [%d]", total));
		
		ret = total > 0;
		
		for(Municipio acuerdo : page.getContent()){
			logger.info(String.format("Municipio : [%s]", acuerdo.toString()));
		}
		
		assertTrue(ret);
		
	}
	
	@Test
	public void testMapaBancario() {

		boolean ret = false;
		
		MapaBancarioPK id = new MapaBancarioPK();
		id.setEntidad("2100");
		id.setOficina("1016");
		

		MapaBancario entidad = mapaBancarioService.findById(id);
		if(entidad == null){
			assertTrue(ret);
		}else{
			ret = true;
			logger.info(String.format("MapaBancario : [%s]", entidad.toString()));
			assertTrue(ret);
		}

		
	}
}
