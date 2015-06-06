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

import telefonica.aaee.marte.acuerdos.dao.model.MotivoBaja;

@Service
public class MotivoBajaService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SimpleJpaRepository<MotivoBaja, Long> repo;

	public MotivoBajaService() {
	}

	public MotivoBajaService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<MotivoBaja, Long> entityInfo = new 
				JpaMetamodelEntityInformation<MotivoBaja, Long>(
						MotivoBaja.class, em.getMetamodel());
		repo = new SimpleJpaRepository<MotivoBaja, Long>(entityInfo, em);

	}
	
	
	public List<MotivoBaja> findAll(){
		return repo.findAll(new Sort(
				new Order(Direction.ASC, "idMotivoBajaMARTE")
				)
		);
	}
}
