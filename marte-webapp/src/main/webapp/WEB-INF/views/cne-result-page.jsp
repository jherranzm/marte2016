<%@ include file="/WEB-INF/views/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message text="missing" code="cne.form.list.title" /></title>

	<%@ include file="/WEB-INF/views/include-css.jsp" %>

</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp" %>

	<div id="wrap">

		<div class="container">
	
			<%@ include file="/WEB-INF/views/cne-warning-errors.jsp" %>
	
			<c:if test="${not empty item}">
				<%@ include file="/WEB-INF/views/cne-result-item.jsp" %>
			</c:if>
	
	
			<div id="cne-info-item-deleted">
				<c:if test="${not empty deletedConsulta}">
					<div class="panel panel-primary">
	
						<!-- cabecera -->
	
						<div class="panel-heading">
							<h3 class="panel-title">
								<spring:message text="missing" code="cne.form.deleted.title" />
							</h3>
						</div>
	
						<!-- contenido -->
	
						<div>
							<!-- inicio contenido -->
							<div class="row">
								<div class="col-sm-3">
									<spring:message text="missing" code="cne.form.field.acuerdo" />
								</div>
								<div class="col-sm-9">${deletedConsulta.acuerdo}.</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<spring:message text="missing" code="cne.form.field.concepto" />
								</div>
								<div class="col-sm-9">${deletedConsulta.descConceptoFacturable}.</div>
							</div>
						</div>
						<!-- fin contenido -->
	
					</div>
					<!-- class="panel panel-primary" -->
				</c:if>
			</div>
	
	
			<div id="cne-panel-search">
				<div id="new-and-search">
					<div id="alta" class="col-sm-2">
						<a class="btn btn-primary btn-sm"
							href="${pageContext.request.contextPath}/cne/new.html"> 
							<spring:message text="missing" code="cne.form.new.title" />
						</a>
					</div>
					<div id="searchForm" class="col-sm-10">
						<form:form method="POST" 
							modelAttribute="searchForm"
							class="form-horizontal"
							action="${pageContext.request.contextPath}/cne/search.html">
							
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
											<spring:message text="missing" code="cne.form.btn.search" />
										</button>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div><!-- #new-and-search -->
			</div>
	
	
			<c:if test="${not empty cnes}">
	
				<!-- Importante: pagination! -->
				<c:set var="baseUrl" value="/cne/pages" />
		
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
								<th><small><spring:message code="cne.form.field.acuerdo" /></small></th>
								<th><small><spring:message code="cne.form.field.fechafactura" /></small></th>
								<th><small><spring:message code="cne.form.field.nombrecliente" /></small></th>
								<th><small><spring:message code="cne.form.field.desctipodeservicio" /></small></th>
								<th><small><spring:message code="cne.form.field.concepto" /></small></th>
								<th><small><spring:message code="cne.form.field.precioacuerdo" /></small></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cnes}" var="cne">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/cne/delete/${cne.id}.html"
										class="btn btn-primary btn-xs"> 
										<spring:message text="missing" code="cne.form.btn.del" />
									</a></td>
		
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.id}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.acuerdo}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.fechaFactura}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.nombreCliente}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.descTipoDeServicio}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.descConceptoFacturable}" /></small>
									</a></td>
									<td><a
										href="${pageContext.request.contextPath}/cne/edit/${cne.id}.html">
											<small><c:out value="${cne.precioAcuerdo}" /></small>
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