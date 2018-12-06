<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-rc.2/js/materialize.min.js"></script>
      
<title>Welcome</title>
</head>
<body class="grey lighten-4">
	<div class="container">
		<div class="row">
			<h2>Welcome</h2>
		</div>
		<div class="row">
			<div class="col s6 card z-depth-3">
				<h5>Register</h5>
				<div class="divider"></div>
				<p><form:errors class="red-text" path="user.*"/></p>
				<c:forEach items="${errors}" var="error">
					<p class="red-text"><c:out value="${error.defaultMessage }"/></p>
				</c:forEach>
				<form:form action="/registration" method="post" modelAttribute="newUser">
					<div class="input-field">
						Name:
						<form:input path="name"/>
					</div>
					<div class="input-field">
						Email:
						<form:input path="email" type="email"/>
					</div>
					<div class="input-field">
						Password:
						<form:input path="password" type="password"/>
						<i>*Password must be at least 8 characters</i>
					</div>
					<div class="input-field">
						Conf. PW:
						<form:input path="passwordConfirmation" type="password"/>
					</div>
					<div class="input-field">
						<button class="btn-small right" type="submit">Register</button>
						<br />
					</div>
				</form:form>
			</div>
			<div class="col s7 l5 offset-l1 card z-depth-3">
				<h5>Login</h5>
				<div class="divider"></div>
				<p class="red-text"><c:out  value="${error}" /></p>
				<form action="/login" method="post">
					<div class="input-field">
						Email:
						<input type="text" name="email" />
					</div>
					<div class="input-field">
						Password:
						<input type="password" name="password"/>
					</div>
					<div class="input-field">
						<button type="submit" class="btn-small right">Login</button>
						<br />
					</div>
				</form>
			</div>
        </div>
    </div>
</body>
</html>