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
      
<title>Edit Task</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>Edit <c:out value="${task.content}"/></h3>
		</div>
		<div class="row">
		<div class="card col s5">
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.defaultMessage }"/>
		</c:forEach>
			<form:form action="/task/${task.id}/edit" method="post" modelAttribute="task">
				<div class="input-field">
					Task
					<form:input path="content"/>
				</div>
				<div class="input-field">
				Assignee
					<form:select path="assignee" class="browser-default">
						<form:option value="${task.assignee.id}"><c:out value="${task.assignee.name }"/></form:option>
						<c:forEach items="${allusers}" var="user">
							<form:option value="${user.id}"><c:out value="${user.name }"/></form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="input-field">
						Priority:
						<form:select path="priority" class="browser-default">
							<form:option value="${task.priority}" ><c:out value="${task.priority}"/></form:option>
							<form:option value="low">Low</form:option>
							<form:option value="medium">Medium</form:option>
							<form:option value="high">High</form:option>
						</form:select>
					</div>
					<div class="input-field">
						<button type="submit" class="btn-small right">Edit</button>
						<br />
					</div>
			</form:form>
		</div>
		</div>
	</div>
</body>
</html>