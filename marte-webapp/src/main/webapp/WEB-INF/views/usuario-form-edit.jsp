<%@ include file="/WEB-INF/views/include.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="usuario.form.edit.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>
		

	<div class="container">

		<div id="usuario-mensajeError">
			<c:if test="${not empty mensajeError}">
				<div class="alert alert-error">
					<p>${mensajeError}</p>
				</div>
			</c:if>
		</div>

		<div id="usuario-form" class="panel panel-primary">
		
			<!-- cabecera -->
			
 	       <div id="usuario-form-titulo" class="panel-heading">
	          <h3 class="panel-title">
					<spring:message text="missing" code="usuario.form.edit.title" />
					${usuarioForm.id}
	          </h3>
 	       </div>
        
        	<!-- contenido -->
        	
	        <div id="usuario-form-contenido"><!-- inicio contenido -->
				<form:form method="POST" commandName="usuarioForm"
					 class="form-horizontal"
					action="${pageContext.request.contextPath}/usuario/edit/${usuarioForm.id}.html">
		
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.username" />
						</label>
						<div class="col-sm-10">
							<input id="username" name="username" type="text" 
							class="form-control" placeholder="${usuarioForm.username}" value="${usuarioForm.username}">
						</div> 
					</div>
		
					<div class="form-group">
						<label for="nombre" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.nombre" />
						</label>
						<div class="col-sm-10">
							<input id="nombre" name="nombre" type="text" 
							class="form-control" placeholder="${usuarioForm.nombre}" value="${usuarioForm.nombre}">
						</div> 
					</div>
		
					<div class="form-group">
						<label for="apellido1" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.apellido1" />
						</label>
						<div class="col-sm-10">
							<input id="apellido1" name="apellido1" type="text" 
							class="form-control" placeholder="${usuarioForm.apellido1}" value="${usuarioForm.apellido1}">
						</div> 
					</div>
		
					<div class="form-group">
						<label for="apellido2" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.apellido2" />
						</label>
						<div class="col-sm-10">
							<input id="apellido2" name="apellido2" type="text" 
							class="form-control" placeholder="${usuarioForm.apellido2}" value="${usuarioForm.apellido2}">
						</div> 
					</div>
					
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.email" />
						</label>
						<div class="col-sm-10">
							<input id="email" name="email" type="text" 
							class="form-control" placeholder="${usuarioForm.email}" value="${usuarioForm.email}">
						</div> 
					</div>
					
					<div class="form-group">
						<label for="enabled" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.enabled" />
						</label>
						<div class="col-sm-10">
							<form:checkbox path="enabled" value="false" />
						</div> 
					</div>
					
		
					<div class="form-group">
						<label for="roles" class="col-sm-2 control-label">
							<spring:message code="usuario.form.field.roles" />
						</label> 
						
						<!-- Para que las roles aparezcan checked, la entidad Pestanya tiene que 
							implementar los métodos equals() y hashcode() -->
						<div class="col-sm-10">
							<form:checkboxes 
												items="${rolesDisponibles}" 
												path="roles"
												itemValue="id"
												itemLabel="nombre" 
												delimiter="<br/>"
												/>
						</div> 
					</div>

					<!-- Botones Save & Back -->
					<div class="form-group">
						<div class="col-md-offset-4 col-md-4">
							<p class="text-center">
								<button id="btn-save" type="submit" class="btn btn-primary">
									<spring:message text="missing" code="usuario.form.btn.save" />
								</button>
								<a href="${pageContext.request.contextPath}/consulta/pages/1"
									id="btn-back" class="btn btn-default"> 
									<spring:message text="missing" code="usuario.form.btn.back" />
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

</body>
</html>