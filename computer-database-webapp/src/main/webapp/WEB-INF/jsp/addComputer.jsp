<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />
<script>
 $(function() {
	 $.datepicker.setDefaults(
			 $.datepicker.regional["<spring:message code="dashboard.lang"/>"]
		);
	  $('.datepicker').datepicker({
	      dateFormat:"<spring:message code="format.show.datePattern" />"
	    }
	  );
	});
</script>

<spring:message code="label.name" var="name"/>
<spring:message code="label.introduced" var="introduced"/>
<spring:message code="label.discontinued" var="discontinued"/>
<spring:message code="label.company" var="company"/>
<spring:message code="add.select.nkcompany" var="nkcompany"/>
<spring:message code="format.datePattern" var="datePattern"/>
<spring:message code="DateValid.computerdto.introduced" var="patternError"/>
<spring:message code="Size.computerdto.name" var="sizeError"/>

<section id="main">

	<h1><spring:message code="dashboard.add"/></h1>
	
	<hr>

	<small><spring:message code="add.requirement"/></small>
	
	<c:if test="${displayDivAddError}">
		<div class="alert alert-danger">
			<spring:message code="add.problem"/> 
		</div>
	</c:if>
	
	<div class="container-fluid">
		<form:form class="form-horizontal" method="POST" action="AddComputer" modelAttribute="computerdto" id="creationForm">
			<form:hidden path="id" value="0"/>
			<div class="form-group has-feedback">
				<label class="col-sm-2 control-label">${name }* : </label>
				<div class="col-md-3">
					<form:input type="text" 
					data-validation="length" data-validation-length="2-255" data-validation-error-msg="${sizeError }"  
					name="computerName" id="computerName" path="name" class="form-control" placeholder="${name }"/>
					<form:errors path="name"/>
				</div>
			</div>
			
			<div class="form-group has-feedback">
				<label class="col-sm-2 control-label">${introduced } : </label>
				<div class="col-md-3">
					<form:input type="text" 
					data-validation="date" data-validation-optional="true" data-validation-format="${datePattern }" data-validation-error-msg="${patternError } : ${datePattern }" 
					id="introducedDate" path="introduced" class="datepicker form-control" placeholder="${introduced }"/>
					<form:errors path="introduced" />
				</div>
			</div>
			
			<div class="form-group has-feedback">
				<label class="col-sm-2 control-label">${discontinued } : </label>
				<div class="col-md-3">
					<form:input type="text"
					data-validation="date" data-validation-optional="true" data-validation-format="${datePattern }" data-validation-error-msg="${patternError } : ${datePattern }" 
					id="discontinuedDate" path="discontinued" class="datepicker form-control" placeholder="${discontinued }"/>
					<form:errors path="discontinued" />
				</div>
			</div>
			
			<div class="form-group" id="company">
				<label class="col-sm-2 control-label">${company } : </label>
				<div class="col-md-3">
					<div class="dropdown">
						<form:select path="companyId">
							<form:option value="0" label="${nkcompany }"/>
	      					<form:options items="${companyList}" itemValue="id" itemLabel="name"/>
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2">
						<input type="submit" class="btn btn-success" value=<spring:message code="dashboard.add"/>>
						<spring:message code="dashboard.or"/>
						<a href="Dashboard" class="btn btn-link"><spring:message code="dashboard.cancel"/></a>
					</div>
				</div>
			</div>			
		</form:form>
	</div>
</section>

<script> $.validate(); </script>

<jsp:include page="include/footer.jsp" />