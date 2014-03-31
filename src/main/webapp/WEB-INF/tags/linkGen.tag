<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ attribute name="servlet" %>						<!-- servlet -->
<%@ attribute name="entitiesperpage" %>				<!-- entities per page -->
<%@ attribute name="page" %>						<!-- current page number -->
<%@ attribute name="filter" %>						<!-- filter of search -->
<%@ attribute name="order" %>						<!-- order by  -->
<%@ attribute name="criteria" %>					<!-- criteria of order -->
<%@ attribute name="label" %>						<!-- href label -->
<%@ attribute name="type" %>						<!-- href type -->


<!-- Initialize url -->
<c:set var="url" value="/computer_database_maven/"/>


<!-- Set Variable in the url -->
<!-- Add the servlet -->
<c:set var="url" value="${url }${servlet }"/>

<!-- Add the number of entities per page (default = 30)-->
<c:choose>
	<c:when test="${!empty entitiesperpage }">
		<c:set var="url" value="${url }?entitiesperpage=${entitiesperpage }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }?entitiesperpage=30"/>
	</c:otherwise>
</c:choose>

<!-- Add the current Page number (default = 1) -->
<c:choose>
	<c:when test="${!empty page }">
		<c:set var="url" value="${url }&page=${page }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&page=1"/>
	</c:otherwise>
</c:choose>


<!-- Add the filter if exist -->
<c:choose>
	<c:when test="${!empty filter }">
		<c:set var="url" value="${url }&filter=${filter }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&filter="/>
	</c:otherwise>
</c:choose>

<!-- Add the order criteria (default = asc (ie ascendant)) -->
<c:choose>
	<c:when test="${!empty order }">
		<c:set var="url" value="${url }&order=${order }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&order=asc"/>
	</c:otherwise>
</c:choose>

<!-- Add the criteria of order (default = name) -->
<c:choose>
	<c:when test="${!empty criteria }">
		<c:set var="url" value="${url }&criteria=${criteria }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&criteria=name"/>
	</c:otherwise>
</c:choose>
		
<!-- creation href -->
<c:choose>
	<c:when test="${!empty type }">
		<a type=${type } href="${url }">${label }</a>
	</c:when>
	<c:otherwise>
		<a href="${url }">${label }</a>
	</c:otherwise>
</c:choose>


