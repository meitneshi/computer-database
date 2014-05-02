<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />

<spring:message code="connection.login" var="labelLogin" />
<spring:message code="connection.pass" var="labelPass" />
<spring:message code="connection.connect" var="labelConnect" />
<spring:message code="form.header" var="loginHeader" />

<c:if test="${error }">
	<div class="alert alert-danger"><spring:message code="login.error" /></div>
</c:if>

<c:if test="${disconnect }">
	<div class="alert alert-success"><spring:message code="disconnect.success" /></div>
</c:if>

<form class="form-horizontal" id="login" method="POST" action="j_spring_security_check">
		<fieldset>
			<legend>${loginHeader }</legend>
			<div class="control-group">
				<label class="control-label">${labelLogin }* : </label>
				<div class="controls">
					<input type="text" id="j_username" name="j_username">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">${labelPass }* : </label>
				<div class="controls">
					<input type="password" id="j_password" name="j_password">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button type="submit" class="btn btn-success" >${labelConnect }</button>
				</div>
			</div>
		</fieldset>
	</form>


<jsp:include page="include/footer.jsp" />
