package telefonica.aaee.marte.marte.dao.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import telefonica.aaee.util.Constantes;

public class GenericMarteService {

	@PersistenceContext(unitName = Constantes.JPAMartePU)
	@Qualifier(value = "marteEntityManagerFactory")
	protected EntityManager em;
	
	public final Integer PAGE_SIZE = 50;

}
