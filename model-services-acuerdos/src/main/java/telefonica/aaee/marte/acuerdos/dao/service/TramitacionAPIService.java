package telefonica.aaee.marte.acuerdos.dao.service;

import java.util.Date;
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

import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;
import telefonica.aaee.marte.acuerdos.dao.specifications.TramitacionAPISpecifications;

@Service
public class TramitacionAPIService extends GenericAcuerdosService {
	private SimpleJpaRepository<TramitacionAPI, Long> repo;

	public TramitacionAPIService() {
	}

	public TramitacionAPIService(EntityManager em) {
		this.em = em;
	}

	@PostConstruct
	public void init() {
		JpaEntityInformation<TramitacionAPI, Long> entityInfo = new 
				JpaMetamodelEntityInformation<TramitacionAPI, Long>(
				TramitacionAPI.class, em.getMetamodel());
		repo = new SimpleJpaRepository<TramitacionAPI, Long>(entityInfo, em);

	}
	
	
	public List<TramitacionAPI> findAll(){
		return repo.findAll();
	}
	
	public Iterable<TramitacionAPI> findAll(PageRequest page) {
		return repo.findAll(page);
	}

	public TramitacionAPI findById(Long id) {
		return repo.findOne(id);
	}
	
	public Page<TramitacionAPI> findByCif(String cif, Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByCif(cif), request);
	}

	public Page<TramitacionAPI> findByIDAcuerdo(String idAcuerdo, Integer pageNumber){
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
				);
		return repo.findAll(TramitacionAPISpecifications.searchByIDAcuerdo(idAcuerdo), request);
	}
	
	public Page<TramitacionAPI> findByCodAPI(String codAPI, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByCodAPI(codAPI), request);
	}

	public Page<TramitacionAPI> findByFechaTramitacionPrevista(Date fechaTramitacionPrevista, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByFechaTramitacionPrevista(fechaTramitacionPrevista), request);
	}

	public Page<TramitacionAPI> findByEstadoTram(int estadoTram, Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(
						new Order(Direction.ASC, "id")
						)
		);
		return repo.findAll(TramitacionAPISpecifications.searchByEstadoTram(estadoTram), request);
	}
	

}
