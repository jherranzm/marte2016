package telefonica.aaee.marte.mofa.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.mofa.dao.model.Municipio;

public class MunicipioSpecifications {
	public static Specification<Municipio> searchByCP(final String cp) {
        return new Specification<Municipio>() {

           public Predicate toPredicate(
        		   Root<Municipio> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(
            		   root.<String>get("codPostal")
            		   	, cp);
           }
       };
     }
}
