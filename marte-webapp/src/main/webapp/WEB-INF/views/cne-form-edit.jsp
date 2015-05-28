<%@ include file="/WEB-INF/views/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="cne.form.edit.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>

</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		
	<div class="container">
	

		<%@ include file="/WEB-INF/views/cne-warning-errors.jsp" %>

		
		
	        <div id="cne-form-contenido"><!-- inicio contenido -->
				<form:form method="POST" commandName="cneForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/cne/edit/${cneForm.id}.html">
		
					<div class="well">
					
						<div class="input-group col-md-10 line">
							<h3><span class="label label-primary">
								<spring:message text="missing" code="cne.form.edit.title" /> ${cneForm.id}
							</span></h3>
						</div>
						
		                <div class="input-group col-md-10 line">
		                        <div class="col-sm-12 form-group">
		                            <label class="control-label" for="acuerdo">Acuerdo</label>
		                            <input type="text" class="form-control input-sm" 
		                            	id="acuerdo" name="acuerdo" 
		                            	placeholder="${cneForm.acuerdo}" value="${cneForm.acuerdo}">
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="tipoFacturacion">Tipo Facturacion</label>
		                    	    <form:select class="col-sm-2 form-control input-sm" id="tipoFacturacion" path="tipoFacturacion">
		                                <form:option value="C">Concertada</form:option>   
		                                <form:option value="P">Personalizada</form:option>   
		                    	    </form:select>   
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="cif">CIF</label>
		                            <form:select class="col-sm-2 form-control input-sm" id="cif" path="cuc">
			                            <form:options items="${cifs}" itemValue="cuc"
										itemLabel="nombreClienteLargo" />
		                            </form:select>   
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="servicio">Servicio</label>
		                            <form:select class="col-sm-2 form-control input-sm" id="objServicio" path="tipoDeServicio">
			                            <form:options items="${servicios}" itemValue="tipoDeServicio"
										itemLabel="servicio" />
									</form:select>
		                        </div>
		                        <div class="col-sm-4 input-group date form-param">
		                            <input type="text" class="form-control input-sm" 
		                            	id="fecha" name="fecha" 
		                            	placeholder="Fecha" value="${cneForm.fecha}">
		                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>   
		                        </div>
		                        <div class="col-sm-12 form-group form-param">
		                            <label class="control-label" for="descConcepto">Concepto</label>
		                            <input type="text" class="form-control input-sm" 
		                            	id="descConcepto" name="descConcepto" 
		                            	placeholder="Descripción concepto"  value="${cneForm.descConcepto}" >   
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="importe">Importe</label>
		                            <input type="number" class="form-control input-sm" 
		                            	id="importe" name="importe" 
		                            	placeholder="0.00"  value="${cneForm.importe}"
		                            	step="0.01" min="0.00">   
		                        </div>
		                        <div class="col-sm-12 form-group form-param">
		                            <label class="control-label" for="comentarios">Comentarios</label>
		                            <input type="text" class="form-control input-sm" 
		                            	id="comentarios" name="comentarios" 
		                            		placeholder="Comentarios"  value="${cneForm.comentarios}">   
		                        </div>
		                        
		                        <div class="form-group form-param">
								    <div class="col-md-offset-4 col-md-4">
									    <p class="text-center">
										    <button id="btn-save" type="submit" class="btn btn-sm btn-primary">
											    Guardar
									    	</button>
										    <a href="${pageContext.request.contextPath}/cne/list.html"
											    id="btn-back" class="btn btn-sm btn-default"> Atrás
										    </a>
									    </p>
		    						</div>
		    					</div>
		                        
		                </div>
            		</div>
					 

					 <!-- Spring Security -->
					 
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					 <input type="hidden" id="idacuerdo" name="idacuerdo" value="${cneForm.idacuerdo}"/>
					 
					 
				</form:form>
	        </div><!-- fin contenido -->
		
		
	</div>	



	<%@ include file="/WEB-INF/views/include-jquery.jsp" %>
	
	<script type="text/javascript">
	$(document).ready(function() { 
		
		$('#fecha').datepicker({
			format: "yyyymmdd",
		    weekStart: 1,
		    todayBtn: "linked",
		    clearBtn: true,
		    language: "es",
		    todayHighlight: true
		});
		
		
		
	});
	</script>
	
</body>
</html>