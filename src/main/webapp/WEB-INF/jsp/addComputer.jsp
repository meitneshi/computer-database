<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />
<script>
  $(function() {
    $(".datepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();
  });
</script>


<script>
$(document).ready(function(){
	$("#creationForm").validate(
	{
		rules: {
			computerName: {
				minlength: 2,
				required: true
			},
			introducedDate: {
				dateController : true
			},
			discontinuedDate: {
				dateController : true
			}
		},
		highlight: function(element) {
			$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			$(element).next('.glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
		},
		unhighlight: function(element) {
			$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			$(element).next('.glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
		},
	});
	$.validator.addMethod(
    	"dateController",
    	function(value, element) {
        	return value.match(/^$/) || value.match(/^(\d{4})([\/-])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$/);
        }, "Invalid Date Format. Must be like \"yyyy-MM-dd\"");

});
</script>

<spring:message code="label.name" var="name"/>
<spring:message code="label.introduced" var="introduced"/>
<spring:message code="label.discontinued" var="discontinued"/>
<spring:message code="add.select.nkcompany" var="nkcompany"/>



<section id="main">

	<h1><spring:message code="dashboard.add"/></h1>
	
	<hr>

	<small><spring:message code="add.requirement"/></small>
	
	<c:if test="${displayDivAdd }">
		<div class="alert alert-success"><spring:message code="add.success"/></div>
	</c:if>
	
	<c:if test="${displayDivAddError}">
		<div class="alert alert-danger">
			<spring:message code="add.problem"/> 
		</div>
	</c:if>
	
	<div class="container-fluid">
		<form:form class="form-horizontal" method="POST" action="AddComputer" modelAttribute="computerdto">
			<form:hidden path="id" value="0"/>
			<div class="form-group has-feedback" id="computerName">
				<label class="col-sm-2 control-label"><spring:message code="label.name"/>* : </label>
				<div class="col-md-3">
					<form:input type="text" path="name" class="form-control" placeholder="${name }"/>
					<form:errors path="name"/>
				</div>
			</div>
			
			<div class="form-group has-feedback" id="introducedDate">
				<label class="col-sm-2 control-label"><spring:message code="label.introduced"/> : </label>
				<div class="col-md-3">
					<form:input type="text" path="introduced" class="datepicker form-control" placeholder="${introduced }"/>
					<form:errors path="introduced" />
				</div>
			</div>
			
			<div class="form-group has-feedback" id="discontinuedDate">
				<label class="col-sm-2 control-label"><spring:message code="label.discontinued"/> : </label>
				<div class="col-md-3">
					<form:input type="text" path="discontinued" class="datepicker form-control" placeholder="${discontinued }"/>
					<form:errors path="discontinued" />
				</div>
			</div>
			
			<div class="form-group" id="company">
				<label class="col-sm-2 control-label"><spring:message code="label.company"/> : </label>
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

<jsp:include page="include/footer.jsp" />