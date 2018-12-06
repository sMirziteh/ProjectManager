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
      
<title>Task Manager</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<p class="right">
				<a href="/logout">Logout</a>
			</p>
			<br />
			<h2>Welcome, <c:out value="${user.name}"/></h2>
		</div>
		<div class="row">
		<p class="right">
			<a href="/tasks/desc">Priority Low - High</a> | <a href="/tasks/asc">Priority High - Low</a>
		</p>
			<table class="card striped">
				<thead>
					<tr>
						<th>Task</th>
						<th>Creator</th>
						<th>Assignee</th>
						<th>Priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alltasks}" var="task">
					<tr>
						<td><a href="/task/${task.id}"><c:out value="${task.content}"/></a></td>
						<td><c:out value="${task.creator.name}"/></td>
						<td><c:out value="${task.assignee.name }"/></td>
						<td><c:out value="${task.priority }"></c:out></td>
					</tr>
					
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<p>
				<a href="/createTask"  class="btn right">Create Task</a>
			</p>
		</div>
	</div>
</body>
</html>