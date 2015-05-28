<%@ include file="/WEB-INF/views/include.jsp"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message text="missing" code="mupload977r.form.title.text" /></title>

<%@ include file="/WEB-INF/views/include-css.jsp"%>

</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp"%>

	<div id="wrap">

		<div class="container">

			<div id="977r-form" class="panel panel-primary">
				<!-- cabecera -->
	
				<div id="977r-form-titulo" class="panel-heading">
					<h3 class="panel-title">
						<spring:message text="missing" code="mupload977r.form.title.text" />
					</h3>
				</div>
	
				<!-- contenido -->
	
				<div id="977r-form-contenido" class="panel-body" >
					<!-- inicio contenido -->
	
					<div id="formulario">
						<form:form method="post" id="uploadMultiple977RFilesForm"
							action="${pageContext.request.contextPath}/977r.json?${_csrf.parameterName}=${_csrf.token}"
							modelAttribute="uploadForm" enctype="multipart/form-data">
	
							<p class="bg-info webapp-margin">
								<spring:message text="missing" code="mupload.form.info.text" />
							</p>
	
							<div class="row">
								<div class="form-group">
									<label for="nombre" class="col-sm-4 control-label"> <spring:message
											text="missing" code="mupload977r.form.field.acuerdo" />
									</label>
									<div class="col-sm-8">
										<input id="acuerdo" name="acuerdo" type="text"
											class="form-control" placeholder="${uploadForm.acuerdo}"
											value="${uploadForm.acuerdo}">
									</div>
								</div>
							</div>
	
							<div class="row">
								<div class="form-group">
									<div class="col-sm-4">
										<spring:message text="missing"
											code="mupload977r.form.field.detalleLlamadas" />
									</div>
									<div class="col-sm-8">
										<form:checkbox path="detalleLlamadas" value="false" />
									</div>
								</div>
							</div>
	
							<div class="row">
	
								<div class="form-group">
									<div class="col-sm-4">
										<spring:message text="missing"
											code="mupload977r.form.field.detalleLlamadasRI" />
									</div>
									<div class="col-sm-8">
										<form:checkbox path="detalleLlamadasRI" value="false" />
									</div>
								</div>
							</div>
	
							<div class="row">
								<div class="form-group">
									<div class="col-sm-4">
										<spring:message text="missing"
											code="mupload977r.form.field.recargarFicheros" />
									</div>
									<div class="col-sm-8">
										<form:checkbox path="recargarFicheros" value="false" />
									</div>
								</div>
							</div>
	
							<!-- 					<div id="divAmbitos" class="row"> -->
							<!-- 						<div class="form-group"> -->
							<!-- 							<label for="reglas" class="col-sm-2 control-label"> -->
							<%-- 								<spring:message text="missing" code="mupload977r.form.field.ambitodellamada" /> --%>
							<!-- 							</label>  -->
							<!-- 							<div class="col-sm-10"> -->
							<%-- 								<form:checkboxes items="${ambitosDisponibles}"  --%>
							<%-- 													path="ambitos" --%>
							<%-- 													itemValue="id" --%>
							<%-- 													itemLabel="descAmbitoDeTraficoExt"  --%>
							<%-- 													delimiter="<br/>"  --%>
							<%-- 													/> --%>
							<!-- 							</div>  -->
							<!-- 						</div> -->
	
							<!-- 					</div> -->
	
	
							<table id="fileTable">
								<tr>
									<td><input name="files" type="file" multiple="multiple" /></td>
								</tr>
							</table>
							<br />
							<input type="submit" id="btnSubmit" name="btnSubmit"
								class="btn btn-primary"
								value="<spring:message text="missing" code="mupload977r.form.btn.upload.text" />" />
	
	
							<!-- Spring Security -->
	
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
	
	
						</form:form>
					</div>
					<!-- #formulario -->
	
	
	
	
					<div id="resultados"></div>
	
				</div>
				<!-- #977r-form-contenido -->
				
			</div>
	
		</div><!-- .container -->
	</div><!-- #wrap -->



	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

	<script type="text/javascript">
		$(function() {

			// 		$('#divAmbitos').hide();
			// 		$("input[name='ambitos']").removeAttr('checked');

			// 		$("input[name='detalleLlamadas']").change(function(){
			// 				// Si se marca, mostramos los ámbitos y los marcamos como 'checked'
			// 				if(this.checked){
			// 					$("input[name='ambitos']").prop('checked', 'checked');
			// 					$('#divAmbitos').show();
			// 				}else{
			// 					$("input[name='ambitos']").removeAttr('checked');
			// 					$('#divAmbitos').hide();
			// 				};
			// 		});

			var form = $("#uploadMultiple977RFilesForm");
			form.on('submit',
			function(e) {
				
				if($("#acuerdo").val().trim() == ""){
					alert("El nombre del Acuerdo NO puede estar vacío.")
					return false;
				}
				
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
						p_info.text('Cargando y procesando fichero(s) 977R...');
						$('#resultados')
								.empty()
								.addClass('text-center');
						$('#resultados')
								.append(p_info);

						var cl = new CanvasLoader('resultados');
						cl.setColor('#3f68a6'); // default is '#000000'
						cl.setShape('roundRect'); // default is 'oval'
						cl.setDiameter(114); // default is 40
						cl.setDensity(17); // default is 40
						cl.setRange(1); // default is 1.3
						cl.setSpeed(1); // default is 2
						cl.setFPS(10); // default is 24
						cl.show(); // Hidden by default
					},
					success : function(data, textStatus, errorThrown) {
						var table = $('<table></table>')
								.attr('id','tblres')
								.addClass('table')
								.addClass('table-striped');
						var thead = $('<thead></thead>');
						var tr = $('<tr></tr>');
						var tdh1 = $('<th></th>').text('ID');
						var tdh2 = $('<th></th>').text('Fichero 977r .zip');
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

						$('#resultados')
								.empty()
								.append(table);

						alert("Proceso de carga finalizado con éxito!");
					},
					error : function(jqXHR, textStatus, errorThrown) {
						alert("ERROR:[" + jqXHR+ "]["+ textStatus+ "]["+ errorThrown+ "]");
					}
				}).fail(
				function(jqXHR, textStatus) {
					alert("Request failed: "+ textStatus);
				});

				e.preventDefault();

			});
		});
	</script>
</body>
</html>
