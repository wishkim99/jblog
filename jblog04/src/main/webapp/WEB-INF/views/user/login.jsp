<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content" id="user">
		<h1 class="logo">JBlog</h1>
		<ul class="menu">
			<li><a href="${pageContext.request.contextPath}">메인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
		</ul>
		<form class="login-form" name="loginform" method="post" action="${pageContext.request.contextPath }/user/auth">
      		<label>아이디</label> 
      		<input type="text" name="id" type="text" value="${email }">
      		<label >패스워드</label> 
      		<input type="password" name="password">
	      		<c:if test="${result == 'fail' }">
							<p>
								로그인이 실패 했습니다.
							</p>
				</c:if>
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>
