<%@ include file="/WEB-INF/views/include.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="informe.form.edit.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		

	<div class="container">

		<div id="informe-mensajeError">
			<c:if test="${not empty mensajeError}">
				<div class="alert alert-error">
					<p>${mensajeError}</p>
				</div>
			</c:if>
		</div>

		<div id="informe-form" class="panel panel-primary">
		
			<!-- cabecera -->
			
 	       <div id="informe-form-titulo" class="panel-heading">
	          <h3 class="panel-title">
					<spring:message text="missing" code="informe.form.edit.title" />
					${informeForm.id}
	          </h3>
 	       </div>
        
        
 	       <div class="well">
			<a href="${pageContext.request.contextPath}/informe/dup/${informeForm.id}"
				id="btn-back" class="btn btn-primary">
				<span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span> 
				<span><spring:message text="missing" code="informe.form.btn.duplicate" /></span>
			</a>
 	       </div>

        	<!-- contenido -->
        	
	        <div id="informe-form-contenido"><!-- inicio contenido -->
				<form:form method="POST" 
					id="informeForm"
					commandName="informeForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/informe/edit/${informeForm.id}.html">
		
					<!-- Botones Save & Back -->
					<div class="form-group">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save-up" type="submit" class="btn btn-primary btn-sm">
									<spring:message text="missing" code="informe.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/consulta/pages/1"
									id="btn-back-up" class="btn btn-primary btn-sm"> 
									<spring:message text="missing" code="informe.form.btn.back" />
								</a>
							</p>
						</div>
					</div>


					<div class="form-group">
						<label for="nombre" class="col-sm-2 control-label">
							<spring:message code="informe.form.field.nombre" />
						</label>
						<div class="col-sm-10">
							<input id="nombre" name="nombre" type="text" 
							class="form-control" placeholder="${informeForm.nombre}" value="${informeForm.nombre}">
						</div> 
					</div>
		
					<div class="form-group">
						<label for="pestanyes" class="col-sm-2 control-label">
							<spring:message code="informe.form.field.pestanyes" />
						</label> 
						
						<!-- Para que las pestanyes aparezcan checked, la entidad Pestanya tiene que 
							implementar los métodos equals() y hashcode() -->
						<div class="col-sm-4">
							<ol id="sortableD" class="connectedSortable list-group">
								<c:forEach items="${informeForm.disponibles}" var="pestanya">
									<li id="pestanya_${pestanya.id}" class="list-group-item">
									<span class="listItemLabel">${pestanya.nombre}</span>
									</li>
								</c:forEach>
							</ol>
						</div> 
						
						<div class="col-sm-1">
						</div>
						
						<div class="col-sm-4">
							<ol id="sortableA" class="connectedSortable list-group">
								<c:forEach items="${informeForm.asignadas}" var="pestanya">
									<li id="pestanya_${pestanya.id}" class="list-group-item">
									<span class="listItemLabel">${pestanya.nombre}</span>
									</li>
								</c:forEach>
							</ol>
							<input id="ordenadas" name="ordenadas" type="hidden" value="">
						</div> 
						
						<div class="col-sm-1">
						</div>
					</div>

					<!-- Botones Save & Back -->
					<div class="form-group">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save-down" type="submit" class="btn btn-primary btn-sm">
									<spring:message text="missing" code="informe.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/informe/pages/1"
									id="btn-back-down" class="btn btn-primary btn-sm"> 
									<spring:message text="missing" code="informe.form.btn.back" />
								</a>
							</p>
						</div>
					</div>


					 <!-- Spring Security -->
					 
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					 
					 
				</form:form>
	        </div><!-- fin contenido -->


    	  </div>





		<%@ include file="/WEB-INF/views/include-goHome.jsp"%>

	</div>



	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>
	
	<script>
  $(function() {
	  $( "#sortableD, #sortableA" ).sortable({
	      connectWith: ".connectedSortable"
	    }).disableSelection();
    
    var form = $("#informeForm");
    
    var submitInformeForm = function(){
    	//var lis = $("#sortable").sortable('serialize', {key : "pest"});
    	//alert(lis);
    	var los = $("#sortableA").sortable('toArray');
    	//alert(los);
    	
    	$("#ordenadas").val(los);
    	return true;
    } 
    
    form.on('submit', submitInformeForm);
  });
  </script>

</body>
</html>