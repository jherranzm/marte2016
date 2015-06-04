/**
 * 
 */
package telefonica.aaee.marte.acuerdos.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;
import telefonica.aaee.marte.acuerdos.dao.service.SituacionPlanaEstadoService;
import telefonica.aaee.marte.acuerdos.test.config.AcuerdosServicesTestConfig;
import telefonica.aaee.marte.acuerdos.test.config.JPAAcuerdosTestConfig;

/**
 * @author jherranzm
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
		AcuerdosServicesTestConfig.class
		, JPAAcuerdosTestConfig.class
		})
@Transactional
public class SituacionPlanaEstadoServiceTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	SituacionPlanaEstadoService situacionPlanaEstadoService;
	
	/**
	 * Test method for {@link telefonica.aaee.marte.acuerdos.dao.service.SituacionPlanaEstadoService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<SituacionPlanaEstado> lspe = situacionPlanaEstadoService.findAll();
		assertTrue(lspe.size() > 0);
		for(SituacionPlanaEstado spe : lspe){
			logger.info(spe);
		}
	}

}
