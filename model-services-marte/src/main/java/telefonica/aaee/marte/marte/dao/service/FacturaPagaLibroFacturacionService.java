package telefonica.aaee.marte.marte.dao.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

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

import telefonica.aaee.marte.marte.dao.specifications.FacturaPagaLibroFacturacionSpecifications;
import telefonica.aaee.marte.marte.model.FacturaPagaLibroFacturacion;


@Repository
public class FacturaPagaLibroFacturacionService extends GenericMarteService{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private SimpleJpaRepository<FacturaPagaLibroFacturacion, Long> repo;
	

	public FacturaPagaLibroFacturacionService() {
	}
	
	
	public FacturaPagaLibroFacturacionService(EntityManager em) {
		logger.info("Inicializando [FacturaPagaLibroFacturacionService] mediante entityManager...");
		this.em = em;
	}
	
	
	@PostConstruct
    public void init() {
		logger.info("Inicializando [FacturaPagaLibroFacturacionService] ...");
        JpaEntityInformation<FacturaPagaLibroFacturacion, Long> entityInfo = new JpaMetamodelEntityInformation<FacturaPagaLibroFacturacion, Long>(FacturaPagaLibroFacturacion.class, em.getMetamodel());
        repo = new SimpleJpaRepository<FacturaPagaLibroFacturacion, Long>(entityInfo, em);
        
        //logger.info(String.format("Número de registros :  [%d]", repo.findAll().size()));
	}
	
	public List<FacturaPagaLibroFacturacion> findAll() {
		return repo.findAll();
	}

	public Iterable<FacturaPagaLibroFacturacion> findAll(PageRequest page) {
		return repo.findAll(page);
	}
	
	
	public FacturaPagaLibroFacturacion findById(Long id) {
		return repo.findOne(id);
	}




	public Page<FacturaPagaLibroFacturacion> findByAcuerdoConcertada(
			 String acuerdoConcertada,
			 Integer pageNumber){
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.DESC, "fechaEmision")
						)
		);
		
		return repo.findAll(FacturaPagaLibroFacturacionSpecifications.searchByAcuerdoConcertada(acuerdoConcertada), request);
	}
	
	public Page<FacturaPagaLibroFacturacion> findByCif(
			String cif,
			Integer pageNumber){
		
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.DESC, "fechaEmision")
						)
				);
		
		return repo.findAll(FacturaPagaLibroFacturacionSpecifications.searchByCif(cif) , request);
	}
	

}