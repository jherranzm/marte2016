package telefonica.aaee.marte.acuerdos.dao.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import telefonica.aaee.util.Constantes;

public class GenericAcuerdosService {

	@PersistenceContext(unitName = Constantes.JPAAcuerdosPU)
	@Qualifier(value = "acuerdosEntityManagerFactory")
	protected EntityManager em;
	
	public final Integer PAGE_SIZE = 10;

}
