package telefonica.aaee.marte.mofa.dao.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

public class GenericMofaService {

	@PersistenceContext(unitName = "JPAMofaPU")
	@Qualifier(value = "mofaEntityManagerFactory")
	protected EntityManager em;
	
	public final Integer PAGE_SIZE = 50;

}
