<%@ include file="/WEB-INF/views/include.jsp"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message text="missing" code="muploadcargaajustes.form.title.text" /></title>

<%@ include file="/WEB-INF/views/include-css.jsp"%>

</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp"%>


	<div id="wrap">

		<div class="container">
		
			<div id="cargaajustes-form" class="panel panel-primary">
	
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message text="missing" code="muploadcargaajustes.form.title.text" />
					</h3>
				</div>
	
				<div class="panel-body" id="formulario">
					<form:form method="post"
						id="uploadMultipleCargaAjustesFilesForm"
						action="${pageContext.request.contextPath}/cargaajustes?${_csrf.parameterName}=${_csrf.token}"
						modelAttribute="uploadForm" 
						enctype="multipart/form-data">
		
						<p class="bg-info webapp-margin">
							<spring:message text="missing" code="muploadcargaajustes.form.info.text" />
						</p>
						
		
		
						<table id="fileTable">
							<tr>
								<td><input name="files" type="file" multiple="multiple"/></td>
							</tr>
						</table>
						<br />
						<input 
							type="submit"
							class="btn btn-primary"
							value="<spring:message text="missing" code="muploadcargaajustes.form.btn.upload.text" />" />
		
							 
							 <!-- Spring Security -->
							 
							 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							 
							 
					</form:form>
				</div>
	
				<div id="resultados"></div>
	
			</div>
		</div>

	</div><!-- #wrap -->



	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

<script type="text/javascript">
	$(function() {
		
		var form = $("#uploadMultipleCargaAjustesFilesForm");
		form.on('submit', function(e){
			var fd = new FormData(this);
			$.ajax({
				url : form.attr('action'),
				type: "POST",
				data: fd,
				dataType : 'json',
				processData: false,  // tell jQuery not to process the data
				contentType: false,   // tell jQuery not to set contentType
				beforeSend : function() {
					
					$("#formulario").hide();
					
					var p_info = $('<p></p>');
					p_info.text('Cargando y procesando fichero(s) de Carga de Ajustes Plana...');
					$('#resultados').empty().addClass('text-center');
					$('#resultados').append(p_info);
					
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
				success: function ( data, textStatus, errorThrown ) {
					var table = $('<table></table>').attr('id', 'tblres').addClass('table').addClass('table-striped');
					var thead = $('<thead></thead>');
					var tr = $('<tr></tr>');
					var tdh1 = $('<th></th>').text('ID');
					var tdh2 = $('<th></th>').text('Mensaje');
					tr.append(tdh1);
					tr.append(tdh2);
					thead.append(tr);
					table.append(thead);
					
					var index = 0;
					for (i in data.resultados){
						index = index+1;
						var td1 = $('<td></td>').append(index);
						var td2 = $('<td></td>').append(data.resultados[i]);
					    var row = $('<tr></tr>');
					    row.append(td1);
					    row.append(td2);
					    table.append(row);
					}
					
					$('#resultados').empty().append(table);
					
					alert( "Proceso de carga finalizado con éxito!" );
				},
				error: function ( jqXHR, textStatus, errorThrown ) {
		            alert( "ERROR:["+jqXHR+"]["+textStatus+"]["+errorThrown+"]" );
		        }
			}).fail(function( jqXHR, textStatus ) {
				  alert( "Request failed: " + textStatus );
			  });
			
			e.preventDefault();
			
		});
	});

	</script>

</body>
</html>
