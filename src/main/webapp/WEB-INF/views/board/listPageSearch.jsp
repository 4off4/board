<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 페이징+검색</title>
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
	<br/>
	<!-- 페이징 처리 -->
	<div>
		<c:if test="${page.prev}">
			<span>[ <a href="/board/listPageSearch?seq=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
		</c:if>
		<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
			<span>
				<c:if test="${select != num}">
					<a href="/board/listPageSearch?seq=${num}${page.searchTypeKeyword}">${num}</a>
				</c:if> 
				<c:if test="${select == num}">
					<b>${num}</b>
				</c:if>		
			</span>
		</c:forEach>
		
		<c:if test="${page.next}">
			<span>[ <a href="/board/listPageSearch?seq=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
		</c:if>
		<br/>
		
		<!-- 검색 기능 -->
		<div>
		
			<select name="searchType">
				<option value="subject" <c:if test="${page.searchType eq 'subject'}">selected</c:if>>제목</option>
				<option value="content" <c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
				<option value="subject_content" <c:if test="${page.searchType eq 'subject_content'}">selected</c:if>>제목+내용</option>
				<option value="name" <c:if test="${page.searchType eq 'name'}">selected</c:if>>작성자</option>
			</select>
			
			<input type="text" name="keyword" value="${page.keyword}"/>
			<button type="button" id="searchBtn">검색</button>
			<p>${page.count}</p>
		</div>
	</div>
	<script>
		
		// 검색 클릭 시 
		document.getElementById("searchBtn").onclick = function () {
			let searchType = document.getElementsByName("searchType")[0].value;
			let keyword =  document.getElementsByName("keyword")[0].value;
	  	
			// 컨트롤러 연결해주기 
			location.href = "/board/listPageSearch?seq=1" + "&searchType=" + searchType + "&keyword=" + keyword;
		};
	</script>
</body>
</html>