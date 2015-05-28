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

			<div class="jumbotron">
				<h1>Upss...!</h1>
				<p><spring:message code="app.alert.operation.notyet.implemented" text="missing" /></p>
				<p>
					<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/index.jsp" role="button">Inicio</a>
				</p>
			</div>

		</div>
		<!-- .container -->

	</div>
	<!-- #wrap -->


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

</body>
</html>