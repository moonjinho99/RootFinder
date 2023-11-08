<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, tr, td, th{
	border: solid 1px rgba(0,0,0,1);
	border-collapse: collapse;
	padding-left: 15px;
	padding-right: 15px;
}

</style>
</head>
<body>
<h1>관리자_회원 탈퇴 화면</h1>

<%
String delete_id = request.getAttribute("delete_id").toString();
%>

<form action="RootFinderServlet?command=admin_delete_userinf_action&delete_id=<%=delete_id%>" method="post">
<table>
<c:forEach items="${admin_user_list}" var="admin_user_list" varStatus="status">	

<tr>
<td>ID</td>
<td>
${admin_user_list.id}
</td>
</tr>

<tr>
<td>사용자명</td>
<td>
${admin_user_list.username}
</td>
</tr>

<tr>
<td>프로필 사진</td>
<td>
<img style="width:80px; height:80px;" src="image/${admin_user_list.profile_img}">
</td>
</tr>

<tr>
<td>나이</td>
<td>
${admin_user_list.birth}
</td>
</tr>


<tr>
<td>성별</td>
<td>
${admin_user_list.gender}
</td>
</tr>

<tr>
<td>EMAIL</td>
<td>
${admin_user_list.email}
</td>
</tr>

<tr>
<td>PHONE</td>
<td>
${admin_user_list.phone}
</td>
</tr>

<tr>
<td>MBTI</td>
<td>
${admin_user_list.mbti} 
</td>
</tr>
</c:forEach>
</table>
<br>
<%
	String delete_username = request.getAttribute("username").toString();
%>
<h3><%=delete_username %>님을 탈퇴처리 하시겠습니까?</h3>

<input type="submit" value="탈퇴">
<input type="button" value="취소" onclick="history.back()">

</form>

</body>
</html>