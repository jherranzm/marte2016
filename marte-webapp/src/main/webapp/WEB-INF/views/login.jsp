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

			<div class="col-md-4">
			</div>
			
			<div class="col-md-4"> 
				<div><!-- inicio contenido -->
				<form:form method="POST"
					name="f" 
					class="form-horizontal"
					action="${pageContext.request.contextPath}/login">
					
					<c:if test="${param.error != null}">        
				        <p>
				            Invalid username and password.
				        </p>
				    </c:if>
				    <c:if test="${param.logout != null}">       
				        <p>
				            You have been logged out.
				        </p>
				    </c:if>
	
						<div class="form-group">
							<div class="col-md-12">
								<input id="username" name="username" type="text" 
								class="form-control input-sm" 
								placeholder="username" 
								value="">
							</div> 
						</div>
				    
						<div class="form-group">
							<div class="col-md-12">
								<input id="password" name="password" type="password" 
								class="form-control input-sm" 
								placeholder="password" 
								value="">
							</div> 
						</div>
				    
						<div class="form-group">
						   <div class="col-md-12">
						     <button id="btn-save" type="submit" class="btn btn-primary btn-sm pull-right">
								Login
						     </button>
						   </div>
						 </div>
						 
				    <input type="hidden"
						name="${_csrf.parameterName}"
						value="${_csrf.token}"/>
				  
				</form:form>
				
			</div><!-- fin contenido -->			
			</div>

			<div class="col-md-4">
			</div>
		</div><!-- .container -->

	</div><!-- #wrap -->


	<%@ include file="/WEB-INF/views/footer.jsp"%>

	<%@ include file="/WEB-INF/views/include-jquery.jsp"%>

</body>
</html>