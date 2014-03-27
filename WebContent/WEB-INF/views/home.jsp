<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page!</title>
<style type="text/css"> 
	body{font-family: "宋体";font-size: 13px;color: rgb(0, 0, 0);}
</style>
</head>
<body>
	<h1>Home page!</h1>
	<h3>${date}</h3><br>
	<!-- <iframe scrolling="auto"></iframe> -->	
	<%-- <sql:setDataSource dataSource="${dataSource}"/>
	<sql:query var="users" sql="select * from SM_User"/> --%>
	
	<%-- <c:forEach var="row" items="${users.rows}">
		${row.userId},${row.user_Name}
	</c:forEach> --%>
	
	<hr>
	<%
		Pattern p = Pattern.compile("('+)|(;+)|(=+)|(<+)|(>+)|((--)+)|((or)+)|(%+)", Pattern.CASE_INSENSITIVE);
		String phone = "123dd88dsaf='aaa' oRR a=eea+";
		Matcher m = p.matcher(phone);
		//phone=phone.replaceAll("[';=<>(--)(or)]", "");
		out.println("phone:" + m.replaceAll(""));
		out.println("<br>");
		String orderId = "2013-06-21-09-28-17-98";
		String phoneNum = "18777998717";
		float wmoney = 10;
		java.math.BigDecimal money = new java.math.BigDecimal(30);
		money = money.setScale(2);
		int rechargeResult = 1;
		long time = System.currentTimeMillis();
		String key = "diakadodonewshiyijia";
		//String code = MD5.encrypt(orderId + phoneNum + money + rechargeResult + time + key);
		StringBuilder url = new StringBuilder("http://localhost:8080/opcddn/common/sim_payres.jsp?");
		url.append("orderId=" + orderId);
		url.append("&phoneNum=" + phoneNum);
		url.append("&money=" + money);
		url.append("&time=" + time);
		//url.append("&code=" + code);
		url.append("&rechargeResult="+rechargeResult);
		out.println(url);
		//response.sendRedirect(url.toString());
	%>
</body>
</html>