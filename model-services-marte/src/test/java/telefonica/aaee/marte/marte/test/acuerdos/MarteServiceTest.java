package telefonica.aaee.marte.marte.test.acuerdos;

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

import telefonica.aaee.marte.marte.dao.model.Acuerdo;
import telefonica.aaee.marte.marte.dao.service.AcuerdoService;
import telefonica.aaee.marte.marte.test.config.JPAMarteTestConfig;
import telefonica.aaee.marte.marte.test.config.ServicesTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServicesTestConfig.class, JPAMarteTestConfig.class})
@Transactional
public class MarteServiceTest {

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
	
	@Test
	public void testAcuerdosPorTipoAcuerdoActivos() {
		
		boolean ret = false;
		
		Page<Acuerdo> page = acuerdoService.findByTipoAcuerdoActivos("02", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de acuerdos : [%d]", total));
		
		ret = total > 0;
		
		for(Acuerdo acuerdo : page.getContent()){
			logger.info(String.format("Acuerdo : [%s]", acuerdo.toString()));
		}

		assertTrue(ret);
		
	}
	
	@Test
	public void testAcuerdosPorTipoAcuerdoNOActivos() {
		
		boolean ret = false;
		
		Page<Acuerdo> page = acuerdoService.findByTipoAcuerdoNoActivos("02", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de acuerdos : [%d]", total));
		
		ret = total > 0;
		
		for(Acuerdo acuerdo : page.getContent()){
			logger.info(String.format("Acuerdo : [%s]", acuerdo.toString()));
		}
		
		assertTrue(ret);
		
	}
	
	@Test
	public void testAcuerdosPorIDAcuerdo() {
		
		boolean ret = false;
		
		Acuerdo acuerdo = acuerdoService.findByIDAcuerdo("T010028275", 1);
		
		ret = acuerdo!= null;
		assertTrue(ret);
		
		if (ret) {
			logger.info(String.format("Acuerdo : [%s]", acuerdo.toString()));
		}
		
		
	}
	
	@Test
	public void testIDAcuerdoNoExiste() {
		
		boolean ret = false;
		
		Acuerdo acuerdo = acuerdoService.findByIDAcuerdo("T020028275", 1);
		
		ret = (acuerdo == null);
		assertTrue(ret);
		
	}
}
