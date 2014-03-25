<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

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
			$(element).closest('.control-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element.text('OK!').addClass('valid').closest('.control-group').removeClass('error').addClass('success');
		}
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
	
	<c:if test="${displayDiv}">
		<div class="alert alert-success">Your computer have been added</div>
	</c:if>
	
	
	<div class="container-fluid">
		<form method="POST" action="/computer_database/AddComputer" id="creationForm" accept-charset=utf-8>
			<div class="row" id="computerName">
				<div class="col-md-2"><strong>Computer Name* : </strong></div>
				<div class="col-md-3">
					<input type="text" name="computerName" id="computerName" class="form-control" placeholder="Computer Name" required/>
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="introducedDate">
				<div class="col-md-2"><strong>Introduced Date : </strong></div>
				<div class="col-md-3">
					<input type="text" name="introducedDate" class="datepicker form-control" placeholder="Introduced Date">
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="discontinuedDate">
				<div class="col-md-2"><strong>Discontinued Date : </strong></div>
				<div class="col-md-3">
					<input type="text" name="discontinuedDate" class="datepicker form-control" placeholder="Discontinued Date">
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="company">
				<div class="col-md-2"><strong>Company Name : </strong></div>
				<div class="col-md-3">
					<div class="dropdown">
						<select id="companies" name="company">
							<option value="0">--Not Known--</option>
							<c:forEach items="${companyList}" var="company">
								<option value="${company.id }" >
									<c:out value="${company.name }"/>
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>

			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2">
						<input type="submit" class="btn btn-success" value="Add Computer">
						or
						<a href="/computer_database/Dashboard" class="btn btn-link">Cancel</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

<jsp:include page="include/footer.jsp" />