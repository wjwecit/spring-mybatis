<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page4!</title>
<style type="text/css">
body {
	font-family: "宋体";
	font-size: 13px;
	color: rgb(0, 0, 0);
}
</style>
</head>
<body>
	<h1>Home page4!</h1>
	msg:${msg}<br>
	date:${date}<br>
	list:${list}<br>
	<c:forEach var="r" items="${list}">
		${r.name}--${r.areaCode}---${r.areaName}<br>
	</c:forEach>
</body>
</html>