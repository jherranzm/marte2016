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

import telefonica.aaee.marte.acuerdos.dao.model.SituacionPlanaEstado;

@Service
public class SituacionPlanaEstadoService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SimpleJpaRepository<SituacionPlanaEstado, String> repo;

	public SituacionPlanaEstadoService() {
	}

	public SituacionPlanaEstadoService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<SituacionPlanaEstado, String> entityInfo = new 
				JpaMetamodelEntityInformation<SituacionPlanaEstado, String>(
						SituacionPlanaEstado.class, em.getMetamodel());
		repo = new SimpleJpaRepository<SituacionPlanaEstado, String>(entityInfo, em);

	}
	
	
	public List<SituacionPlanaEstado> findAll(){
		return repo.findAll(new Sort(
				new Order(Direction.ASC, "estado")
				)
		);
	}
}
