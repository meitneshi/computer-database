<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>


<%@ attribute name="currentPageNumber" %>
<%@ attribute name="offsetSQL" %>
<%@ attribute name="pageMax" %>
<%@ attribute name="filter" %>

<ul class="pagination">
<!-- button first page -->
	<li>
		<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=1">&laquo;</a>
	</li>

<!-- 	disable previous button if page 1 -->
	<c:choose>
		<c:when test="${currentPageNumber == 1}">
			<li class="disabled">
				<a href="">&lt;</a>
			</li>
		</c:when>
		<c:otherwise>
			<li>
				<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}">&lt;</a>
			</li>
		</c:otherwise>
	</c:choose>
	
<!-- Display page Number and +2/-2 pages with activation-->
<!-- Page -2 (if exist)  -->
	<c:if test="${currentPageNumber-2 > 0 }">
		<li>
			<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 2}">
				<c:out value="${currentPageNumber - 2 }"></c:out>
			</a>
		</li>
	</c:if>
	
<!-- Page -1 (if exists) -->
	<c:if test="${currentPageNumber-1 > 0 }">
		<li>
			<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}">
				<c:out value="${currentPageNumber - 1 }"></c:out>
			</a>
		</li>
	</c:if>
	
<!-- Current page -->
	<li class="active">
		<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber }">
			<c:out value="${currentPageNumber }"></c:out>
		</a>
	</li>
	
<!-- Page +1 (if exists) -->
	<c:if test="${currentPageNumber+1 <= pageMax }">
		<li>
			<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}">
				<c:out value="${currentPageNumber + 1 }"></c:out>
			</a>
		</li>
	</c:if>
	
<!-- Page +2 (if exists) -->
	<c:if test="${currentPageNumber+2 <= pageMax }">
		<li>
			<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 2}">
				<c:out value="${currentPageNumber + 2 }"></c:out>
			</a>
		</li>
	</c:if>
	
<!-- disable next button if last page -->
	<c:choose>
		<c:when test="${currentPageNumber == pageMax}">
			<li class="disabled">
				<a href="">&gt;</a>
			</li>
		</c:when>
		<c:otherwise>
			<li>
				<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}">&gt;</a>
			</li>
		</c:otherwise>
	</c:choose>

<!-- button last page -->
	<li>
		<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${pageMax }">&raquo;</a>
	</li>
</ul>

