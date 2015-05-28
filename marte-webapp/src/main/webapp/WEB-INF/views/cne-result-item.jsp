
	    <div class="well alert alert-dismissible" role="alert">
	
	        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
			
	               <c:choose>
	                <c:when test="${op=='u'}">
	                	<h3><span class="label label-primary">
	                	<spring:message text="missing" code="cne.op.update.title" />
	                	</span></h3>
	            	</c:when>
	                <c:when test="${op=='c'}">
	                	<h3><span class="label label-primary">
		                <spring:message text="missing" code="cne.op.create.title" />
	                	</span></h3>
	            	</c:when>
	                <c:when test="${op=='d'}">
	                	<h3><span class="label label-primary">
	    	            <spring:message text="missing" code="cne.op.delete.title" />
	                	</span></h3>
	                </c:when>
	                <c:otherwise>
	                	<h3><span class="label label-primary">
	        	        <spring:message text="missing" code="cne.op.unknownop.title" />
	                	</span></h3>
	                </c:otherwise>
	               </c:choose>
	    	
	                
	           
	           
	            <div id="cne-result-item-body">
	            
					<div id="cne-result-item-body-field-acuerdo" class="col-md-3">
					   <strong><spring:message text="missing" code="cne.form.field.acuerdo" /></strong>
					   <span>${item.acuerdo}</span>
					 </div><!-- .col-md-3 -->
					 
					 <div class="clearfix"></div>
	            
					<div id="cne-result-item-body-field-fechafactura" class="col-md-3">
					   <strong><spring:message text="missing" code="cne.form.field.fechafactura" /></strong>
					   <span>${item.fecha}</span>
					 </div><!-- .col-md-3 -->
	            
	              	<div class="clearfix"></div>
	
					<div id="cne-result-item-body-field-nombrecliente" class="col-md-11">
					   <strong><spring:message text="missing" code="cne.form.field.nombrecliente" /></strong>
					   <span>${item.acuerdoCliente.nombreClienteLargo}</span>
					 </div><!-- .col-md-11 -->
	            
	              	<div class="clearfix"></div>
	              	
					<div id="cne-result-item-body-field-desctipodeservicio" class="col-md-5">
					   <strong><spring:message text="missing" code="cne.form.field.desctipodeservicio" /></strong>
					   <span>${item.objServicio.servicio}</span>
					 </div><!-- .col-md-5 -->
	            
	              	<div class="clearfix"></div>
	              	
					<div id="cne-result-item-body-field-concepto" class="col-md-11">
					   <strong><spring:message text="missing" code="cne.form.field.concepto" /></strong>
					   <span>${item.descConcepto}</span>
					 </div><!-- .col-md-11 -->
	            
	              	<div class="clearfix"></div>
	              	
					<div id="cne-result-item-body-field-precioacuerdo" class="col-md-4">
					   <strong><spring:message text="missing" code="cne.form.field.precioacuerdo" /></strong>
					   <span>${item.importe}</span>
					   <strong>EUR</strong>
					 </div><!-- .col-md-11 -->
	            
	              	<div class="clearfix"></div>
	              	
					<div id="cne-result-item-body-field-comentarios" class="col-md-11">
					   <strong><spring:message text="missing" code="cne.form.field.comentarios" /></strong>
					   <span>${item.comentarios}</span>
					 </div><!-- .col-md-11 -->
	            
	              	<div class="clearfix"></div>
	            </div><!-- .panel-body -->
	            
	            <div id="cne-result-item-footer">
	                <p><strong><spring:message text="missing" code="cne.op.ended" /></strong></p>
	            </div>
	    
	    </div><!-- .alert -->

