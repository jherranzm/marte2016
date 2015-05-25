package telefonica.aaee.marte.acuerdos.test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.service.TramitacionAPIService;
import telefonica.aaee.marte.acuerdos.dao.vo.YearMonthEstatusVO;
import telefonica.aaee.marte.acuerdos.test.config.JPAAcuerdosTestConfig;
import telefonica.aaee.marte.acuerdos.test.config.ServicesTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServicesTestConfig.class, JPAAcuerdosTestConfig.class})
@Transactional
public class TramitacionAPITest {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private TramitacionAPIService tramitacionAPIService;

	@Test
	public void testAcuerdosFindByCif() {

		boolean ret = false;

		Page<TramitacionAPI> page = tramitacionAPIService.findByCif("P4508800B", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de elementos en la página : [%d]", total));
		logger.info(String.format("Número de páginas                : [%d]", page.getTotalPages()));
		logger.info(String.format("Número de elementos totales      : [%d]", page.getTotalElements()));

		ret = total > 0;
		
		for(TramitacionAPI tramitacion : page.getContent()){
			logger.info(String.format("TramitacionAPI : [%s]", tramitacion.toString()));
		}

		assertTrue(ret);
		
	}

	@Test
	public void testAcuerdosFindByIDAcuerdo() {
		
		boolean ret = false;
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByIDAcuerdo("T000045850", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de elementos en la página : [%d]", total));
		logger.info(String.format("Número de páginas                : [%d]", page.getTotalPages()));
		logger.info(String.format("Número de elementos totales      : [%d]", page.getTotalElements()));
		
		ret = total > 0;
		
		for(TramitacionAPI tramitacion : page.getContent()){
			logger.info(String.format("TramitacionAPI : [%s]", tramitacion.toString()));
		}
		
		assertTrue(ret);
		
	}
	
	
	@Test
	public void testAcuerdosFindByCodAPIBaja() {
		
		boolean ret = false;
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByCodAPI("Baja", 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de elementos en la página : [%d]", total));
		logger.info(String.format("Número de páginas                : [%d]", page.getTotalPages()));
		logger.info(String.format("Número de elementos totales      : [%d]", page.getTotalElements()));
		
		ret = total > 0;
		
//		for(TramitacionAPI tramitacion : page.getContent()){
//			logger.info(String.format("TramitacionAPI : [%s]", tramitacion.toString()));
//		}
		
		assertTrue(ret);
		
	}
	
	
	
	@Test
	public void testAcuerdosFindByEstadoTramRechazada() {
		
		boolean ret = false;
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByEstadoTram(2, 1);
		
		int total = page.getContent().size();
		logger.info(String.format("Número de elementos en la página : [%d]", total));
		logger.info(String.format("Número de páginas                : [%d]", page.getTotalPages()));
		logger.info(String.format("Número de elementos totales      : [%d]", page.getTotalElements()));
		
		ret = total > 0;
		
		for(TramitacionAPI tramitacion : page.getContent()){
			logger.info(String.format("TramitacionAPI : [%s]", tramitacion.toString()));
		}
		
		assertTrue(ret);
		
	}
	
	@Test
	public void testGroupBy(){
		boolean ret = false;
		
		List<Tuple> tupleResult = tramitacionAPIService.groupByEstadoTram();
		
		ret = (tupleResult.size() > 0);
		
		for (Tuple t : tupleResult) {
			Integer id = (Integer) t.get(0);
			Long num = (Long) t.get(1);
		    logger.info(String.format("EstadoTram : [%d] : [%d]", id, num));
		}
		
		assertTrue(ret);
		
		
	}
	
	
	@Test
	public void testGroupByFechaTramitacionPrevista(){
		boolean ret = false;
		
		List<YearMonthEstatusVO> tupleResult = tramitacionAPIService.groupByMesTramitacionPrevista();
		
		ret = (tupleResult.size() > 0);
		
		for(YearMonthEstatusVO res : tupleResult){
		    logger.info(String.format("Estadísticas : [%s]", res.toString()));
		}

		assertTrue(ret);
		
		
	}
	
	@Test
	public void testGroupByFechaTramitacionPrevistaFiltradoPorFecha(){
		boolean ret = false;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
//		cal.set(Calendar.MONTH, 4); //Mayo! 0,Enero; 1, Febrero
		cal.set(Calendar.MONTH, 3); //Mayo! 0,Enero; 1, Febrero
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date fechaInicio = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date fechaFin = cal.getTime();

		
		List<YearMonthEstatusVO> tupleResult = tramitacionAPIService.groupByMesTramitacionPrevista(
				fechaInicio
				, fechaFin);
		
		ret = (tupleResult.size() > 0);
		
		for(YearMonthEstatusVO res : tupleResult){
			logger.info(String.format("Estadísticas : [%s]", res.toString()));
		}
		
		assertTrue(ret);
		
		
	}
	
	
	@Test
	public void testTramitacionPrevista(){
		boolean ret = false;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
//		cal.set(Calendar.MONTH, 4); //Mayo! 0,Enero; 1, Febrero
		cal.set(Calendar.MONTH, 3); //Mayo! 0,Enero; 1, Febrero
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date fechaInicio = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date fechaFin = cal.getTime();
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByFechaTramitacionPrevista(
				fechaInicio, fechaFin, 1);
		
		ret = (page.getContent().size() > 0);
		
		logger.info(String.format("Número de elementos en la página : [%d]", page.getContent().size()));
		logger.info(String.format("Número de páginas                : [%d]", page.getTotalPages()));
		logger.info(String.format("Número de elementos totales      : [%d]", page.getTotalElements()));

		assertTrue(ret);
		
	}
	
	
	
	@Test
	public void getPeticionesPorTipoPorAnioYMes(){
		boolean ret = false;
		
		List<Tuple> page = tramitacionAPIService.getPeticionesPorTipoPorAnioYMes();
		
		ret = (page.size() > 0);
		
//		for (Tuple t : page) {
//			for(int k = 0; k < t.getElements().size(); k++){
//				logger.info(String.format("T : [%d]"
//						, t.get(k)
//						));
//			}
//		}
		
		assertTrue(ret);
		
	}
	
}
