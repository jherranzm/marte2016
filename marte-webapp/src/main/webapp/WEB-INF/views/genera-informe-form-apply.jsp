<%@ include file="/WEB-INF/views/include.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing"
		code="aplica.condiciones.form.new.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		
	<div id="wrap">

		<div class="container">
		
			<div>
				<c:if test="${not empty mensajeError}">
					<div class="alert alert-error">
						<p>${mensajeError}</p>
					</div>
			    </c:if>		
			</div>
			
			
			<div class="panel panel-primary">
			
				<!-- cabecera -->
				
	 	       <div class="panel-heading">
		          <h3 class="panel-title">
						<spring:message text="missing"  code="aplica.condiciones.form.new.title" />
		          </h3>
	 	       </div>
	        
	        	<!-- contenido -->
	        	
		        <div id="formulario"><!-- inicio contenido -->
					<form:form method="POST" 
						id="generaInformeForm"
						commandName="generaInformeForm"
						 class="form-horizontal"
						action="${pageContext.request.contextPath}/generaInforme/apply?${_csrf.parameterName}=${_csrf.token}">
			
						<div class="form-group">
							<label for="acuerdoId" class="col-sm-2 control-label">
								<spring:message code="aplica.condiciones.form.field.acuerdo" />
							</label> 
							<div class="col-sm-10">
								<form:select path="acuerdoId">
									<form:options items="${acuerdos}" itemValue="id"
										itemLabel="acuerdo" />
								</form:select>
							</div> 
						</div>
			
					<div class="form-group">
						<div class="dual-list list-left col-sm-5">
							<div class="col-sm-12">
								<label for="reglas" class="control-label">
									<spring:message text="missing" 
										code="aplica.condiciones.form.field.reglas.disponibles" />
								</label> 
							</div> 
							<div class="col-sm-12">
