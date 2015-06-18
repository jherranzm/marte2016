package telefonica.aaee.marte.marte.dao.service;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import telefonica.aaee.marte.marte.dao.exceptions.AjustePlanaNotFoundException;
import telefonica.aaee.marte.marte.dao.specifications.AjustePlanaSpecifications;
import telefonica.aaee.marte.marte.model.AjustePlana;


@Repository
public class AjustePlanaService extends GenericMarteService{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String FIND_BY_UNIQUE = "AjustePlana.findByUnique";
		
	private SimpleJpaRepository<AjustePlana, Long> repo;
	

	public AjustePlanaService() {
	}
	
	
	public AjustePlanaService(EntityManager em) {
		logger.info("Inicializando [AjustePlanaService] mediante entityManager...");
		this.em = em;
	}
	
	
	@PostConstruct
    public void init() {
		logger.info("Inicializando [AjustePlanaService] ...");
        JpaEntityInformation<AjustePlana, Long> entityInfo = new JpaMetamodelEntityInformation<AjustePlana, Long>(AjustePlana.class, em.getMetamodel());
        repo = new SimpleJpaRepository<AjustePlana, Long>(entityInfo, em);
	}
	
	public List<AjustePlana> findAll() {
		return repo.findAll();
	}

	public Iterable<AjustePlana> findAll(PageRequest page) {
		return repo.findAll(page);
	}
	
	
	public AjustePlana findById(Long id) {
		return repo.findOne(id);
	}




	@Transactional(value="marteTransactionManager")
	public AjustePlana update(AjustePlana modificada) 
			throws AjustePlanaNotFoundException {
		AjustePlana item = repo.findOne(new Long(modificada.getId()));

		if (item == null)
			throw new AjustePlanaNotFoundException();
		logger.info("Localizado : " + item.getId());

		AjustePlana result = repo.saveAndFlush(modificada);
		logger.info("Modificado : " + result.toString());
		return result;
	}


	public AjustePlana findByUnique(AjustePlana ajuste) {
		AjustePlana ret = null;
		
		logger.info(String.format("SQL : [%s]", FIND_BY_UNIQUE));
		
		Query query = em.createNamedQuery(FIND_BY_UNIQUE);
		query.setParameter("t", ajuste.getTipoDoc());
		query.setParameter("c", ajuste.getCif());
		query.setParameter("a", ajuste.getAcuerdoNumero());
		query.setParameter("n", ajuste.getNegocio());
		query.setParameter("f", ajuste.getFechaAny());
		
	    try {
			ret = (AjustePlana)query.getSingleResult();
			logger.info(String.format("Número de registros encontrados : [%d]", (ret == null ? 0 : 1)));
		} catch (Exception e) {
			logger.info(String.format("No se han encontrado registros...", ""));
		}
	    
	    return ret ;
	}



	@Transactional(value="marteTransactionManager")
	public AjustePlana create(AjustePlana nuevo) {
		logger.info("Guardamos el AjustePlana...");
		AjustePlana result = repo.saveAndFlush(nuevo);
		logger.info(String.format("AjustePlana guardado con ID :[%d]", result.getId()));
		
		return result;
	}
	
	
	public List<AjustePlana> saveAll(Set<AjustePlana> oficinas){
		return repo.save(oficinas);
	}
	
	public Page<AjustePlana> findByAcuerdoAny(
			 String tipoDoc,
			 String cif,
			 String acuerdoNumero,
			 String fechaAny,
			 Integer pageNumber){
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "acuerdoNumero")
						)
		);
		
		return repo.findAll(AjustePlanaSpecifications.searchByAcuerdoAny(tipoDoc, cif, acuerdoNumero, fechaAny), request);
	}
	
	public Page<AjustePlana> findByAcuerdoAny(
			AjustePlana ajuste,
			Integer pageNumber){
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "acuerdoNumero")
						)
				);
		
		return repo.findAll(AjustePlanaSpecifications.searchByAcuerdoAny(
				ajuste.getTipoDoc()
				, ajuste.getCif()
				, ajuste.getAcuerdoNumero()
				, ajuste.getFechaAny())
				, request);
	}
	
	@Transactional(value="marteTransactionManager")
	public void loadDataInfile(String file){
		String sql = "LOAD DATA LOCAL INFILE '"+ file +"' "
				+ "into table marte.tbl_AjustePlana_temp "
				+ "fields terminated by ';' enclosed by '\"' "
				+ "(tipo_doc, cif, acuerdo_numero"
				+ ", ajustes001, ajustes002, ajustes003, ajustes004, ajustes005"
				+ ", ajustes006, ajustes007, ajustes008, ajustes009, ajustes010"
				+ ", total_ajustes, negocio, fecha_any); ";
		
		logger.info(sql);
		Query query = em.createNativeQuery(sql);
		logger.info("Inicio de carga masiva de AjustePlanas...");
		long ret = query.executeUpdate();
		logger.info(String.format("Fin de carga masiva! Se han cargado [%d] registros.", ret));
	}


}