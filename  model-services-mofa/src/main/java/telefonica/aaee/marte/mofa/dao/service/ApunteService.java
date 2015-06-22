package telefonica.aaee.marte.mofa.dao.service;

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

import telefonica.aaee.marte.mofa.model.Apunte;


@Repository
public class ApunteService extends GenericMofaService{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private SimpleJpaRepository<Apunte, String> repo;

	public ApunteService() {
	}
	
	public ApunteService(EntityManager em) {
		logger.info("Inicializando [ApunteService] mediante entityManager...");
		this.em = em;
	}
	
	@PostConstruct
    public void init() {
		logger.info("Inicializando [ApunteService] ...");
        JpaEntityInformation<Apunte, String> entityInfo = 
        		new JpaMetamodelEntityInformation<Apunte, String>(Apunte.class, em.getMetamodel());
        repo = new SimpleJpaRepository<Apunte, String>(entityInfo, em);
	}
	
	public List<Apunte> findAll() {
		return repo.findAll();
	}

	public Iterable<Apunte> findAll(PageRequest page) {
		return repo.findAll(page);
	}
	
	
	public Apunte findById(String id) {
		return repo.findOne(id);
	}
	
	public Page<Apunte> findLastApuntes( int numberOfItems,
			 Integer pageNumber){
		
		PageRequest request = new PageRequest(pageNumber - 1, numberOfItems,
				new Sort(
						new Order(Direction.DESC, "factura")
						)
		);
		
		return repo.findAll(request);
	}
	



}