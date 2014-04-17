<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="link" %>

<%@ attribute name="currentPageNumber" %>
<%@ attribute name="pageMax" %>
<%@ attribute name="filter" %>
<%@ attribute name="order" %>
<%@ attribute name="criteria" %>

<ul class="pagination">
<!-- button first page -->
	<li>
		<link:linkGen label="&laquo;" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="1" filter="${filter }" order="${order }" criteria="${criteria }"/>
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
				<link:linkGen label="&lt;" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber-1 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
			</li>
		</c:otherwise>
	</c:choose>
	
<!-- Display page Number and +2/-2 pages with activation-->
<!-- Page -2 (if exist)  -->
	<c:if test="${currentPageNumber-2 > 0 }">
		<li>
			<link:linkGen label="${currentPageNumber-2 }" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber-2 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
		</li>
	</c:if>
	
<!-- Page -1 (if exists) -->
	<c:if test="${currentPageNumber-1 > 0 }">
		<li>
			<link:linkGen label="${currentPageNumber-1 }" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber-1 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
		</li>
	</c:if>
	
<!-- Current page -->
	<li class="active">
		<link:linkGen label="${currentPageNumber }" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber }" filter="${filter }" order="${order }" criteria="${criteria }"/>
	</li>
	
<!-- Page +1 (if exist)  -->
	<c:if test="${currentPageNumber+1 <= pageMax }">
		<li>
			<link:linkGen label="${currentPageNumber+1 }" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber+1 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
		</li>
	</c:if>
	
<!-- Page +2 (if exists) -->
	<c:if test="${currentPageNumber+2 <= pageMax }">
		<li>
			<link:linkGen label="${currentPageNumber+2 }" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber+2 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
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
				<link:linkGen label="&gt;" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${currentPageNumber+1 }" filter="${filter }" order="${order }" criteria="${criteria }"/>
			</li>
		</c:otherwise>
	</c:choose>

<!-- button last page -->
	<li>
		<link:linkGen label="&raquo;" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="${pageMax }" filter="${filter }" order="${order }" criteria="${criteria }"/>
	</li>
</ul>