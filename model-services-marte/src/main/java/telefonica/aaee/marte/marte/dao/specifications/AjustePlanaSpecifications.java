package telefonica.aaee.marte.marte.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.marte.model.AjustePlana;

public abstract class AjustePlanaSpecifications {
	public static Specification<AjustePlana> searchByCif(final String cif) {
        return new Specification<AjustePlana>() {

           public Predicate toPredicate(
        		   Root<AjustePlana> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.like(
            		   root.<String>get("cif")
            		   	, "%" + cif + "%");
           }
       };
     }
	public static Specification<AjustePlana> searchByAcuerdoAny(
			final String tipoDoc,
			final String cif,
			final String acuerdoNumero,
			final String fechaAny
			) {
		return new Specification<AjustePlana>() {
			
			public Predicate toPredicate(
					Root<AjustePlana> root
					, CriteriaQuery<?> criteriaQuery
					, CriteriaBuilder criteriaBuilder)
			{
				return criteriaBuilder.and(
						criteriaBuilder.equal(root.<String>get("tipoDoc"), tipoDoc)
						, criteriaBuilder.equal(root.<String>get("cif"), cif)
						, criteriaBuilder.equal(root.<String>get("acuerdoNumero"), acuerdoNumero)
						, criteriaBuilder.equal(root.<String>get("fechaAny"), fechaAny)
						);
			}
		};
	}
}
