package telefonica.aaee.marte.acuerdos.dao.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlana;

@Service
public class SituacionPlanaService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SimpleJpaRepository<SituacionPlana, String> repo;

	public SituacionPlanaService() {
	}

	public SituacionPlanaService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<SituacionPlana, String> entityInfo = new 
				JpaMetamodelEntityInformation<SituacionPlana, String>(
						SituacionPlana.class, em.getMetamodel());
		repo = new SimpleJpaRepository<SituacionPlana, String>(entityInfo, em);

	}
	
	
	public List<SituacionPlana> findAll(){
		return repo.findAll(new Sort(
				new Order(Direction.ASC, "acuerdofx")
				)
		);
	}
}
