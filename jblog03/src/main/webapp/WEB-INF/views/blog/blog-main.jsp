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
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title}</h1>
			<ul>
				<c:choose>
				<c:when test="${empty authUser}">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					<c:if test="${authUser.id==blogVo.user_id}">
					<li><a href="${pageContext.request.contextPath}/${blogVo.user_id}/admin/basic">블로그 관리</a></li>
					</c:if>
					
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${LastPostVo.title }</h4>
				<p>
						${LastPostVo.contents }
					<p>
				</div>
				<div class="blog-contents">
					<h4>${selectedPostVo.title }</h4>
				<p>
						${selectedPostVo.contents }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items = "${plist}" var = "postVo" varStatus = "status">
					<tr>
						<li><a href="${pageContext.request.contextPath}/${blogVo.user_id}/${postVo.category_no}/${postVo.no}">${postVo.title}</a></li>
						<li><span>${postVo.reg_date}</span></li>
					</tr>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<c:forEach items="${list}" var="categoryVo" varStatus="status">
			<li><a href="${pageContext.request.contextPath}/${blogVo.user_id}/${categoryVo.no }">${categoryVo.name }</a></li>
		</c:forEach>
		</div>
		
		<div id="footer">
			<p>
				<strong>${blogVo.title}</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>