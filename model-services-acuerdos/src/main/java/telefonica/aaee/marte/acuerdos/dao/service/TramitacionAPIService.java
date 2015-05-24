package telefonica.aaee.marte.acuerdos.dao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import telefonica.aaee.marte.acuerdos.dao.model.CodAPI;
import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.model.YearMonthEstatusVO;
import telefonica.aaee.marte.acuerdos.dao.specifications.TramitacionAPISpecifications;

@Service
public class TramitacionAPIService extends GenericAcuerdosService {

	private static final String ESTADO_TRAM = "estadoTram";
	private static final String FECHA_TRAM_PREVISTA = "fechaTramPrevista";

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CodAPIService codAPIService;

	private SimpleJpaRepository<TramitacionAPI, Long> repo;

	public TramitacionAPIService() {
	}

	public TramitacionAPIService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<TramitacionAPI, Long> entityInfo = new 
				JpaMetamodelEntityInformation<TramitacionAPI, Long>(
				TramitacionAPI.class, em.getMetamodel());
		repo = new SimpleJpaRepository<TramitacionAPI, Long>(entityInfo, em);

	}
	
	
	public List<TramitacionAPI> findAll(){
		return repo.findAll();
	}
	
	public Iterable<TramitacionAPI> findAll(PageRequest page) {
		return repo.findAll(page);
	}

	public TramitacionAPI findById(Long id) {
		return repo.findOne(id);
	}
	
	public Page<TramitacionAPI> findByCif(String cif, Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByCif(cif), request);
	}

	public Page<TramitacionAPI> findByIDAcuerdo(String idAcuerdo, Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
				);
		return repo.findAll(TramitacionAPISpecifications.searchByIDAcuerdo(idAcuerdo), request);
	}
	
	public Page<TramitacionAPI> findByCodAPI(String codAPI, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByCodAPI(codAPI), request);
	}

	public Page<TramitacionAPI> findByFechaTramitacionPrevista(Date fechaTramitacionPrevista, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByFechaTramitacionPrevista(fechaTramitacionPrevista), request);
	}
	
	public Page<TramitacionAPI> findByFechaTramitacionPrevista(
				Date inicioPeriodo
				, Date finPeriodo
				, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
				);
		return repo.findAll(TramitacionAPISpecifications
				.searchByFechaTramitacionPrevista(inicioPeriodo, finPeriodo), request);
	}

	public Page<TramitacionAPI> findByEstadoTram(int estadoTram, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByEstadoTram(estadoTram), request);
	}
	
	public List<Tuple> groupByEstadoTram(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = builder.createTupleQuery();
		Root<TramitacionAPI> root = cq.from(TramitacionAPI.class);
		
		cq.multiselect(root.get(ESTADO_TRAM), builder.count(root.get("id")));  //using metamodel
		cq.groupBy(root.get(ESTADO_TRAM));
		List<Tuple> tupleResult = em.createQuery(cq).getResultList();
		for (Tuple t : tupleResult) {
			Integer id = (Integer) t.get(0);
			Long num = (Long) t.get(1);
		    logger.info(String.format("EstadoTram : [%d] : [%d]", id, num));
		}
		return tupleResult;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<YearMonthEstatusVO> groupByMesTramitacionPrevista(){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<YearMonthEstatusVO> cq = builder.createQuery(YearMonthEstatusVO.class);
		Root<TramitacionAPI> root = cq.from(TramitacionAPI.class);
		
		Expression<Integer> year = builder.function("year", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		Expression<Integer> month = builder.function("month", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		
		Expression<Boolean> estaPendiente = builder.equal(root.<Integer>get(ESTADO_TRAM), 0);
		Expression<Boolean> estaRechazada = builder.equal(root.<Integer>get(ESTADO_TRAM), 2);
		Expression<Boolean> estaTramitada = builder.or(
				builder.equal(root.<Integer>get(ESTADO_TRAM), 1)
				, builder.equal(root.<Integer>get(ESTADO_TRAM), 3));
		Expression<Integer> exp0 = builder.literal(0);
		Expression<Integer> exp1 = builder.literal(1);
		
		Expression<Integer> pendiente = builder.function("if", Integer.class
				, estaPendiente, exp1, exp0 );
		Expression<Integer> rechazada = builder.function("if", Integer.class
				, estaRechazada, exp1, exp0 );
		Expression<Integer> tramitada = builder.function("if", Integer.class
				, estaTramitada, exp1, exp0 );
		
		Expression<Long> totalPorAnioMes = builder.count(root.get("id"));
		Expression<Long> totalPendientes = builder.sumAsLong(pendiente);
		Expression<Long> totalRechazadas = builder.sumAsLong(rechazada);
		Expression<Long> totalTramitadas = builder.sumAsLong(tramitada);
		
		cq.multiselect(year, month
					, totalPorAnioMes
					, totalPendientes, totalRechazadas, totalTramitadas);  
		
		cq.groupBy(year, month);
		
		List<YearMonthEstatusVO> tupleResult = em.createQuery(cq).getResultList();
		
		return tupleResult;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<YearMonthEstatusVO> groupByMesTramitacionPrevista(
			Date inicioPeriodo
			, Date finPeriodo
			){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<YearMonthEstatusVO> cq = builder.createQuery(YearMonthEstatusVO.class);
		Root<TramitacionAPI> root = cq.from(TramitacionAPI.class);
		
		Expression<Integer> year = builder.function("year", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		Expression<Integer> month = builder.function("month", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		
		Expression<Boolean> estaPendiente = builder.equal(root.<Integer>get(ESTADO_TRAM), 0);
		Expression<Boolean> estaRechazada = builder.equal(root.<Integer>get(ESTADO_TRAM), 2);
		Expression<Boolean> estaTramitada = builder.or(
				builder.equal(root.<Integer>get(ESTADO_TRAM), 1)
				, builder.equal(root.<Integer>get(ESTADO_TRAM), 3));
		Expression<Integer> exp0 = builder.literal(0);
		Expression<Integer> exp1 = builder.literal(1);
		
		Expression<Integer> pendiente = builder.function("if", Integer.class
				, estaPendiente, exp1, exp0 );
		Expression<Integer> rechazada = builder.function("if", Integer.class
				, estaRechazada, exp1, exp0 );
		Expression<Integer> tramitada = builder.function("if", Integer.class
				, estaTramitada, exp1, exp0 );
		
		Expression<Long> totalPorAnioMes = builder.count(root.get("id"));
		Expression<Long> totalPendientes = builder.sumAsLong(pendiente);
		Expression<Long> totalRechazadas = builder.sumAsLong(rechazada);
		Expression<Long> totalTramitadas = builder.sumAsLong(tramitada);
		
		cq.multiselect(year, month
				, totalPorAnioMes
				, totalPendientes, totalRechazadas, totalTramitadas);  
		
		Predicate fechaInicio = builder.greaterThanOrEqualTo(root.<Date>get(FECHA_TRAM_PREVISTA), inicioPeriodo);
		Predicate fechaFinal  = builder.lessThanOrEqualTo(root.<Date>get(FECHA_TRAM_PREVISTA), finPeriodo);
		
		Predicate where = builder.and(fechaInicio, fechaFinal);
		
		cq.where(where);
		
		cq.groupBy(year, month);
		
		List<YearMonthEstatusVO> tupleResult = em.createQuery(cq).getResultList();
		
		return tupleResult;
	}
	
	
	public List<Tuple> getPeticionesPorTipoPorAnioYMes(){
		
		List<Tuple> lista = new ArrayList<Tuple>();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = builder.createTupleQuery();
		Root<TramitacionAPI> root = cq.from(TramitacionAPI.class);
		
		Expression<Integer> year = builder.function("year", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		Expression<Integer> month = builder.function("month", Integer.class, root.get(FECHA_TRAM_PREVISTA));
		
		cq.groupBy(year, month);
		
		List<CodAPI> listaCampos = codAPIService.findAll();
		List<Selection<?>> listaExpresiones = new ArrayList<Selection<?>>();
		listaExpresiones.add(year);
		listaExpresiones.add(month);
		
		
		for(CodAPI codAPI : listaCampos){
			logger.info(String.format("CodAPI : [%s]", codAPI));
			Expression<Boolean> isCodAPI = builder.equal(root.<Integer>get("codAPI"), codAPI.getCodApi());
			Expression<Integer> exp0 = builder.literal(0);
			Expression<Integer> exp1 = builder.literal(1);
			
			Expression<Integer> sumCodAPI = builder.function("if", Integer.class
					, isCodAPI, exp1, exp0 );
			Expression<Long> totalCodAPI = builder.sumAsLong(sumCodAPI);
			listaExpresiones.add(totalCodAPI);
		}
		
		cq.multiselect(listaExpresiones);
		
		TypedQuery<Tuple> peticiones = em.createQuery(cq);
		
		lista = peticiones.getResultList();
		
		return lista;
	}
	
}
