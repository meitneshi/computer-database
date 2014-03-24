<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<c:if test="${fn:length(computerList) == 1}">
			<c:out value="${fn:length(computerList)}"/> Computer found
		</c:if>
		<c:out value= "${fn:length(computerList)}"/> Computers found
	</h1>
	
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<form class="navbar-form navbar-left" method="GET">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search by name">
				</div>
				<button type="submit" class="btn btn-default">Filter by name</button>
			</form>
			<a href="addComputer.jsp" class="btn btn-success btn-lg active">Add Computer</a>
		</div>
	</nav>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Computer Name</th>
				<th>Introduced Date</th>
				<th>Discontinued Date</th>
				<th>Company</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${computerList}" var="computer">
				<tr>
					<td>
						<a href="#" onclick="">
							<c:out value="${computer.name }"/>
						</a>
					</td>
					<td>
						<c:out value="${computer.introduced }"/>
					</td>
					<td>
						<c:out value="${computer.discontinued }"/>
					</td>
					<td>
						<c:out value="${computer.company.name }"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<jsp:include page="include/footer.jsp" />
