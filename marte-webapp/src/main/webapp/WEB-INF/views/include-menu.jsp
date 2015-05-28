<!-- Docs master nav -->
<header class="navbar navbar-inverse navbar-static-top bs-docs-nav"
	id="top" role="banner">
	<div class="container">
		<div class="navbar-header">
			<!-- 			<button class="navbar-toggle" type="button" data-toggle="collapse" -->
			<!-- 				data-target=".bs-navbar-collapse"> -->
			<!-- 				<span class="sr-only">Toggle navigation</span> <span -->
			<!-- 					class="icon-bar"></span> <span class="icon-bar"></span> <span -->
			<!-- 					class="icon-bar"></span> -->
			<!-- 			</button> -->

			<a href="${pageContext.request.contextPath}/index.jsp"
				class="navbar-brand"> <spring:message text="missing"
					code="app.name" />
			</a>
		</div>

		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="${pageContext.request.contextPath}/index.jsp"> <spring:message
							code="go.home" text="missing" />
				</a></li>

				<sec:authorize access="hasRole('ROLE_ADMIN')">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><spring:message code="menu.informes"
								text="missing" /> <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/role/pages/1">
									<spring:message code="role.form.list.title" text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/usuario/pages/1">
									<spring:message code="usuario.form.list.title" text="missing" />
							</a></li>
							<li class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/consulta/pages/1">
									<spring:message code="consulta.form.list.title" text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/pestanya/pages/1">
									<spring:message code="pestanya.form.list.title" text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/informe/pages/1"> <spring:message
										code="informe.form.list.title" text="missing" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/regladenegocio/pages/1">
									<spring:message code="regladenegocio.form.list.title"
										text="missing" />
							</a></li>
						</ul></li>

				</sec:authorize>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><spring:message code="menu.utilidades"
							text="missing" /> <b class="caret"></b></a>
					<ul class="dropdown-menu">

						<sec:authorize access="hasRole('ROLE_ADMIN')">

							<li><a
								href="${pageContext.request.contextPath}/generaInforme/new">
									<spring:message code="genera.informe.form.list.title"
										text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/generaInforme/apply">
									<spring:message code="genera.informe.form.apply.title"
										text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/acuerdo/pages/1">
									<spring:message code="acuerdo.form.list.title"
										text="missing" />
							</a></li>
							<li class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/escenario/muploadescenariofiles-form.html">
									<spring:message code="escenario.form.list.title" text="missing" />
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/escenario/copy-escenario-form.html">
									<spring:message code="escenario.form.new.title" text="missing" />
							</a></li>
							<li class="divider"></li>

						</sec:authorize>

						<li><a
							href="${pageContext.request.contextPath}/ii/muploadiifiles-form.html">
								<spring:message code="ii.form.list.title" text="missing" />
						</a></li>
						<li><a
							href="${pageContext.request.contextPath}/hz/muploadhzfiles-form.html">
								<spring:message code="hz.form.list.title" text="missing" />
						</a></li>

						<sec:authorize access="hasRole('ROLE_ADMIN')">

							<li class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/977r/mupload977rfiles-form.html">
									<spring:message code="977r.form.list.title" text="missing" />
							</a></li>
							<li class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/segmentacion"> <spring:message
										code="segmentacion.form.new.title" text="missing" /></a></li>
							<li><a
								href="${pageContext.request.contextPath}/seg/list"> <spring:message
										code="segmentacion.form.consultas" text="missing" /></a></li>

						</sec:authorize>

					</ul></li>

				<li><a href="#about"> <spring:message code="go.about"
							text="missing" />
				</a></li>
				<li><a href="#contact"> <spring:message code="go.contact"
							text="missing" />
				</a></li>
			</ul>


			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<img src="${pageContext.request.contextPath}/img/icons/lang_es.png">
					<b class="caret"></b></a>
					<ul class="dropdown-menu">

						<li><a href="?lang=en"><img
								src="${pageContext.request.contextPath}/img/icons/lang_en.png"></a>
						</li>
						<li><a href="?lang=es"><img
								src="${pageContext.request.contextPath}/img/icons/lang_es.png"></a>
						</li>
						<li><a href="?lang=ca"><img
								src="${pageContext.request.contextPath}/img/icons/lang_ca.png"></a>
						</li>
					</ul>
				</li>
				
				<li class="btn-vertical-align-middle">
				
					<sec:authorize access="isAuthenticated()">
						<form class="form-inline"
							action="${pageContext.request.contextPath}/logout" method="POST">
							<div>&nbsp;</div>
							<button type="submit" class="btn btn-xs btn-vertical-align-middle">
								<span class="glyphicon glyphicon-off"></span> 
								<sec:authentication property="principal.username" />
							</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
					</sec:authorize>
				</li>
			</ul>
		</nav>
	</div>
</header>