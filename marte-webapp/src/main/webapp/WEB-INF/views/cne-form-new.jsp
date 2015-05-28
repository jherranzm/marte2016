<%@ include file="/WEB-INF/views/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="cne.form.new.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>

</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		
	<div class="container">
	

		<%@ include file="/WEB-INF/views/cne-warning-errors.jsp" %>

		
		
	        <div><!-- inicio contenido -->
				<form:form method="POST" commandName="cneForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/cne/save.html">
		
					<div class="well">

						<div class="input-group col-md-10 line">
							<h3><span class="label label-primary">
								<spring:message text="missing" code="cne.form.new.title" />
							</span></h3>
						</div>
						

		                <div class="input-group col-md-10 line">
		                        <div class="col-sm-12 form-group">
		                            <label class="control-label" for="acuerdo">Acuerdo</label>
		                            <form:select id="acuerdo" path="acuerdoId" class="col-sm-2 form-control input-sm">
										<form:options items="${acuerdos}" itemValue="id"
											itemLabel="acuerdo" />
									</form:select>
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
		                            </form:select>   
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="servicio">Servicio</label>
		                            <form:select class="col-sm-2 form-control input-sm" id="servicio" path="tipoDeServicio">
									</form:select>
		                        </div>
		                        <div class="col-sm-4 input-group date form-param">
		                            <input type="text" class="form-control input-sm" id="fecha" name="fecha" placeholder="Fecha">
		                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>   
		                        </div>
		                        <div class="col-sm-12 form-group form-param">
		                            <label class="control-label" for="descConcepto">Concepto</label>
		                            <input type="text" class="form-control input-sm" id="descConcepto" name="descConcepto" placeholder="Descripción concepto">   
		                        </div>
		                        <div class="col-sm-4 form-group form-param">
		                            <label class="control-label" for="importe">Importe</label>
		                            <input type="number" class="form-control input-sm" id="importe" name="importe" placeholder="0.00" step="0.01" min="0.00">   
		                        </div>
		                        <div class="col-sm-12 form-group form-param">
		                            <label class="control-label" for="comentarios">Comentarios</label>
		                            <input type="text" class="form-control input-sm" id="comentarios" name="comentarios" placeholder="Comentarios">   
		                        </div>
		                        
		                        <div class="form-group form-param">
								    <div class="col-md-offset-4 col-md-4">
									    <p class="text-center">
										    <button id="btn-save" type="submit" class="btn btn-primary">
											    Guardar
									    	</button>
										    <a href="${pageContext.request.contextPath}/cne/list.html"
											    id="btn-back" class="btn btn-default"> Atrás
										    </a>
									    </p>
		    						</div>
		    					</div>
		                        
		                </div>
            		</div>
					 

					 <!-- Spring Security -->
					 
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					 <input type="hidden" id="idacuerdo" name="idacuerdo" value="-1"/>
					 
					 
				</form:form>
	        </div><!-- fin contenido -->
		
		
	</div>	



	<%@ include file="/WEB-INF/views/include-jquery.jsp" %>
	
	<script type="text/javascript">
	$(document).ready(function() { 
		
		$('.form-param').hide();
		
		$('#fecha').datepicker({
			format: "yyyymmdd",
		    weekStart: 1,
		    todayBtn: "linked",
		    clearBtn: true,
		    language: "es",
		    todayHighlight: true
		});
		
		
		
		$('#acuerdo').change(
			function() {
				
				$('#idacuerdo').val($(this).val());
				
                $('.form-param').show();
                $('#acuerdo').attr('disabled', true);
                

				
				
				$.getJSON('${pageContext.request.contextPath}/cne/getServicios', {
					idAcuerdo : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="">Servicio</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].tipoDeServicio + '">'
								+ data[i].servicio + '</option>';
					}
					html += '</option>';
	 
					$('#servicio').html('').html(html);
				});
				
				$.getJSON('${pageContext.request.contextPath}/cne/getCifs', {
					idAcuerdo : $(this).val(),
					ajax : 'true'
				}, function(data) {
					var html = '<option value="">Cif</option>';
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						html += '<option value="' + data[i].cuc + '">'
								+ data[i].nombreClienteLargo + '</option>';
					}
					html += '</option>';
	 
					$('#cif').html('').html(html);
				});

			});
	});
	</script>
	
</body>
</html>