<%@ include file="/WEB-INF/views/include.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="role.form.list.title" text="missing" /></title>

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
	
			<div>
				<c:if test="${not empty nuevaConsulta}">
					<div class="panel panel-primary">
	
						<!-- cabecera -->
	
						<div class="panel-heading">
							<h3 class="panel-title">
								<spring:message text="missing" code="role.form.new.title" />
							</h3>
						</div>
	
						<!-- contenido -->
	
						<div>
							<!-- inicio contenido -->
							<div class="row">
								<div class="col-lg-3">
									<spring:message text="missing" code="role.form.field.nombre" />
								</div>
								<div class="col-lg-9">${nuevaConsulta.nombre}.</div>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<spring:message text="missing" code="role.form.field.comentarios" />
								</div>
								<div class="col-lg-9">${nuevaConsulta.comentarios}.</div>
							</div>
						</div>
						<!-- fin contenido -->
	
					</div>
					<!-- class="panel panel-primary" -->
				</c:if>
			</div>
	
	
			<div>
				<c:if test="${not empty deletedConsulta}">
					<div class="panel panel-primary">
	
						<!-- cabecera -->
	
						<div class="panel-heading">
							<h3 class="panel-title">
								<spring:message text="missing" code="role.form.deleted.title" />
							</h3>
						</div>
	
						<!-- contenido -->
	
						<div>
							<!-- inicio contenido -->
							<div class="row">
								<div class="col-lg-3">
									<spring:message text="missing" code="role.form.field.nombre" />
								</div>
								<div class="col-lg-9">${deletedConsulta.nombre}.</div>
							</div>
							<div class="row">
								<div class="col-lg-3">
									<spring:message text="missing" code="role.form.field.comentarios" />
								</div>
								<div class="col-lg-9">${deletedConsulta.comentarios}.</div>
							</div>
						</div>
						<!-- fin contenido -->
	
					</div>
					<!-- class="panel panel-primary" -->
				</c:if>
			</div>
	
	
			<div>
				<div id="new-and-search">
					<div id="alta" class="col-sm-2">
						<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/role/new.html"> 
							<spring:message text="missing" code="role.form.new.title" />
						</a>
					</div>
					<div id="searchForm" class="col-sm-10">
						<form:form method="POST" 
							modelAttribute="searchForm"
							class="form-horizontal"
							action="${pageContext.request.contextPath}/role/search.html">
							
						 <!-- Spring Security -->
						 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							
							<div class="form-group">
	
								<div class="row">
									<div class="col-sm-6">
										<input id="queBuscar" name="queBuscar" type="text"
											class="form-control">
									</div>
									<div class="col-sm-6">
										<button id="btn-save" type="submit" class="btn btn-primary btn-sm">
											<spring:message text="missing" code="role.form.btn.search" />
										</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div><!-- #new-and-search -->
			</div>
	
			
			<c:if test="${not empty roles}">
	
	
				<!-- Importante: pagination! -->
				<c:set var="baseUrl" value="/role/pages" />
		
				<c:url var="firstUrl" value="${baseUrl}/1" />
				<c:url var="lastUrl" value="${baseUrl}/${totalPages}" />
				<c:url var="prevUrl" value="${baseUrl}/${currentIndex - 1}" />
				<c:url var="nextUrl" value="${baseUrl}/${currentIndex + 1}" />
		
		
				<div id="pagination-up">
					<%@ include file="/WEB-INF/views/include-pagination.jsp"%>
				</div>
		
				<div>
					<table
						class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>#</th>
								<th>ID</th>
								<th><spring:message code="role.form.field.nombre" /></th>
								<th><spring:message code="role.form.field.comentarios" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roles}" var="role">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/role/delete/${role.id}.html"
										class="btn btn-primary btn-xs"> 
										<spring:message text="missing" code="role.form.btn.del" />
									</a></td>
		
									<td><a
										href="${pageContext.request.contextPath}/role/edit/${role.id}.html">
											<c:out value="${role.id}" />
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/role/edit/${role.id}.html">
											<c:out value="${role.nombre}" />
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/role/edit/${role.id}.html">
											<c:out value="${fn:substring(role.comentarios, 0, 20)}" />
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
				</div>
		
				<div id="pagination-down">
					<%@ include file="/WEB-INF/views/include-pagination.jsp"%>
				</div>
				
			</c:if>
	
		</div><!-- .container -->

	</div><!-- #wrap -->

	<%@ include file="/WEB-INF/views/footer.jsp"%>


	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

</body>
</html>