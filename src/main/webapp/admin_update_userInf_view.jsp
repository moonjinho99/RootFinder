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
<h1>관리자_회원 정보 수정 화면</h1>

<%
String current_id = request.getAttribute("current_id").toString();
String current_profile_img ="";
try{
	current_profile_img = request.getAttribute("current_profile_img").toString();
}catch(Exception e)
{
	System.out.println(e.getMessage());	
}
%>

<form action="RootFinderServlet?command=admin_update_userinf_action&current_id=<%=current_id%>&current_profile_img=<%=current_profile_img%>" method="post" enctype="multipart/form-data">
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
<input type="text" name="update_username" value="${admin_user_list.username}">
</td>
</tr>

<tr>
<td>프로필 사진</td>
<td>
<img src="image/${admin_user_list.profile_img}" style="width:80px; height:80px;">
<input type="file" name="update_profile_img" value="${admin_user_list.profile_img}">
</td>
</tr>
</c:forEach>
</table>
</form>
<input type="submit" value="수정">
<input type="button" value="취소" onclick="history.back()">

</body>
</html>