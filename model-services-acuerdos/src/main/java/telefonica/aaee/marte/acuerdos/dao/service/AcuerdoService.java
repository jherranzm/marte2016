package telefonica.aaee.marte.acuerdos.dao.service;

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
import org.springframework.stereotype.Service;

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;
import telefonica.aaee.marte.acuerdos.dao.specifications.AcuerdoSpecifications;

@Service
public class AcuerdoService extends GenericAcuerdosService {

	protected final Log logger = LogFactory.getLog(getClass());

	private SimpleJpaRepository<Acuerdo, String> repo;

	public AcuerdoService() {
	}

	public AcuerdoService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<Acuerdo, String> entityInfo = new JpaMetamodelEntityInformation<Acuerdo, String>(
				Acuerdo.class, em.getMetamodel());
		repo = new SimpleJpaRepository<Acuerdo, String>(entityInfo, em);
		
		//logger.info(String.format("\n\n\n\n\n\nNúmero de acuerdos: [%d]", repo.findAll().size()));

	}
	
	
	public List<Acuerdo> findAll(){
		return repo.findAll();
	}
	
	public Iterable<Acuerdo> findAll(PageRequest page) {
		return repo.findAll(page);
	}

	public Acuerdo findById(String id) {
		return repo.findOne(id);
	}
	
	public Page<Acuerdo> findByCif(String cif, Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "acuerdoNumero")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByCif(cif), request);
	}

	public Page<Acuerdo> findByCifActivos(String cif, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.DESC, "acuerdoNumero")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByCifActivos(cif), request);
	}

	public Page<Acuerdo> findByTipoAcuerdoActivos(String tipoAcuerdo, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "acuerdoNumero")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByTipoAcuerdoActivos(tipoAcuerdo), request);
	}

	public Acuerdo findByIDAcuerdo(String IDAcuerdo) {
		return repo.findOne(IDAcuerdo);
	}

	public Page<Acuerdo> findByTipoAcuerdoNoActivos(String tipoAcuerdo, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "acuerdoNumero")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByTipoAcuerdoNoActivos(tipoAcuerdo), request);
	}
	
}
