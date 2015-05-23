CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `marte`.`vacuerdos` AS
    SELECT 
        `a`.`IDAcuerdo` AS `IDAcuerdo`,
        `a`.`Territorio` AS `Territorio`,
        `a`.`segmento` AS `Segmento`,
        `a`.`TipoDoc` AS `TipoDoc`,
        `a`.`Supra` AS `CIF`,
        `a`.`AcuerdoNumero` AS `AcuerdoNumero`,
        `a`.`Nombre` AS `Nombre`,
        `f`.`ImporteFijo` AS `ImporteFijoMT`,
        `f`.`ImporteFijoNM` AS `ImporteFijoNM`,
        `f`.`DescuentoPlana` AS `DescuentoPlanaMT`,
        `f`.`DescuentoPlanaNM` AS `DescuentoPlanaNM`,
        `f`.`ModalidadConcertada` AS `ModalidadConcertada`,
        `f`.`Planaautoajustable` AS `PlanaAutoajustable`,
        `f`.`Planasimplificada` AS `PlanaSimplificada`,
        `f`.`NuevoMercado` AS `NuevoMercado`,
        `a`.`TipoAcuerdo` AS `TipoAcuerdo`,
        `a`.`Operadora` AS `Operadora`,
        IF((`a`.`BAJA` = 'True'), 1, 0) AS `Baja`,
        `a`.`Fecha_Baja` AS `FechaBaja`,
        `a`.`CausaBaja` AS `CausaBaja`
    FROM (`acuerdos`.`acuerdos` `a`
        JOIN `acuerdos`.`fija_acuerdos` `f` 
        ON ((`a`.`IDAcuerdo` = `f`.`IDAcuerdo`)))
    WHERE
        (1 = 1)