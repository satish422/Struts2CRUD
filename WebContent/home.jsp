<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<s:head />
</head>
<body>
Hello <s:property value="#session.userName" />, 
<s:if test="userList.size() > 0">
	<div>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>EmailID</th>
			<th>Password</th>
		</tr>
		<s:iterator value="userList">
			<tr>
				<td><s:property value="userId" /></td>
				<td><s:property value="userName" /></td>
				<td><s:property value="emailId" /></td>
				<td><s:property value="password" /></td>
			</tr>
		</s:iterator>
	</table>
	</div>
</s:if>
<a href="logout">Logout</a>
</body>
</html>