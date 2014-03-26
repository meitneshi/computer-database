<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>


<%@ attribute name="currentPageNumber" %>
<%@ attribute name="offsetSQL" %>
<%@ attribute name="pageMax" %>
<%@ attribute name="filter" %>
<%@ attribute name="order" %>
<%@ attribute name="criteria" %>

<ul class="pagination">
<!-- button first page -->
	<c:choose>
		<c:when test="${!empty filter }">
			<c:choose>
				<c:when test="${!empty order && !empty criteria }">
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=1&filter=${filter}&on=${order}&c=${criteria}">&laquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=1&filter=${filter}">&laquo;</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${!empty order && !empty criteria }">
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=1&on=${order}&c=${criteria}">&laquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=1">&laquo;</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	
<!-- 	disable previous button if page 1 -->
	<c:choose>
		<c:when test="${currentPageNumber == 1}">
			<li class="disabled">
				<a href="">&lt;</a>
			</li>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${!empty filter }">
					<c:choose>
						<c:when test="${!empty order && !empty criteria }">
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&filter=${filter}&on=${order}&c=${criteria}">&lt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&filter=${filter}">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty order && !empty criteria }">
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&on=${order}&c=${criteria}">&lt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}">&lt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	
<!-- Display page Number and +2/-2 pages with activation-->
<!-- Page -2 (if exist)  -->
	<c:if test="${currentPageNumber-2 > 0 }">
		<c:choose>
			<c:when test="${!empty filter }">
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 2}&filter=${filter}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber - 2 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 2}&filter=${filter}">
								<c:out value="${currentPageNumber - 2 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 2}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber - 2 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 2}">
								<c:out value="${currentPageNumber - 2 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	
<!-- Page -1 (if exists) -->
	<c:if test="${currentPageNumber-1 > 0 }">
		<c:choose>
			<c:when test="${!empty filter }">
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&filter=${filter}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber - 1 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&filter=${filter}">
								<c:out value="${currentPageNumber - 1 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber - 1 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber - 1}">
								<c:out value="${currentPageNumber - 1 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	
<!-- Current page -->
	<c:choose>
		<c:when test="${!empty filter }">
			<li class="active">
				<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber }&filter=${filter}">
					<c:out value="${currentPageNumber }"/>
				</a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="active">
				<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber }">
					<c:out value="${currentPageNumber }"/>
				</a>
			</li>
		</c:otherwise>
	</c:choose>
	
<!-- Page +1 (if exist)  -->
	<c:if test="${currentPageNumber+1 <= pageMax }">
		<c:choose>
			<c:when test="${!empty filter }">
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +1}&filter=${filter}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber +1 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +1}&filter=${filter}">
								<c:out value="${currentPageNumber +1 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +1}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber +1 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +1}">
								<c:out value="${currentPageNumber +1 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	
<!-- Page +2 (if exists) -->
	<c:if test="${currentPageNumber+2 <= pageMax }">
		<c:choose>
			<c:when test="${!empty filter }">
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +2}&filter=${filter}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber +2 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +2}&filter=${filter}">
								<c:out value="${currentPageNumber +2 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty order && !empty criteria }">
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +2}&on=${order}&c=${criteria}">
								<c:out value="${currentPageNumber +2 }"/>
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber +2}">
								<c:out value="${currentPageNumber +2 }"/>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:if>
	
<!-- disable next button if last page -->
	<c:choose>
		<c:when test="${currentPageNumber == pageMax}">
			<li class="disabled">
				<a href="">&gt;</a>
			</li>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${!empty filter }">
					<c:choose>
						<c:when test="${!empty order && !empty criteria }">
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}&filter=${filter}&on=${order}&c=${criteria}">&gt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}&filter=${filter}">&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty order && !empty criteria }">
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}&on=${order}&c=${criteria}">&gt;</a>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${currentPageNumber + 1}">&gt;</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>

<!-- button last page -->
	<c:choose>
		<c:when test="${!empty filter }">
			<c:choose>
				<c:when test="${!empty order && !empty criteria }">
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${pageMax}&filter=${filter}&on=${order}&c=${criteria}">&raquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${pageMax}&filter=${filter}">&raquo;</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${!empty order && !empty criteria }">
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${pageMax}&on=${order}&c=${criteria}">&raquo;</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="/computer_database/Dashboard?epp=${entitiesPerPage }&p=${pageMax}">&raquo;</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</ul>