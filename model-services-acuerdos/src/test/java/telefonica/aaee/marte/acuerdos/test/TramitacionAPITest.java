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

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.model.EstadoTramitacion;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.service.AcuerdoService;
import telefonica.aaee.marte.acuerdos.dao.service.CodAPIService;
import telefonica.aaee.marte.acuerdos.dao.service.EstadoTramitacionService;
import telefonica.aaee.marte.acuerdos.dao.service.MarteUsuarioService;
import telefonica.aaee.marte.acuerdos.dao.service.MotivoBajaService;
import telefonica.aaee.marte.acuerdos.dao.service.TramitacionAPIService;
import telefonica.aaee.marte.acuerdos.dao.vo.YearMonthEstatusVO;
import telefonica.aaee.marte.acuerdos.test.config.AcuerdosServicesTestConfig;
import telefonica.aaee.marte.acuerdos.test.config.JPAAcuerdosTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
		AcuerdosServicesTestConfig.class
		, JPAAcuerdosTestConfig.class
		})
@Transactional
public class TramitacionAPITest {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private MarteUsuarioService usuarioService;

	@Autowired
	private CodAPIService codAPIService;
	
	@Autowired
	private TramitacionAPIService tramitacionAPIService;
	
	@Autowired
	private AcuerdoService acuerdoService;
	
	@Autowired
	private EstadoTramitacionService estadoTramitacionService;
	
	@Autowired
	private MotivoBajaService motivoBajaService;
	
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
		
		Acuerdo acuerdo = acuerdoService.findByIDAcuerdo("T000045850");
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByIDAcuerdo(acuerdo, 1);
		
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
		
		EstadoTramitacion et = estadoTramitacionService.findById((short)2);
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByEstadoTram(et, 1);
		
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
			Long id = (Long) t.get(0);
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
		
		assertTrue(ret);
		
	}

	@Test
	public void testAcuerdosBajaTramitada() {
		
		boolean ret = false;
		
		EstadoTramitacion et = estadoTramitacionService.findById((short)1);
		String tipoPeticion = "Baja";
		
		Page<TramitacionAPI> page = tramitacionAPIService.findByTipoPeticionEstadoTram(tipoPeticion, et, 1);
		
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
	public void testSave(){
		
		logger.info("testSave!");
		
		TramitacionAPI tramAPI = new TramitacionAPI();
		
		Calendar cal = Calendar.getInstance();
		Date fechaPeticion = cal.getTime();

		cal.set(Calendar.YEAR, 2500);
		cal.set(Calendar.MONTH, 11); //Mayo! 0,Enero; 1, Febrero
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		Date fechaTramitacionPrevista = cal.getTime();
		
		tramAPI.setFechaPeticion(fechaPeticion);
		tramAPI.setFechaTramPrevista(fechaTramitacionPrevista);
		tramAPI.setFechaTramAPI(fechaTramitacionPrevista);
		tramAPI.setFechaGAE(fechaTramitacionPrevista);
		tramAPI.setFechaCorreo(fechaTramitacionPrevista);
		tramAPI.setFinVigencia(fechaTramitacionPrevista);
		
//		Acuerdo acuerdo = acuerdoService.findById("T010002319");
//		
//		// Información proveniente de ACUERDO
//		tramAPI.setTipoDoc(acuerdo.getTipoDoc());
//		tramAPI.setAcuerdoNumero(acuerdo.getAcuerdoNumero());
//		tramAPI.setCif(acuerdo.getCif());
//		tramAPI.setPlanaAutoajustable(acuerdo.getPlanaAutoajustable());
//		tramAPI.setTipoPlanas(acuerdo.getModalidadConcertada());
		
		logger.info(String.format("TramitacionAPI : [%s]", tramAPI.toString()));
		
		TramitacionAPI retTramAPI = tramitacionAPIService.save(tramAPI);
		logger.info(String.format("TramitacionAPI : [%s]", retTramAPI.toString()));
		
		assertTrue(retTramAPI.getId() > 0);
	}
}
