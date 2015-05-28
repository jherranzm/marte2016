<%@ include file="/WEB-INF/views/include.jsp"%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message text="missing" code="genera.informe.success.title.text" /></title>


<%@ include file="/WEB-INF/views/include-css.jsp"%>


</head>
<body>

	<%@ include file="/WEB-INF/views/include-menu.jsp"%>

	<div id="wrap">

		<div class="container">

			<div class="page-header">
				<h1>
					<spring:message text="missing" code="genera.informe.success.title.text" />
				</h1>
			</div><!-- .page-header -->

			<c:if test="${not empty FORM.resultados}">
				<div id="resultado">
					<h3 style="color: blue;">
						<spring:message text="missing" code="genera.informe.success.info.resultados" />
					</h3>
					<br>
					<table>
						<c:forEach items="${FORM.resultados}" var="resultado">
							<tr><td>${resultado}</td></tr>
						</c:forEach>
					</table>
				</div><!-- #resultado -->
			</c:if>

			<c:if test="${not empty FORM.regularizaciones}">
				<div id="regularizacion">
					<h3 style="color: blue;">
						<spring:message text="missing" code="genera.informe.success.info.resultados" />
					</h3>
					<br>
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th><spring:message text="missing" code="regularizacion.field.acuerdo" /></th>
								<th><spring:message text="missing" code="regularizacion.field.cif" /></th>
								<th><spring:message text="missing" code="regularizacion.field.importeBoe" /></th>
								<th><spring:message text="missing" code="regularizacion.field.precioAcuerdo" /></th>
								<th><spring:message text="missing" code="regularizacion.field.facturadoTotal" /></th>
								<th><spring:message text="missing" code="regularizacion.field.regularizacion" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${FORM.regularizaciones}" var="regularizacion">
							<tr>
								<td>
									<c:out value="${regularizacion.acuerdo}" />
								</td>
								<td>
									<c:out value="${regularizacion.cif}" />
								</td>
								<td>
									<c:out value="${regularizacion.importeBoe}" />
								</td>
								<td>
									<c:out value="${regularizacion.precioAcuerdo}" />
								</td>
								<td>
									<c:out value="${regularizacion.facturadoTotal}" />
								</td>
								<td>
									<c:out value="${regularizacion.regularizacion}" />
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div><!-- #resultado -->
			</c:if>

			<c:if test="${not empty FORM.urls}">
				<div id="urls">
					<h3 style="color: blue;">
						<spring:message text="missing" code="genera.informe.success.info.enlaces" />
					</h3>
					<br>
					<c:forEach items="${FORM.urls}" var="url">
						<li><a href="${pageContext.request.contextPath}/getFile/file/${url.fileName}/${url.fileExt}">${url.fileName}</a> - ${url.fileSize} bytes</li>
					</c:forEach>
				</div><!-- #urls -->
			</c:if>

		</div><!-- .container -->

	</div><!-- wrap -->



	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

</body>
</html>