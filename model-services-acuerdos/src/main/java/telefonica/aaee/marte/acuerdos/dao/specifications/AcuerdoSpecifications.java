package telefonica.aaee.marte.acuerdos.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.acuerdos.dao.model.Acuerdo;

public class AcuerdoSpecifications {
	public static Specification<Acuerdo> searchByCif(final String cif) {
        return new Specification<Acuerdo>() {

           public Predicate toPredicate(
        		   Root<Acuerdo> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(
            		   root.<String>get("cif")
            		   	, cif );
           }
       };
     }

	public static Specification<Acuerdo> searchByCifActivos(final String cif) {
        return new Specification<Acuerdo>() {

            public Predicate toPredicate(
         		   Root<Acuerdo> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.and(
                		criteriaBuilder.equal(
                      		   root.<String>get("cif")
                      		   	, cif )
                      	, criteriaBuilder.equal(
                       		   root.<Boolean>get("baja")
                     		   	, false )
                		);
            }
        };
	}

	public static Specification<Acuerdo> searchByTipoAcuerdoActivos(
			final String tipoAcuerdo) {
        return new Specification<Acuerdo>() {

            public Predicate toPredicate(
         		   Root<Acuerdo> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.and(
                		criteriaBuilder.equal(
                      		   root.<String>get("tipoAcuerdo")
                      		   	, tipoAcuerdo )
                      	, criteriaBuilder.equal(
                       		   root.<Boolean>get("baja")
                     		   	, false )
                		);
            }
        };
	}

	public static Specification<Acuerdo> searchByTipoAcuerdoNoActivos(
			final String tipoAcuerdo) {
        return new Specification<Acuerdo>() {

            public Predicate toPredicate(
         		   Root<Acuerdo> root
         		   , CriteriaQuery<?> criteriaQuery
         		   , CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.and(
                		criteriaBuilder.equal(
                      		   root.<String>get("tipoAcuerdo")
                      		   	, tipoAcuerdo )
                      	, criteriaBuilder.equal(
                       		   root.<Boolean>get("baja")
                     		   	, true )
                		);
            }
        };
	}


}
