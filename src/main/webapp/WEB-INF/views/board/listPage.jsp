<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 페이징</title>
</head>
<body>
	<div id="nav">
		<%@ include file="../include/nav.jsp" %>
	</div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>	
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
	 			<tr>
					<td>${list.seq}</td>
					<td><a href="/board/view?seq=${list.seq}">${list.subject}</td>
					<td>${list.content}</td>
					<td>${list.name}</td>
					<td>${list.reg_date}</td>
					<td>${list.readCount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징 처리 -->
	<div>
		<c:if test="${page.prev}">
			<span>[ <a href="/board/listPage?seq=${page.startPageNum - 1}">이전</a> ] 
		</c:if>
		
		<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
			<span>
				<c:if test="${select != num}">
					<a href="/board/listPage?seq=${num}">${num}</a>
				</c:if>    
				
				<c:if test="${select == num}">
					<b>${num}</b>
				</c:if>		
			</span>
		</c:forEach>
		
		<c:if test="${page.next}">
			<span>[ <a href="/board/listPage?seq=${page.endPageNum + 1}">다음</a> ] </span>
		</c:if>
	
<%-- 		<c:forEach begin="1" end="${pageNum}" var="num">
			<span>
				<a href="/board/listPage?num=${num}">${num}</a>
			</span>
		</c:forEach> --%>
	</div>
</body>
</html>