<%-- 								<form:checkboxes items="${reglasDisponibles}"  --%>
<%-- 													path="reglas" --%>
<%-- 													itemValue="id" --%>
<%-- 													itemLabel="nombre"  --%>
<%-- 													delimiter="<br/>" --%>
<%-- 													/> --%>

							
								<div id="sortableD" class="connectedSortable list-group">
									<c:forEach items="${reglasDisponibles}" var="regla">
										<a href="#" id="reglaD_${regla.id}" class="list-group-item">
										${regla.nombre}
										</a>
									</c:forEach>
								</div>

							</div> 
						</div> 
						
						
				        <div class="list-arrows col-md-1 text-center">
				            <button class="btn btn-default btn-sm move-left">
				                <span class="glyphicon glyphicon-chevron-left"></span>
				            </button>
				
				            <button class="btn btn-default btn-sm move-right">
				                <span class="glyphicon glyphicon-chevron-right"></span>
				            </button>
				        </div>
						
						<div class="dual-list list-right col-sm-5">
							<div class="col-sm-12">
								<label for="reglasAplicadas" class="control-label">
									<spring:message text="missing" 
										code="aplica.condiciones.form.field.reglas.aplicadas" />
								</label> 
							</div> 
							<div class="col-sm-12">
							
								<div id="sortableA" class="connectedSortable list-group">
								</div>
								<input id="ordenadas" name="ordenadas" type="hidden" value="">
							
							</div> 
						</div> 
					</div>
	
	
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<p class="text-center">
									<button id="btn-save" type="submit" class="btn btn-primary">
										<spring:message text="missing"  code="aplica.condiciones.form.btn.apply" />
									</button>
									<a href="${pageContext.request.contextPath}/"
										id="btn-back" class="btn btn-default"> <spring:message text="missing" 
											code="aplica.condiciones.form.btn.back" />
									</a>
								</p>
							</div>
						</div>
						 
	
						 <!-- Spring Security -->
						 
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						 
						 
					</form:form>
		        </div><!-- fin contenido -->
	
	
	
	
	
				<div id="resultados">
					<div id="infoResultados"></div>
					<div id="urls">
						<h3 style="color: blue;">
							<spring:message text="missing" code="resultados" />
						</h3>
						<br>
						<div id="tableResultados"></div>
					</div>
					<!-- #urls -->
				</div>
	
	    	  </div>
			
			
			
	
		</div><!-- .container -->
	</div><!-- #wrap -->


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp" %>
	
		<script type="text/javascript">
			$(function() {
				
				$('#chk_reglas_aplicadas').sortable();
				
				$('body').on('click', '.list-group .list-group-item', function () {
	                $(this).toggleClass('active');
	            });
				
			    $( "#sortableD, #sortableA" ).sortable({
			      connectWith: ".connectedSortable"
			    }).disableSelection();
			    
			    $('.list-arrows button').click(function () {
	                var $button = $(this), actives = '';
	                if ($button.hasClass('move-left')) {
	                    actives = $('#sortableA a.active');
	                    actives.clone().appendTo('#sortableD').toggleClass('active');
	                    actives.remove();
	                } else if ($button.hasClass('move-right')) {
	                	
	                    actives = $('#sortableD a.active');
	                    actives.clone().appendTo('#sortableA').toggleClass('active');
	                    actives.remove();
	                }
	                return false;
	            });
			    

				$("#resultados").hide();
				$("#urls").hide();
				$("#infoResultados").hide();
				
				
				function createTableResultados(data) {
					var table = $('<table></table>').attr('id', 'tblDown')
							.addClass('table').addClass('table-striped');
					var thead = $('<thead></thead>');
					var tr = $('<tr></tr>');
					var tdh1 = $('<th></th>').text('ID');
					var tdh2 = $('<th></th>').text('Mensaje');
					tr.append(tdh1);
					tr.append(tdh2);
					thead.append(tr);
					table.append(thead);

					var index = 0;
					for (i in data.resultados) {
						index = index + 1;
						var td1 = $('<td></td>').append(index);
						var td2 = $('<td></td>').append(data.resultados[i]);
						var row = $('<tr></tr>');
						row.append(td1);
						row.append(td2);
						table.append(row);
					}

					return table;
				}
				;

				var form = $("#generaInformeForm");
				form.on('submit', function(e) {
					
					var los = $("#sortableA").sortable('toArray');
			    	
			    	$("#ordenadas").val(los);
			    	
					var fd = new FormData(this);
					$.ajax({
						url : form.attr('action'),
						type : "POST",
						data : fd,
						dataType : 'json',
						processData : false, // tell jQuery not to process the data
						contentType : false, // tell jQuery not to set contentType
						beforeSend : function() {

							$("#formulario").hide();

							var p_info = $('<p></p>');
							p_info.text('Aplicando condiciones / reglas de negocio ...');
							$('#infoResultados').empty().addClass('text-center');
							$('#infoResultados').append(p_info);

							var cl = new CanvasLoader('infoResultados');
							cl.setColor('#3f68a6'); // default is '#000000'
							cl.setShape('roundRect'); // default is 'oval'
							cl.setDiameter(114); // default is 40
							cl.setDensity(17); // default is 40
							cl.setRange(1); // default is 1.3
							cl.setSpeed(1); // default is 2
							cl.setFPS(10); // default is 24
							cl.show(); // Hidden by default

							$("#infoResultados").show();
							$("#resultados").show();
						},
						success : function( data, textStatus, errorThrown) {

							$("#resultados").hide();
							$("#infoResultados").hide();

							$('#tableResultados').empty().append(createTableResultados(data));

							$("#urls").show();
							$("#resultados").show();

						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert("ERROR:[" + jqXHR + "][" + textStatus + "][" + errorThrown + "]");
						}
					}).fail(function(jqXHR, textStatus) {
						alert("Request failed: "
								+ textStatus);
					});

					e.preventDefault();

				});
			});
		</script>


</body>
</html>