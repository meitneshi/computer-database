<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ attribute name="value" %>				<!-- value of option -->
<%@ attribute name="label" %>				<!-- label for option -->
<%@ attribute name="selected" %>			<!-- selected or not -->
<%@ attribute name="max" %>					<!-- number max of computer -->
<%@ attribute name="entities" %>			<!-- number of entities per page -->

<c:set var="valueopt" value="${value}"/>

<c:choose>
	<c:when test="${value == max }">
		<c:set var="label" value="Show all Computer"/>
	</c:when>
	<c:otherwise>
		<c:set var="label" value="${value}"/>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${value ==  entitiesPerPage}">
		<c:set var="selected" value="selected"/>
	</c:when>
	<c:otherwise>
		<c:set var="selected" value=""/>
	</c:otherwise>
</c:choose>

<option ${selected } value="${value }">${label }</option>