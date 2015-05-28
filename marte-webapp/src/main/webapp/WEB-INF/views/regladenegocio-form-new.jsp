<%@ include file="/WEB-INF/views/include.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="regladenegocio.form.new.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		
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
					<spring:message text="missing"  code="regladenegocio.form.new.title" />
	          </h3>
 	       </div>
        
        	<!-- contenido -->
        	
	        <div><!-- inicio contenido -->
				<form:form method="POST" commandName="regladenegocioForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/regladenegocio/save.html">
		
					<div class="form-group">
						<label for="nombre" class="col-lg-2 control-label">
							<spring:message code="regladenegocio.form.field.nombre" />
						</label>
						<div class="col-lg-10">
							<input id="nombre" name="nombre" type="text" 
							class="form-control" 
							placeholder="${regladenegocio.nombre}" 
							value="${regladenegocio.nombre}">
						</div> 
					</div>
		
					<div class="form-group">
						<label for="definicion" class="col-lg-2 control-label">
							<spring:message code="regladenegocio.form.field.descripcion" />
						</label> 
						<div class="col-lg-10">
							<textarea class="form-control" id="descripcion" name="descripcion" rows="5" placeholder="${regladenegocio.descripcion}">${regladenegocio.descripcion}</textarea>
						</div> 
					</div>
		
					<div class="form-group">
						<label for="procedimiento" class="col-lg-2 control-label">
							<spring:message code="regladenegocio.form.field.procedimiento" />
						</label>
						<div class="col-lg-10">
							<input id="procedimiento" name="procedimiento" type="text" 
							class="form-control" 
							placeholder="${regladenegocio.procedimiento}" 
							value="${regladenegocio.procedimiento}">
						</div> 
					</div>
		
		
					<div class="form-group">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save" type="submit" class="btn btn-primary">
									<spring:message text="missing"  code="regladenegocio.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/regladenegocio/pages/1"
									id="btn-back" class="btn btn-default"> <spring:message text="missing" 
										code="regladenegocio.form.btn.back" />
								</a>
							</p>
						</div>
					</div>
					 
						
					 <!-- Spring Security -->
					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
				</form:form>
	        </div><!-- fin contenido -->


    	  </div>
		
		
		<%@ include file="/WEB-INF/views/include-goHome.jsp" %>
		

	</div><!-- .container -->


	<%@ include file="/WEB-INF/views/include-jquery.jsp" %>
	
</body>
</html>