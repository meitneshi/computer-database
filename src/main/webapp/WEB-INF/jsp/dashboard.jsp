<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="link" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="option" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<c:choose>
			<c:when test="${page.numberOfComputer == 1}">
				<c:out value="${page.numberOfComputer}"/> <spring:message code="dashboard.computerFound"/> (<spring:message code="dashboard.over"/> <c:out value= "${page.numberTotalOfComputer}"/> <spring:message code="dashboard.computers"/>)
			</c:when>
			<c:otherwise>
				<c:out value= "${page.numberOfComputer}"/> <spring:message code="dashboard.computersFound"/> (<spring:message code="dashboard.over"/> <c:out value= "${page.numberTotalOfComputer}"/> <spring:message code="dashboard.computers"/>)
			</c:otherwise>
		</c:choose>
	</h1>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<form class="navbar-form navbar-left" method="GET" action="Dashboard">
				<div class="form-group">
					<input type="hidden" name="entitiesperpage" value="${page.entitiesPerPage }">
					<input type="hidden" name="page" value="1">
					<input type="hidden" name="order" value="asc">
					<input type="hidden" name="criteria" value="name">
					<input type="text" name="filter" class="form-control" placeholder="<spring:message code="search.placeholder"/>">
				</div>
				<button type="submit" class="btn btn-default"><spring:message code="search.button"/></button>
			</form>
			
<!-- Dropdown select number of entites to show -->
			<div class="navbar-left">
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
			<div class="navbar-left">
				<spring:message code="search.numberOfResult"/> : <c:out value="${page.pageMax }"/><br>
				<c:if test="${!empty page.filter}">
					<spring:message code="search.criteria"/> : "<c:out value="${page.filter }"/>"
				</c:if>
			</div>
			
			
			<div class="navbar-right">
				<a href="AddComputer" class="btn btn-success btn-lg active"><spring:message code="dashboard.add"/></a>
			</div>
		</div>
	</nav>
	
	<page:pagination pageMax="${page.pageMax }" currentPageNumber="${page.currentPagenumber }" filter="${page.filter }" order="${page.order }" criteria="${page.criteria }"></page:pagination>	
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>
					<spring:message code="label.name"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-up' ></span>" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="1" filter="${page.filter }" order="asc" criteria="name"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-down' ></span>" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="1" filter="${page.filter }" order="desc" criteria="name"/>
				</th>
				
				<th>
					<spring:message code="label.introduced"/>
				</th>
				
				<th>
					<spring:message code="label.discontinued"/>
				</th>
				
				<th>
					<spring:message code="label.company"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-up' ></span>" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="1" filter="${page.filter }" order="asc" criteria="company"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-down' ></span>" servlet="Dashboard" entitiesperpage="${page.entitiesPerPage }" page="1" filter="${page.filter }" order="desc" criteria="company"/>
				</th>
				
				<th>
					<spring:message code="label.actions"/>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.computerPageList}" var="computer">
				<tr>
					<td id="name">
						
						<a href="EditComputer?id=${computer.id }" onclick="">
							<c:out value="${computer.name }"/>
						</a>
					</td>
					<td id="introduced">
						<c:choose>
							<c:when test="${computer.introduced == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.introduced }"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td id="discontinued">
						<c:choose>
							<c:when test="${computer.discontinued == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.discontinued }"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td id="company_name">
						<c:choose>
							<c:when test="${computer.company.name == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.company.name }"/>
							</c:otherwise>
						</c:choose>
					</td>
					
					<td id="actions">
						<a type="button" href="EditComputer?id=${computer.id }" class="btn btn-info">
							<span class="glyphicon glyphicon-pencil" ></span>
						</a>
						<a type="button" href="DeleteComputer?id=${computer.id }" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this computer ?')">
							<span class="glyphicon glyphicon-trash" ></span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<jsp:include page="include/footer.jsp" />
