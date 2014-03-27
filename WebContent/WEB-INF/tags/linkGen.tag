<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ attribute name="servlet" %>			<!-- servlet -->
<%@ attribute name="epp" %>				<!-- entities per page -->
<%@ attribute name="p" %>				<!-- current page number -->
<%@ attribute name="filter" %>			<!-- filter of search -->
<%@ attribute name="on" %>				<!-- order by  -->
<%@ attribute name="c" %>				<!-- criteria of order -->
<%@ attribute name="label" %>			<!-- href label -->
<%@ attribute name="type" %>			<!-- href type -->


<!-- Initialize url -->
<c:set var="url" value="/computer_database/"/>


<!-- Set Variable in the url -->
<!-- Add the servlet -->
<c:set var="url" value="${url }${servlet }"/>

<!-- Add the number of entities per page (default = 30)-->
<c:choose>
	<c:when test="${!empty epp }">
		<c:set var="url" value="${url }?epp=${epp }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }?epp=30"/>
	</c:otherwise>
</c:choose>

<!-- Add the current Page number (default = 1) -->
<c:choose>
	<c:when test="${!empty p }">
		<c:set var="url" value="${url }&p=${p }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&p=1"/>
	</c:otherwise>
</c:choose>


<!-- Add the filter if exist -->
<c:if test="${!empty filter }">
	<c:set var="url" value="${url }&filter=${filter }"></c:set>
</c:if>

<!-- Add the order criteria (default = asc (ie ascendant)) -->
<c:choose>
	<c:when test="${!empty on }">
		<c:set var="url" value="${url }&on=${on }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&on=asc"/>
	</c:otherwise>
</c:choose>

<!-- Add the criteria of order (default = name) -->
<c:choose>
	<c:when test="${!empty c }">
		<c:set var="url" value="${url }&c=${c }"/>
	</c:when>
	<c:otherwise>
		<c:set var="url" value="${url }&on=name"/>
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


