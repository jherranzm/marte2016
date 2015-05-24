package telefonica.aaee.marte.acuerdos.dao.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.model.YearMonthEstatusVO;
import telefonica.aaee.marte.acuerdos.dao.specifications.TramitacionAPISpecifications;

@Service
public class TramitacionAPIService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

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
		
		cq.multiselect(root.get("estadoTram"), builder.count(root.get("id")));  //using metamodel
		cq.groupBy(root.get("estadoTram"));
		List<Tuple> tupleResult = em.createQuery(cq).getResultList();
		for (Tuple t : tupleResult) {
			Integer id = (Integer) t.get(0);
			Long num = (Long) t.get(1);
		    logger.info(String.format("EstadoTram : [%d] : [%d]", id, num));
		}
		return tupleResult;
	}
	
	
	public List<YearMonthEstatusVO> groupByMesTramitacionPrevista(){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<YearMonthEstatusVO> cq = builder.createQuery(YearMonthEstatusVO.class);
		Root<TramitacionAPI> root = cq.from(TramitacionAPI.class);
		
		Expression<Integer> year = builder.function("year", Integer.class, root.get("fechaTramPrevista"));
		Expression<Integer> month = builder.function("month", Integer.class, root.get("fechaTramPrevista"));
		
		Expression<Boolean> estaPendiente = builder.equal(root.<Integer>get("estadoTram"), 0);
		Expression<Boolean> estaRechazada = builder.equal(root.<Integer>get("estadoTram"), 2);
		Expression<Boolean> estaTramitada = builder.or(
				builder.equal(root.<Integer>get("estadoTram"), 1)
				, builder.equal(root.<Integer>get("estadoTram"), 3));
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
		
//		List<Tuple> tupleResult = em.createQuery(cq).getResultList();
//		for (Tuple t : tupleResult) {
//			Integer resYear = (Integer) t.get(0);
//			Integer resMonth = (Integer) t.get(1);
//			Long num = (Long) t.get(2);
//			Long pendientes = (Long) t.get(3);
//			Long rechazadas = (Long) t.get(4);
//			Long tramitadas = (Long) t.get(5);
//			logger.info(String.format("fechaTramPrevista : [%d][%d] : [%d][%d][%d][%d]", resYear, resMonth, num, pendientes, rechazadas, tramitadas));
//		}
		
		List<YearMonthEstatusVO> tupleResult = em.createQuery(cq).getResultList();
		
		
		return tupleResult;
	}
	
}
