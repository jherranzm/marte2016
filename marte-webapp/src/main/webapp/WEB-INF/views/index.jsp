<%@ include file="/WEB-INF/views/include.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<%@ include file="/WEB-INF/views/include-css.jsp"%>
</head>

<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp"%>

	<div id="wrap">

		<div class="container">
		
			<div id="left" class="col-md-10">
				<div id="options-open" class="col-md-4">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/ii/muploadiifiles-form.html"
							 class="list-group-item">
							<spring:message code="ii.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/hz/muploadhzfiles-form.html"
							 class="list-group-item">
							<spring:message code="hz.form.list.title" text="missing" />
						</a>
					</div>
				</div>
	
				<div id="options-protected" class="col-md-4">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/consulta/pages/1"
							 class="list-group-item">
							<spring:message code="consulta.form.list.title" text="missing" />
							<!-- 					Lista Consultas -->
						</a>
						<a href="${pageContext.request.contextPath}/pestanya/pages/1"
							 class="list-group-item">
							<spring:message code="pestanya.form.list.title" text="missing" />
							<!-- 					Lista Pestañas -->
						</a>
						<a href="${pageContext.request.contextPath}/informe/list.html"
							 class="list-group-item">
							<spring:message code="informe.form.list.title" text="missing" />
							<!-- 					Lista Informes -->
						</a>
						<a href="${pageContext.request.contextPath}/regladenegocio/list.html"
							 class="list-group-item">
							<spring:message code="regladenegocio.form.list.title" text="missing" />
							<!-- 					Lista Reglas de Negocio -->
						</a>
					</div>
					</sec:authorize>
					
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/acuerdo/pages/1"
							 class="list-group-item">
							<spring:message code="acuerdo.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/escenario/muploadescenariofiles-form.html"
							 class="list-group-item">
							<spring:message code="escenario.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/escenario/copy-escenario-form.html"
							 class="list-group-item">
							<spring:message code="escenario.form.new.title" text="missing" />
						</a>
					</div>
					</sec:authorize>
				</div>
	
				<div id="options-loaders" class="col-md-4">
					
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/generaInforme/new"
							 class="list-group-item">
							<spring:message code="genera.informe.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/generaInforme/apply"
							 class="list-group-item">
							<spring:message code="genera.informe.form.apply.title" text="missing" />
						</a>
					</div>
					</sec:authorize>
					
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/977r/mupload977rfiles-form.html"
							 class="list-group-item">
							<spring:message code="977r.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/cargaajustes"
							 class="list-group-item">
							<spring:message code="cargaajustes.form.list.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/cargaprefijos"
							 class="list-group-item">
							<spring:message code="muploadcargaprefijos.form.list.title" text="missing" />
						</a>
					</div>
					</sec:authorize>
					
					<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="list-group">
						<a href="${pageContext.request.contextPath}/segmentacion"
							 class="list-group-item">
							<spring:message code="segmentacion.form.new.title" text="missing" />
						</a>
						<a href="${pageContext.request.contextPath}/seg/list"
							 class="list-group-item">
							<spring:message code="segmentacion.form.consultas" text="missing" />
						</a>
					</div>
					</sec:authorize>
				</div>
			</div>
			<div id="right" class="col-md-2">
				<div id="userConnected">
				
						<%@ include file="/WEB-INF/views/user-logged.jsp"%>
						
				</div><!-- #userConnected -->
			</div>
			
			

		</div><!-- .container -->

	</div><!-- #wrap -->


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

</body>
</html>