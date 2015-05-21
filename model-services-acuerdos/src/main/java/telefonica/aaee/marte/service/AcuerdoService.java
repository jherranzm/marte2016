package telefonica.aaee.marte.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import telefonica.aaee.marte.model.Acuerdo;
import telefonica.aaee.marte.specifications.AcuerdoSpecifications;

@Service
public class AcuerdoService extends GenericAcuerdosService {

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
						new Order(Direction.ASC, "fechaVigor")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByCif(cif), request);
	}

	public Page<Acuerdo> findByCifActivos(String cif, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "fechaVigor")
						)
		);
		return repo.findAll(AcuerdoSpecifications.searchByCifActivos(cif), request);
	}
	
}
