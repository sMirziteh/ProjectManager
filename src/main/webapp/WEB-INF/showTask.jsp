<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>
      
<title><c:out value="${task.content}"/></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h4>Task: <c:out value="${task.content}"/></h4>
		</div>
		<div class="row">
			<div class="card col s5">
				<ul>
					<li>Creator: <c:out value="${task.creator.name }"/></li>
					<li>Assignee: <c:out value="${task.assignee.name }"></c:out></li>
					<li>Priority: <c:out value="${task.priority }"/></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<c:set value="${sessionScope.user_id }" var="user"/>
			<c:set value="${task.creator.id }" var="creator"/>
			<c:set value="${task.assignee.id }" var="assignee"/>
			<c:if test="${user eq creator }">
				<p>
					<a href="/task/${task.id }/edit" class="btn">Edit</a>
					<a href="/task/${task.id }/delete" class="btn">Delete</a>
				</p>
			
			</c:if>
		</div>
		<div class="row">
			<c:if test="${user eq assignee }">
				<a href="/task/${task.id }/complete" class="btn blue">Completed</a>
			</c:if>
		</div>
	</div>
</body>
</html>