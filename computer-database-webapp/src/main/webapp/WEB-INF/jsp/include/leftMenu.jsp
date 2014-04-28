<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>

<div class="col-md-2">
	<nav class="navbar navbar-default">
		<div class="panel-heading"><h1><spring:message code="menu.title"/></h1></div>
		<hr>

<!-- 			Dropdown select number of entites to show -->
		<div>
			<spring:message code="entities.label"/> : 
			<form name="selectNumberOfEntitiesPerPage" method ="GET" action="Dashboard">
				<input type="hidden" name="filter" value="${page.filter }">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="order" value="${page.order }">
				<input type="hidden" name="criteria" value="${page.criteria }">
				<select class="form-control" id="entitiesPerPage" name="entitiesperpage" onChange="this.form.submit();">
					<option value="10" <c:if test="${page.entitiesPerPage == 10 }">selected</c:if>>10</option>
					<option value="30" <c:if test="${page.entitiesPerPage == 30 }">selected</c:if>>30</option>
					<option value="50" <c:if test="${page.entitiesPerPage == 50 }">selected</c:if>>50</option>
					<option value="100" <c:if test="${page.entitiesPerPage == 100 }">selected</c:if>>100</option>
					<option value="${page.numberOfComputer }" <c:if test="${page.entitiesPerPage == page.numberOfComputer }">selected</c:if>><spring:message code="select.showAll"/></option>	
				</select>
			</form>
		</div>
		<hr>
		<div><spring:message code="search.numberOfResult"/> : <c:out value="${page.pageMax }"/></div>
		<hr>

<!-- 			Search by computer name form -->
		<div>
			<form class="form-inline" method="GET" action="Dashboard">
				<input type="hidden" name="entitiesperpage" value="${page.entitiesPerPage }">
				<input type="hidden" name="page" value="1">
				<input type="hidden" name="order" value="asc">
				<input type="hidden" name="criteria" value="name">
				<input type="text" name="filter" class="form-control" placeholder="<spring:message code="search.placeholder"/>">
				<button type="submit" class="btn btn-default"><spring:message code="search.button"/></button>
			</form>
		</div>
		
		
<!-- 			Explicit Searching criteria -->
		<div>
			<c:if test="${!empty page.filter}">
				<spring:message code="search.criteria"/> : "<c:out value="${page.filter }"/>"
			</c:if>
		</div>
		<hr>
		
<!--             Add Computer Button -->
		<div>
			<a href="AddComputer" class="btn btn-success btn-lg active"><spring:message code="dashboard.add"/></a>
		</div>
		<hr>
		
<!-- 			Menu footer -->
		<div>
			<small>
				<spring:message code="menu.credits"/>
			</small>
		</div>
	</nav>
</div>


