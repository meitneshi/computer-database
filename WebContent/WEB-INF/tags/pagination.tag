<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="currentPageNumber" %>
<%@ attribute name="offsetSQL" %>
<%@ attribute name="pageMax" %>

<c:out value="${pageMax }" />
<c:out value="${currentPageNumber }" />
<%-- <c:out value="${offsetSQL }" /> --%>
