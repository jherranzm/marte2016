package telefonica.aaee.marte.marte.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.marte.model.FacturaPagaLibroFacturacion;

public abstract class FacturaPagaLibroFacturacionSpecifications {
	public static Specification<FacturaPagaLibroFacturacion> searchByCif(final String cif) {
        return new Specification<FacturaPagaLibroFacturacion>() {

           public Predicate toPredicate(
        		   Root<FacturaPagaLibroFacturacion> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(root.<String>get("cif"), cif);
           }
       };
     }
	public static Specification<FacturaPagaLibroFacturacion> searchByAcuerdoConcertada(
			final String acuerdoConcertada
			) {
		return new Specification<FacturaPagaLibroFacturacion>() {
			
			public Predicate toPredicate(
					Root<FacturaPagaLibroFacturacion> root
					, CriteriaQuery<?> criteriaQuery
					, CriteriaBuilder criteriaBuilder)
			{
				return criteriaBuilder.and(
						criteriaBuilder.equal(root.<String>get("acuerdoConcertada"), acuerdoConcertada)
						);
			}
		};
	}
}
