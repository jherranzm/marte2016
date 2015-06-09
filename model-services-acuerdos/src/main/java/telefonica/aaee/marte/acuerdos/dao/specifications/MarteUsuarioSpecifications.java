package telefonica.aaee.marte.acuerdos.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.acuerdos.dao.model.MarteUsuario;

public class MarteUsuarioSpecifications {
	public static Specification<MarteUsuario> searchByCif(final String username) {
        return new Specification<MarteUsuario>() {

           public Predicate toPredicate(
        		   Root<MarteUsuario> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(
            		   root.<String>get("codUsuario")
            		   	, username );
           }
       };
     }


}
