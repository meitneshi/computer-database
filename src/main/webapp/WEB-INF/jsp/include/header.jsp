<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link href="resources/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" media="screen">

<script src="resources/js/jQuery-2.1.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.1.47/jquery.form-validator.min.js"></script>

</head>
<body>
	<header class="navbar">
		<h1 class="navbar-link">
			<a href="dashboard.jsp"> <spring:message code="header.title"/> </a>
		</h1>
		<span style="float: right">
    		<a href="Dashboard?lang=en"><img src="resources/img/unionjack.jpg" alt="Translate into english" /></a> 
    		<a href="Dashboard?lang=fr"><img src="resources/img/francais.jpg" alt="Traduire en français" /></a>
		</span>
	</header>