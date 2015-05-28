<%@ include file="/WEB-INF/views/include.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing"
		code="genera.informe.form.new.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp"%>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp"%>

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
						<spring:message text="missing" code="genera.informe.form.new.title" />
					</h3>
				</div>
	
				<!-- contenido -->
	
				<div id="formulario">
					<!-- inicio contenido -->
					<form:form method="POST" 
						id="generaInformeForm"
						commandName="generaInformeForm" class="form-horizontal"
						action="${pageContext.request.contextPath}/generaInforme/gen?${_csrf.parameterName}=${_csrf.token}">
	
						<div class="form-group">
							<label for="informeId" class="col-lg-2 control-label"> <spring:message
									code="genera.informe.form.field.informe" />
							</label>
							<div class="col-lg-10">
								<form:select path="informeId">
									<form:options items="${informes}" itemValue="id"
										itemLabel="nombre" />
								</form:select>
							</div>
						</div>
	
						<div class="form-group">
							<label for="acuerdoId" class="col-lg-2 control-label"> <spring:message
									code="genera.informe.form.field.acuerdo" />
							</label>
							<div class="col-lg-10">
								<form:select path="acuerdoId">
									<form:options items="${acuerdos}" itemValue="id"
										itemLabel="acuerdo" />
								</form:select>
							</div>
						</div>
	
						<div class="form-group">
							<div class="col-md-offset-4 col-md-4">
								<p class="text-center">
									<button id="btn-save" type="submit" class="btn btn-primary">
										<spring:message text="missing"
											code="genera.informe.form.btn.go" />
									</button>
									<a href="${pageContext.request.contextPath}/" id="btn-back"
										class="btn btn-default"> <spring:message text="missing"
											code="genera.informe.form.btn.back" />
									</a>
								</p>
							</div>
						</div>
	
						 
	
						 <!-- Spring Security -->
						 
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						 
						 
					</form:form><!-- id="generaInformeForm" -->
				</div><!-- id="formulario" -->
				
				<!-- fin contenido -->
	
			</div>
			<!-- .container -->
	
	
	
			<div id="resultados">
				<div id="infoResultados"></div>
				<div id="urls">
					<h3 style="color: blue;">
						<spring:message text="missing" code="muploadhz.success.info.text" />
					</h3>
					<br>
					<div id="tableDownloadableFiles"></div>
				</div>
				<!-- #urls -->
			</div>
	
		</div>


	</div>
	<!-- #wrap -->


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

		<script type="text/javascript">
			$(function() {

				var ctx = "${pageContext.request.contextPath}";

				$("#resultados").hide();
				$("#urls").hide();
				$("#infoResultados").hide();

				function createTableDownloadableFiles(data) {
					var table = $('<table></table>').attr('id', 'tblDown')
							.addClass('table').addClass('table-striped');
					var thead = $('<thead></thead>');
					var tr = $('<tr></tr>');
					var tdh1 = $('<th></th>').text('ID');
					var tdh2 = $('<th></th>').text('Fichero Excel');
					var tdh3 = $('<th></th>').text('Tamaño');
					tr.append(tdh1);
					tr.append(tdh2);
					tr.append(tdh3);
					thead.append(tr);
					table.append(thead);

					var index = 0;
					for (i in data.urls) {
						index = index + 1;
						var td1 = $('<td></td>').append(index);
						var str = '<a href="'+ ctx +'/getFile/file/'+ data.urls[i].fileName +'/' + data.urls[i].fileExt +'">'
								+ data.urls[i].fileName + '</a>';
						var td2 = $('<td></td>').append(str);
						var td3 = $('<td></td>').append(data.urls[i].fileSize);
						var row = $('<tr></tr>');
						row.append(td1);
						row.append(td2);
						row.append(td3);
						table.append(row);
					}

					return table;
				};
				
				
				function fillErrorMessages(data) {
					var div = $('<div></div>').attr('id', 'tblErrores');

					for (i in data.resultados) {
						var alert = $('<div class="alert alert-danger" role="alert"><strong>ERROR! </strong></div>')
							.append(data.resultados[i]);
						div.append(alert);
					}

					return div;
				};

				var form = $("#generaInformeForm");
				form.on('submit', function(e) {
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
								p_info.text('Generando informe ...');
								$('#infoResultados')
										.empty()
										.addClass('text-center');
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
						success : function(data, textStatus, errorThrown) {
							
							console.log("URLs    :" + data.urls);
							console.log("Errores : " + data.resultados);
							
							$("#resultados").hide();
							
							$("#infoResultados")
								.hide()
								.empty();
							
							if(data.resultados != null && data.resultados.length > 0){
								$("#infoResultados").append(fillErrorMessages(data))
								.show();
							}

							$('#tableDownloadableFiles')
								.hide()
								.empty();
							
							if(data.urls != null && data.urls.length > 0){
								$('#tableDownloadableFiles').append(createTableDownloadableFiles(data))
								.show();
								$("#urls").show();
							}
							$("#resultados").show();

						},
						error : function(jqXHR, textStatus, errorThrown) {
								alert("ERROR:[" + jqXHR + "][" + textStatus + "][" + errorThrown + "]");
						}
					})
					.fail(function(jqXHR, textStatus) {
							alert("Request failed: " + textStatus);
						});

				e.preventDefault();

			});
		});
		</script>
</body>
</html>