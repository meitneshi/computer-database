<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="include/header.jsp" />

<hr>

<table>
	<tr>
		<td>
			<img src="resources/img/errors/daleks.jpg">
		</td>
		<td>
			<h1><spring:message code="403.header"/><br>
			<spring:message code="403.message"/><br>
			<a href="Dashboard"><spring:message code="error.redirect" /></a></h1>
		</td>
	</tr>
</table>

<jsp:include page="include/footer.jsp" />