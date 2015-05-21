package telefonica.aaee.marte.test.acuerdos;

import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import telefonica.aaee.marte.model.Acuerdo;
import telefonica.aaee.marte.service.AcuerdoService;
import telefonica.aaee.marte.test.config.JPAAcuerdosTestConfig;
import telefonica.aaee.marte.test.config.ServicesTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServicesTestConfig.class, JPAAcuerdosTestConfig.class})
@Transactional
public class AcuerdoServiceTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AcuerdoService acuerdoService;

	@Test
	public void testAcuerdos() {

		boolean ret = false;

		Page<Acuerdo> page = acuerdoService.findByCif("A08511149", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de acuerdos : [%d]", total));

		ret = total > 0;
		
		for(Acuerdo acuerdo : page.getContent()){
			logger.info(String.format("Acuerdo : [%s]", acuerdo.toString()));
		}

		assertTrue(ret);
		
	}
	@Test
	public void testAcuerdosPorCifSupraQueEstenActivos() {
		
		boolean ret = false;
		
		Page<Acuerdo> page = acuerdoService.findByCifActivos("A08511149", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de acuerdos : [%d]", total));
		
		ret = total > 0;
		
		for(Acuerdo acuerdo : page.getContent()){
			logger.info(String.format("Acuerdo : [%s]", acuerdo.toString()));
		}

		assertTrue(ret);
		
	}
}
