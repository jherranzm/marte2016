<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="informe.form.new.title" /></title>
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
		<div id="informe-saved">
			<div class="row">
				<div id="informe-saved-id" class="span6">
				</div>
				<div id="informe-saved-nombre" class="span6">
				</div>
			</div>
		</div>
		<div id="informe-form" class="panel panel-primary">
		
			<!-- cabecera -->
			
 	       <div id="informe-form-titulo" class="panel-heading">
	          <h3 class="panel-title">
					<spring:message text="missing" code="informe.form.new.title" />
					${informeForm.id}
	          </h3>
 	       </div>
        
        	<!-- contenido -->
        	
	        <div id="informe-form-contenido"><!-- inicio contenido -->
				<form:form id="informe-form-new" method="POST" commandName="informeForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/informe/save.html">
		
					<!-- Botones Save & Back -->
					<div class="form-group line">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save-up" type="submit" class="btn btn-primary">
									<spring:message text="missing" code="informe.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/consulta/pages/1"
									id="btn-back-up" class="btn btn-default"> 
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
							class="form-control" placeholder="Nombre" >
						</div> 
					</div>
		
					<div class="form-group">
						<label for="pestanyes" class="col-sm-2 control-label">
							<spring:message code="informe.form.field.pestanyes" />
						</label> 
						<div class="col-sm-5">
							<ol id="sortableD" class="connectedSortable list-group">
								<c:forEach items="${informeForm.disponibles}" var="pestanya">
									<li id="pestanya_${pestanya.id}" class="list-group-item">
									<span class="listItemLabel">${pestanya.nombre}</span>
									</li>
								</c:forEach>
							</ol>
						</div> 
						<div class="col-sm-5">
							<ol id="sortableA" class="connectedSortable list-group">
								<c:choose>
									<c:when test="${not empty informeForm.asignadas}">
										<c:forEach items="${informeForm.asignadas}" var="pestanya">
											<li id="pestanya_${pestanya.id}" class="list-group-item">
											<span class="listItemLabel">${pestanya.nombre}</span>
											</li>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<li id="pestanya_0" class="list-group-item">
											<span class="listItemLabel">Add tabs here...</span>
										</li>
									</c:otherwise>
								</c:choose>
							</ol>
							
							<input id="ordenadas" name="ordenadas" type="hidden" value="">
						</div> 
					</div>
					<!-- Botones Save & Back -->
					<div class="form-group line">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save-down" type="submit" class="btn btn-primary">
									<spring:message text="missing" code="informe.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/informe/pages/1"
									id="btn-back-down" class="btn btn-default"> 
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
    
    var form = $("#informe-form-new");
    
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