<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


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


<section id="main">

	<h1>Add Computer</h1>
	
	<hr>

	<small>Fields marked with (*) are required</small>
	
	<c:if test="${displayDivAdd}">
		<div class="alert alert-success">Your computer have been added</div>
	</c:if>
	
	<c:if test="${displayDivAddError}">
		<div class="alert alert-danger">
			A problem occured during the creation of computer due to the following reason(s) : 
			<div>
				<c:if test="${errorName }">
					*invalid name
				</c:if>
			</div>
			<div>
				<c:if test="${errorIntroduced }">
					*invalid introduced date
				</c:if>
			</div>
			<div>
				<c:if test="${errorDiscontinued }">
					*invalid discontinued date
				</c:if>
			</div>
		</div>
	</c:if>
	
	
	<div class="container-fluid">
	
		<form:form class="form-horizontal" method="POST" action="AddComputer" modelAttribute="computerdto">
			<form:hidden path="id" value="0"/>
			<div class="form-group has-feedback" id="computerName">
				<label class="col-sm-2 control-label">Computer Name* : </label>
				<div class="col-md-3">
					<form:input type="text" path="name" class="form-control" placeholder="Computer Name"/>
					<span class="glyphicon form-control-feedback"></span>
				</div>
			</div>
			
			<div class="form-group has-feedback" id="introducedDate">
				<label class="col-sm-2 control-label">Introduced Date : </label>
				<div class="col-md-3">
					<form:input type="text" path="introduced" class="datepicker form-control" placeholder="Introduced Date"/>
					<span class="glyphicon form-control-feedback"></span>
				</div>
			</div>
			
			<div class="form-group has-feedback" id="discontinuedDate">
				<label class="col-sm-2 control-label">Discontinued Date : </label>
				<div class="col-md-3">
					<form:input type="text" path="discontinued" class="datepicker form-control" placeholder="Discontinued Date"/>
					<span class="glyphicon form-control-feedback"></span>
				</div>
			</div>
			
			<div class="form-group" id="company">
				<label class="col-sm-2 control-label">Company Name : </label>
				<div class="col-md-3">
					<div class="dropdown">
						<form:select path="companyId">
							<form:option value="0" label="--Not Known--"/>
	      					<form:options items="${companyList}" itemValue="id" itemLabel="name"/>
						</form:select>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2">
						<input type="submit" class="btn btn-success" value="Add Computer">
						or
						<a href="Dashboard" class="btn btn-link">Cancel</a>
					</div>
				</div>
			</div>			
		</form:form>
	</div>
</section>

<jsp:include page="include/footer.jsp" />