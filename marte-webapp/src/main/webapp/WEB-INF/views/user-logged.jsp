
<div>
	<!-- inicio contenido -->

	<sec:authorize access="isAuthenticated()">
		Usuario : <sec:authentication property="principal.username" />
	</sec:authorize>
	<sec:authorize access="! isAuthenticated()">
		<form:form method="POST" name="f" class="form-horizontal"
			action="${pageContext.request.contextPath}/login">

			<div class="form-group">
				<div class="col-md-12">
					<input id="username" name="username" type="text"
						class="form-control input-sm" placeholder="username" value="">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-12">
					<input id="password" name="password" type="password"
						class="form-control input-sm" placeholder="password" value="">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-12">
					<button id="btn-save" type="submit"
						class="btn btn-primary btn-sm pull-right">Login</button>
				</div>
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form:form>
	</sec:authorize>



</div>
<!-- fin contenido -->