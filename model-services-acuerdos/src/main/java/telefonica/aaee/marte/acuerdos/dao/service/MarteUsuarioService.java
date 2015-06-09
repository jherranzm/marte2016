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

import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;
import telefonica.aaee.marte.acuerdos.dao.specifications.MarteUsuarioSpecifications;

@Service
public class MarteUsuarioService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SimpleJpaRepository<MarteUsuario, Long> repo;

	public MarteUsuarioService() {
	}

	public MarteUsuarioService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<MarteUsuario, Long> entityInfo = new 
				JpaMetamodelEntityInformation<MarteUsuario, Long>(
						MarteUsuario.class, em.getMetamodel());
		repo = new SimpleJpaRepository<MarteUsuario, Long>(entityInfo, em);

	}
	
	
	public List<MarteUsuario> findAll(){
		return repo.findAll(new Sort(
				new Order(Direction.ASC, "idUsuario")
				)
		);
	}

	public MarteUsuario findByUsername(String username) {
		return repo.findOne(MarteUsuarioSpecifications.searchByCif(username));
	}
}
