-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `977r_ADD_COND_TTrafico`(
  IN elAcuerdo VARCHAR(25)
)
BEGIN
  DECLARE funcion TEXT default 'Tráfico:Incorporar en 977r.ttrafico';
  DECLARE version TEXT default '';
  CALL add_log(CONCAT (":[Inicio]:", funcion, ":", version));
    -- Añadimos los registros que haya de este Acuerdo
		INSERT INTO 977r.ttrafico (
		  	acuerdo,
		  	idacuerdo,
		  	ambito_de_trafico,
      		desc_ambito_de_trafico
		)
		select 
			v.acuerdo,
			v.idacuerdo,
			v.ambito_de_trafico,
			v.desc_ambito_de_trafico

		from 977r.tbl_acuerdos ac
		left join 977r.tbl_trf_cursado_acap_ext v on 1 = 1
			and ac.id = v.idacuerdo
		left join 977r.ttrafico t on 1 = 1
			and t.idacuerdo = v.idacuerdo
			and t.ambito_de_trafico = v.ambito_de_trafico
			and t.desc_ambito_de_trafico = v.desc_ambito_de_trafico
		where 1 = 1
			and ac.acuerdo = elAcuerdo
			and t.acuerdo is null
		GROUP BY
			v.acuerdo,
			v.ambito_de_trafico
		ORDER BY
			v.acuerdo,
			v.ambito_de_trafico
		;
  CALL add_log(CONCAT (" Registros Incorporados en 977r.ttrafico:", row_count()));
  update 977r.ttrafico
  set
    ini_periodo = (select min(fecha_factura) from 977r.tbl_trf_cursado_acap_ext where acuerdo = elAcuerdo group by acuerdo),
    fin_periodo = (select max(fecha_factura) from 977r.tbl_trf_cursado_acap_ext where acuerdo = elAcuerdo group by acuerdo)
  where
    acuerdo = elAcuerdo
  ;
  CALL add_log(CONCAT (" Registros Modificados en 977r.ttrafico:", row_count()));
  CALL add_log(CONCAT ("[Fin]:", funcion, ":", version));
END