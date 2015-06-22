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

import telefonica.aaee.marte.mofa.dao.model.Municipio;
import telefonica.aaee.marte.mofa.dao.specifications.MunicipioSpecifications;


@Repository
public class MunicipioService extends GenericMofaService{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private SimpleJpaRepository<Municipio, Long> repo;

	public MunicipioService() {
	}
	
	public MunicipioService(EntityManager em) {
		logger.info("Inicializando [MunicipioService] mediante entityManager...");
		this.em = em;
	}
	
	@PostConstruct
    public void init() {
		logger.info("Inicializando [MunicipioService] ...");
        JpaEntityInformation<Municipio, Long> entityInfo = 
        		new JpaMetamodelEntityInformation<Municipio, Long>(Municipio.class, em.getMetamodel());
        repo = new SimpleJpaRepository<Municipio, Long>(entityInfo, em);
	}
	
	public List<Municipio> findAll() {
		return repo.findAll();
	}

	public Iterable<Municipio> findAll(PageRequest page) {
		return repo.findAll(page);
	}
	
	
	public Municipio findById(Long id) {
		return repo.findOne(id);
	}
	
	public Page<Municipio> findByCP(
			String cp,
			Integer pageNumber){
		
		PageRequest request = new PageRequest(0, 1000,
				new Sort(
						new Order(Direction.ASC, "municipio")
						)
				);
		
		return repo.findAll(MunicipioSpecifications.searchByCP(cp)
				, request);
	}
	

}