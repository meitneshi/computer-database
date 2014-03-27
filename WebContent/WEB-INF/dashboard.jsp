<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="link" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<c:if test="${numberOfComputer == 1}">
			<c:out value="${numberOfComputer}"/> Computer found (over <c:out value= "${nbTotal}"/> computers)
		</c:if>
		<c:out value= "${numberOfComputer}"/> Computers found (over <c:out value= "${nbTotal}"/> computers)
	</h1>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<form class="navbar-form navbar-left" method="POST" action="/computer_database/Dashboard">
				<div class="form-group">
					<input type="hidden" name="entitiesperpage" value="${entitiesPerPage }">
					<input type="hidden" name="page" value="${currentPageNumber }">
					<input type="hidden" name="order" value="asc">
					<input type="hidden" name="criteria" value="name">
					<input type="text" name="filter" class="form-control" placeholder="Search by name">
				</div>
				<button type="submit" class="btn btn-default">Filter by name</button>
			</form>
			
			
<!-- Dropdown select number of entites to show -->
			<div class="navbar-left">
				Number of computer to show per page:
				<form name="selectNumberOfEntitiesPerPage" method ="POST" action="/computer_database/Dashboard">
					<input type="hidden" name="filter" value="${filter }">
					<input type="hidden" name="page" value="1">
					<input type="hidden" name="order" value="${order }">
					<input type="hidden" name="criteria" value="${criteria }">
					<select id="entitiesPerPage" name="entitiesperpage" onChange="this.form.submit();">
						<c:choose>
							<c:when test="${entitiesPerPage == 10 }">
								<option value="10" selected >10</option>
								<option value="30">30</option>
								<option value="50">50</option>
								<option value="100">100</option>
								<option value="${numberOfComputer}">Show all computers</option>
							</c:when>
							<c:when test="${entitiesPerPage == 30 }">
								<option value="10">10</option>
								<option value="30" selected >30</option>
								<option value="50">50</option>
								<option value="100">100</option>
								<option value="${numberOfComputer}">Show all computers</option>
							</c:when>
							<c:when test="${entitiesPerPage == 50 }">
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50" selected >50</option>
								<option value="100">100</option>
								<option value="${numberOfComputer}">Show all computers</option>
							</c:when>
							<c:when test="${entitiesPerPage == 100 }">
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50">50</option>
								<option value="100" selected >100</option>
								<option value="${numberOfComputer}">Show all computers</option>
							</c:when>
							<c:when test="${entitiesPerPage == numberOfComputer }">
								<option value="10">10</option>
								<option value="30">30</option>
								<option value="50">50</option>
								<option value="100">100</option>
								<option value="${numberOfComputer}" selected >Show all computers</option>
							</c:when>
						</c:choose>
					</select>
				</form>
			</div>
			<div class="navbar-left">
				Total number of page found : <c:out value="${pageMax }"/><br>
				<c:if test="${!empty filter}">
					Search Criteria : "<c:out value="${filter }"/>"
				</c:if>
			</div>
			
			
			<div class="navbar-right">
				<a href="/computer_database/AddComputer" class="btn btn-success btn-lg active">Add Computer</a>
			</div>
		</div>
	</nav>
	
	<c:if test="${displayDivEdit}">
		<div class="alert alert-success">Your computer have been successfully edited</div>
	</c:if>
	
	<page:pagination pageMax="${pageMax }" currentPageNumber="${currentPageNumber }" filter="${filter }" order="${order }" criteria="${criteria }"></page:pagination>	
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>
					Computer Name
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-up' ></span>" servlet="Dashboard" entitiesperpage="${entitiesPerPage }" page="${currentPageNumber }" filter="${filter }" order="asc" criteria="name"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-down' ></span>" servlet="Dashboard" entitiesperpage="${entitiesPerPage }" page="${currentPageNumber }" filter="${filter }" order="des" criteria="name"/>
				</th>
				
				<th>
					Introduced Date
 					
				</th>
				
				<th>
					Discontinued Date
					
				</th>
				
				<th>
					Company
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-up' ></span>" servlet="Dashboard" entitiesperpage="${entitiesPerPage }" page="${currentPageNumber }" filter="${filter }" order="asc" criteria="company"/>
					<link:linkGen type="button" label="<span class='glyphicon glyphicon-chevron-down' ></span>" servlet="Dashboard" entitiesperpage="${entitiesPerPage }" page="${currentPageNumber }" filter="${filter }" order="des" criteria="company"/>
				</th>
				
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computerPageList}" var="computer">
				<tr>
					<td id="name">
						
						<a href="/computer_database/EditComputer?id=${computer.id }" onclick="">
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
						<a type="button" href="/computer_database/EditComputer?id=${computer.id }" class="btn btn-info">
							<span class="glyphicon glyphicon-pencil" ></span>
						</a>
						<a type="button" href="/computer_database/DeleteComputer?id=${computer.id }" class="btn btn-danger"onclick="return confirm('Are you sure you want to delete this computer ?')">
							<span class="glyphicon glyphicon-trash" ></span>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<jsp:include page="include/footer.jsp" />
