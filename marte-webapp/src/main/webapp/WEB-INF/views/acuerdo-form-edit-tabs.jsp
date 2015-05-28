	<div class="row">
        <div class="span12">
            <div id="tab" class="btn-group" data-toggle="buttons-radio">
              <a href="#clientes" class="btn btn-default active" data-toggle="tab"><spring:message code="acuerdo.clientes" /></a>
              <a href="#servicios" class="btn btn-default" data-toggle="tab"><spring:message code="acuerdo.servicios" /></a>
              <a href="#condCuotas" class="btn btn-default" data-toggle="tab"><spring:message code="acuerdo.cuotas" /></a>
              <a href="#condTrafic" class="btn btn-default"  data-toggle="tab"><spring:message code="acuerdo.trafico" /></a>
            </div>
            
            
            <div class="tab-content">
                <div class="tab-pane active" id="clientes">
                    <br>
                    <p class="lead"><spring:message code="acuerdo.label.listaClientes" /></p>
                    <div class="row">

							<div>
								<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th><spring:message code="acuerdocliente.form.field.cuc" /></th>
											<th><spring:message code="acuerdocliente.form.field.tipoDoc" /></th>
											<th><spring:message code="acuerdocliente.form.field.cif" /></th>
											<th><spring:message code="acuerdocliente.form.field.nombreCliente" /></th>
											<th><spring:message code="acuerdocliente.form.field.fechaIniPeriodo" /></th>
											<th><spring:message code="acuerdocliente.form.field.fechaFinPeriodo" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${clientes}" var="cliente">
											<tr>
												<td><c:out value="${cliente.cuc}" /></td>
												<td><c:out value="${cliente.tipoDoc}" /></td>
												<td><c:out value="${cliente.cif}" /></td>
												<td><c:out value="${cliente.nombreCliente}" /></td>
												<td><c:out value="${cliente.fechaIniPeriodo}" /></td>
												<td><c:out value="${cliente.fechaFinPeriodo}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

                    </div>
                </div>
    
                <div class="tab-pane" id="servicios">
                    <br>
                    <p class="lead"><spring:message code="acuerdo.label.listaServicios" /></p>
                    <div class="row">

							<div>
								<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th><spring:message code="acuerdoservicio.form.field.tipoDeServicio" /></th>
											<th><spring:message code="acuerdoservicio.form.field.descTipoDeServicio" /></th>
											<th><spring:message code="acuerdoservicio.form.field.fechaIniPeriodo" /></th>
											<th><spring:message code="acuerdoservicio.form.field.fechaFinPeriodo" /></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${servicios}" var="servicio">
											<tr>
												<td><c:out value="${servicio.tipoDeServicio}" /></td>
												<td><c:out value="${servicio.descTipoDeServicio}" /></td>
												<td><c:out value="${servicio.fechaIniPeriodo}" /></td>
												<td><c:out value="${servicio.fechaFinPeriodo}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

                    </div>
                </div>
    
                <div class="tab-pane" id="condCuotas">
                    <br>
                    <p class="lead"><spring:message code="acuerdo.label.listaCuotas" />:<c:out value="${numConceptos}" /></p>
                    <div class="row">

							<div>
								<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th><spring:message code="acuerdoccff.form.field.tipoDeServicio" /></th>
											<th><spring:message code="acuerdoccff.form.field.conceptoFacturable" /></th>
											<th><spring:message code="acuerdoccff.form.field.codigoPersonalizacion" /></th>
											<th><spring:message code="acuerdoccff.form.field.descConceptoFacturable" /></th>
											<th><spring:message code="acuerdoccff.form.field.precioEspecial" /></th>
											<th><spring:message code="acuerdoccff.form.field.tipoPrecioEspecial" /></th>
											<th><spring:message code="acuerdoccff.form.field.importeOriginal" /></th>
											<th><spring:message code="acuerdoccff.form.field.importeAcuerdo" /></th>
											<th>#</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${conceptos}" var="concepto">
											<tr>
												<td><c:out value="${concepto.tipoDeServicio}" /></td>
												<td><c:out value="${concepto.conceptoFacturable}" /></td>
												<td><c:out value="${concepto.codigoPersonalizacion}" /></td>
												<td><c:out value="${concepto.descConceptoFacturable}" /></td>
												<td><c:out value="${concepto.precioEspecial}" /></td>
												<td><c:out value="${concepto.tipoPrecioEspecial}" /></td>
												<td><c:out value="${concepto.importeOriginal}" /></td>
												<td><c:out value="${concepto.importeAcuerdo}" /></td>
												<td><a
													href="${pageContext.request.contextPath}/acuerdoccff/edit/${concepto.id}.html"
													class="btn btn-primary btn-xs"> 
													<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

                    </div>
                </div>
     
                <div class="tab-pane" id="condTrafic">
                    <br>
                    <p class="lead"><spring:message code="acuerdo.label.listaTraficos" />:<c:out value="${numTraficos}" /></p>
                    <div class="row">

							<div>
								<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th><spring:message code="acuerdotrf.form.field.ambitoDeTrafico" /></th>
											<th><spring:message code="acuerdotrf.form.field.descAmbitoDeTrafico" /></th>
											<th><spring:message code="acuerdotrf.form.field.precioEspecial" /></th>
											<th><spring:message code="acuerdotrf.form.field.tipoDescuento" /></th>
											<th><spring:message code="acuerdotrf.form.field.precioPorMinuto" /></th>
											<th>#</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${traficos}" var="trafico">
											<tr>
												<td><c:out value="${trafico.ambitoDeTrafico}" /></td>
												<td><c:out value="${trafico.descAmbitoDeTrafico}" /></td>
												<td><c:out value="${trafico.precioEspecial}" /></td>
												<td><c:out value="${trafico.tipoDescuento}" /></td>
												<td><c:out value="${trafico.precioPorMinuto}" /></td>
												<td><a
													href="${pageContext.request.contextPath}/acuerdotrf/edit/${trafico.id}.html"
													class="btn btn-primary btn-xs"> 
													<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

                    </div>
                </div>
                
            </div>
        </div>
    </div>