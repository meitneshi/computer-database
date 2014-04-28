


<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />

<spring:message code="connection.login" var="labelLogin" />
<spring:message code="connection.pass" var="labelPass" />
<spring:message code="connection.connect" var="labelConnect" />

<form class="form" id="login" name="login" method="POST" action="j_spring_security_check">
	<div class="form-group has-feedback">
		<label class="col-sm-2 control-label">${labelLogin }* : </label>
		<input type="text" name="j_username" value="">
	</div>
	
	<div class="form-group has-feedback">
		<label class="col-sm-2 control-label">${labelPass }* : </label>
		<input type="password" name="j_password">
	</div>
	
	<input class="btn btn-primary" type="submit" value="${labelConnect }">
</form>


<jsp:include page="include/footer.jsp" />
