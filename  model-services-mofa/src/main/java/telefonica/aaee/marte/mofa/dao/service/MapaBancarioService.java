package telefonica.aaee.marte.mofa.dao.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import telefonica.aaee.marte.mofa.dao.model.MapaBancario;
import telefonica.aaee.marte.mofa.dao.model.MapaBancarioPK;


@Repository
public class MapaBancarioService extends GenericMofaService{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private SimpleJpaRepository<MapaBancario, MapaBancarioPK> repo;

	public MapaBancarioService() {
	}
	
	public MapaBancarioService(EntityManager em) {
		logger.info("Inicializando [MapaBancarioService] mediante entityManager...");
		this.em = em;
	}
	
	@PostConstruct
    public void init() {
		logger.info("Inicializando [MapaBancarioService] ...");
        JpaEntityInformation<MapaBancario, MapaBancarioPK> entityInfo = 
        		new JpaMetamodelEntityInformation<MapaBancario, MapaBancarioPK>(MapaBancario.class, em.getMetamodel());
        repo = new SimpleJpaRepository<MapaBancario, MapaBancarioPK>(entityInfo, em);
	}
	
	public List<MapaBancario> findAll() {
		return repo.findAll();
	}

	public Iterable<MapaBancario> findAll(PageRequest page) {
		return repo.findAll(page);
	}
	
	
	public MapaBancario findById(MapaBancarioPK id) {
		return repo.findOne(id);
	}
	

	public MapaBancario findByEntidadOficina(String banco, String oficina){
		MapaBancarioPK id = new MapaBancarioPK();
		id.setEntidad(banco);
		id.setOficina(oficina);
		
		return repo.findOne(id);
	}
}