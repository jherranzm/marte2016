package telefonica.aaee.marte.acuerdos.dao.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.acuerdos.dao.model.TramitacionAPI;

public class TramitacionAPISpecifications {
	
	public static Specification<TramitacionAPI> searchByCif(final String cif) {
        return new Specification<TramitacionAPI>() {

           public Predicate toPredicate(
        		   Root<TramitacionAPI> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(
            		   root.<String>get("cif")
            		   	, cif );
           }
       };
     }

	public static Specification<TramitacionAPI> searchByIDAcuerdo(
			final String idAcuerdo) {
        return new Specification<TramitacionAPI>() {

            public Predicate toPredicate(
         		   Root<TramitacionAPI> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(
             		   root.<String>get("idAcuerdo")
             		   	, idAcuerdo );
            }
        };
	}

	public static Specification<TramitacionAPI> searchByCodAPI(final String codAPI) {
        return new Specification<TramitacionAPI>() {

            public Predicate toPredicate(
         		   Root<TramitacionAPI> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(
             		   root.<String>get("codAPI")
             		   	, codAPI );
            }
        };
	}

	public static Specification<TramitacionAPI> searchByFechaTramitacionPrevista(
			final Date fechaTramitacionPrevista) {
        return new Specification<TramitacionAPI>() {

            public Predicate toPredicate(
         		   Root<TramitacionAPI> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(
             		   root.<String>get("fechaTramPrevista")
             		   	, fechaTramitacionPrevista );
            }
        };
	}

	public static Specification<TramitacionAPI> searchByFechaTramitacionPrevista(
			final Date inicioPeriodo
			, final Date finalPeriodo) {
        return new Specification<TramitacionAPI>() {

            public Predicate toPredicate(
         		   Root<TramitacionAPI> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
            	Predicate mayorOIgualQue = criteriaBuilder.greaterThanOrEqualTo(
              		   root.<Date>get("fechaTramPrevista")
            		   	, inicioPeriodo );
            	Predicate menorOIgualQue = criteriaBuilder.lessThanOrEqualTo(
            			root.<Date>get("fechaTramPrevista")
            			, finalPeriodo );
            	
                return criteriaBuilder.and(mayorOIgualQue, menorOIgualQue);
            }
        };
	}

	public static Specification<TramitacionAPI> searchByEstadoTram(
			final int estadoTram) {
        return new Specification<TramitacionAPI>() {

            public Predicate toPredicate(
         		   Root<TramitacionAPI> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(
             		   root.<String>get("estadoTram")
             		   	, estadoTram );
            }
        };
	}

}
