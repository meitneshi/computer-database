<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<jsp:include page="include/header.jsp" />
<section id="main">

	<h1>Add Computer</h1>
	
	<hr>
	<small>Les champs marqués d'un astérisque (*) sont obligatoires</small>
	
	<div class="container-fluid">
		<form>
			<div class="row" id="computerName">
				<div class="col-md-1"><strong>Computer Name* : </strong></div>
				<div class="col-md-3">
					<input type="text" name="ComputerName" id="ComputerName" class="form-control" placeholder="Computer Name">
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="introducedDate">
				<div class="col-md-1"><strong>Introduced Date : </strong></div>
				<div class="col-md-3">
					<input type="text" name="IntroducedDate" id="IntroducedDate" class="form-control" placeholder="Introduced Date">
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="discontinuedDate">
				<div class="col-md-1"><strong>Discontinued Date : </strong></div>
				<div class="col-md-3">
					<input type="text" name="DiscontinuedDate" id="DiscontinuedDate" class="form-control" placeholder="Discontinued Date">
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
			
			<div class="row" id="company">
				<div class="col-md-1"><strong>Company : </strong></div>
				<div class="col-md-3">
				
				</div>
<!-- 				<div class="col-md-4">pour le message de validation</div> -->
			</div>
	
		</form>
	</div>
	
	<form action="addComputer.jsp" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" />
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" pattern="YY-MM-dd"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="introducedDate" pattern="YY-MM-dd"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<option value="1">Apple</option>
						<option value="2">Dell</option>
						<option value="3">Lenovo</option>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="dashboard.jsp" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />