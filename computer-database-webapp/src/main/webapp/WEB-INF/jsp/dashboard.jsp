<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="link" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="option" %>

<jsp:include page="include/header.jsp" />
<jsp:include page="include/leftMenu.jsp" />


<spring:message code="format.datePattern" var="datePattern"/>
<spring:message code="delete.confirm" var="deleteConfirm" />
<spring:message code="delete.warning" var="deleteWarning" />


<div id="main" class="col-md-10">
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
	
	<page:pagination pageMax="${page.pageMax }" currentPageNumber="${page.currentPagenumber }" filter="${page.filter }" order="${page.order }" criteria="${page.criteria }"></page:pagination>	
	
	<c:if test="${page.add }">
		<div class="alert alert-success"><spring:message code="add.success" /></div>
	</c:if>
	
	<c:if test="${page.edit }">
		<div class="alert alert-success"><spring:message code="edit.success" /></div>
	</c:if>
	
	<c:if test="${page.delete }">
		<div class="alert alert-success"><spring:message code="delete.success" /></div>
	</c:if>
	
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
								<joda:format value="${computer.introduced}" pattern="${datePattern }"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td id="discontinued">
						<c:choose>
							<c:when test="${computer.discontinued == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<joda:format value="${computer.discontinued}" pattern="${datePattern }"/>
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
						<a type="button" href="DeleteComputer?id=${computer.id }" class="btn btn-danger" onclick="return confirm('${deleteConfirm} ${deleteWarning }')">
							<span class="glyphicon glyphicon-trash" ></span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<jsp:include page="include/footer.jsp" />
