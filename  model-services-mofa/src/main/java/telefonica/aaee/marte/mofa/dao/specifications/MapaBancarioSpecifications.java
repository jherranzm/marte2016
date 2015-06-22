package telefonica.aaee.marte.mofa.dao.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import telefonica.aaee.marte.mofa.dao.model.MapaBancario;
import telefonica.aaee.marte.mofa.dao.model.MapaBancarioPK;

public class MapaBancarioSpecifications {
	public static Specification<MapaBancario> searchByEntidadOficina(final MapaBancarioPK id) {
        return new Specification<MapaBancario>() {

           public Predicate toPredicate(
        		   Root<MapaBancario> root
        		   , CriteriaQuery<?> criteriaQuery
        		   , CriteriaBuilder criteriaBuilder)
           {
               return criteriaBuilder.equal(
            		   root.<MapaBancarioPK>get("id")
            		   	, id);
           }
       };
     }
	public static Specification<MapaBancario> searchByEntidadOficina(final String banco, final String oficina) {
		return new Specification<MapaBancario>() {
			
			public Predicate toPredicate(
					Root<MapaBancario> root
					, CriteriaQuery<?> criteriaQuery
					, CriteriaBuilder criteriaBuilder)
			{
				MapaBancarioPK id = new MapaBancarioPK();
				id.setEntidad(banco);
				id.setOficina(oficina);
				
				return criteriaBuilder.equal(
						root.<MapaBancarioPK>get("id")
						, id);
			}
		};
	}

}
