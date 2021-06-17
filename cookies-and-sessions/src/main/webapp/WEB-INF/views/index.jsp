<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<style>
.errors{
color:red;
position:fixed;
text-align:left;
margin-left:10px;
}
</style>
</head>
<body>
	<h1 align="center">Love Calculator</h1>
	<form:form action="process-page-dto-modelAttribute" method="get" modelAttribute="userInfo">
		<div align="center">
			<p>
				<lable for="yourName">Your Name: </lable>
				<form:input type="text" id="yourName" path="yourName" />
				<form:errors path="yourName" class="errors"/>
			</p>
			<p>
				<lable for="crushName">Crush Name: </lable>
				<form:input type="text" id="crushName" path="crushName" />
				<form:errors path="crushName" class="errors"/>
				
			</p>
			<p>
				<form:checkbox  id="termsAndConditions" path="termsAndConditions" />
				<label for="termsAndConditions">I am agreeing this is for fun</label>
				<form:errors path="termsAndConditions" class="errors"/>
				
			</p>
			<input type="submit" value="Calculate" />
		</div>
	</form:form>
</body>
</html>