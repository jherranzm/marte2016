<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="acuerdo.form.edit.title" /></title>
	<%@ include file="/WEB-INF/views/include-css.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		
	<div class="container">
		<div id="acuerdo-mensajeError">
			<c:if test="${not empty mensajeError}">
				<div class="alert alert-error">
					<p>${mensajeError}</p>
				</div>
			</c:if>
		</div>
		
        	<!-- contenido -->
        	
	        <div id="acuerdo-form-contenido"><!-- inicio contenido -->
	        
	        	<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/pages/1">Acuerdos</a></li>
					<li class="active"><c:out value="${acuerdoForm.acuerdo}"></c:out></li>
				</ol>
	        
	        	<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}/acuerdo/clientes/${acuerdoForm.id}.html">Clientes</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/servicios/${acuerdoForm.id}.html">Servicios</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/cuotas/${acuerdoForm.id}.html">Cuotas</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/trafico/${acuerdoForm.id}.html">Tráfico Estándar</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/traficori/${acuerdoForm.id}.html">Tráfico Red Inteligente</a></li>
					<li><a href="${pageContext.request.contextPath}/acuerdo/cne/${acuerdoForm.id}.html">Cuota NO Estándar</a></li>
				</ol>
					
					<%@ include file="/WEB-INF/views/acuerdo-form-edit-tabs.jsp" %>
		
	        </div><!-- fin contenido -->
    	  </div>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>
	
	<script>
  $(function() {
	  $( "#sortableD, #sortableA" ).sortable({
	      connectWith: ".connectedSortable"
	    }).disableSelection();
    
    var form = $("#acuerdoForm");
    
    var submitInformeForm = function(){
    	var los = $("#sortableA").sortable('toArray');
    	$("#ordenadas").val(los);
    	return true;
    } 
    
    form.on('submit', submitInformeForm);
  });
  </script>
</body>
</html>