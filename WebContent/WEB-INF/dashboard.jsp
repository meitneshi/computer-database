<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="page" %>

<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">
		<c:if test="${numberOfComputer == 1}">
			<c:out value="${numberOfComputer}"/> Computer found
		</c:if>
		<c:out value= "${numberOfComputer}"/> Computers found
	</h1>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<form class="navbar-form navbar-left" method="POST">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search by name">
				</div>
				<button type="submit" class="btn btn-default">Filter by name</button>
			</form>
			<div class="navbar-right">
				<a href="/computer_database/AddComputer" class="btn btn-success btn-lg active">Add Computer</a>
			</div>
		</div>
	</nav>
	
	<c:if test="${displayDivEdit}">
		<div class="alert alert-success">Your computer have been successfully edited</div>
	</c:if>
	
	<page:pagination pageMax="${pageMax }" currentPageNumber="${currentPageNumber }" offsetSQL="${offsetSQL }"></page:pagination>	

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
			<c:forEach items="${computerPageList}" var="computer">
				<tr>
					<td>
						
						<a href="/computer_database/EditComputer?id=${computer.id }" onclick="">
							<c:out value="${computer.name }"/>
						</a>
					</td>
					<td>
						<c:choose>
							<c:when test="${computer.introduced == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.introduced }"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${computer.discontinued == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.discontinued }"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${computer.company.name == null}">
								<c:out value="N/A"/>
							</c:when>
							<c:otherwise>
								<c:out value="${computer.company.name }"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>

<jsp:include page="include/footer.jsp" />
